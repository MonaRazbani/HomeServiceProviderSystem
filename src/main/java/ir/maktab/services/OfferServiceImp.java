package ir.maktab.services;

import ir.maktab.data.dao.OfferDao;
import ir.maktab.data.models.entities.roles.Expert;
import ir.maktab.dto.mapper.OfferMapper;
import ir.maktab.dto.mapper.OrderMapper;
import ir.maktab.dto.modelDtos.OfferDto;
import ir.maktab.dto.modelDtos.OrderDto;
import ir.maktab.dto.modelDtos.roles.ExpertDto;
import ir.maktab.exceptions.EditionDenied;
import ir.maktab.exceptions.OfferNotFound;
import ir.maktab.data.models.entities.Offer;
import ir.maktab.data.models.entities.Order;
import ir.maktab.data.models.enums.OfferStatus;
import ir.maktab.data.models.enums.OrderStatus;
import ir.maktab.validation.ControlEdition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Service

public class OfferServiceImp implements OfferService{
    private final OrderService orderService;
    private final ExpertService expertService;
    private final OfferDao offerDao;
    private final ControlEdition controlEdition;
    private final OrderMapper orderMapper ;
    private final OfferMapper offerMapper ;

    @Autowired
    public OfferServiceImp(OrderService orderService, ExpertService expertService, OfferDao offerDao,
                           ControlEdition controlEdition, OrderMapper orderMapper, OfferMapper offerMapper) {
        this.orderService = orderService;
        this.expertService = expertService;
        this.offerDao = offerDao;
        this.controlEdition = controlEdition;
        this.orderMapper = orderMapper;
        this.offerMapper = offerMapper;
    }


    @Override
    public OfferDto saveOffer(OfferDto offerDto) {

        if (controlEdition.isValidToEdit(offerDto.getOrder().getStatus())) {

            Offer offer = offerMapper.toOffer(offerDto);
            offer.setIdentificationCode(UUID.randomUUID());
            offer.getExpert().setId(expertService.findExpertByEmail(offer.getExpert().getEmail()).getId());
            offer.getOrder().setId(orderService.findOrderId(offer.getOrder().getIdentificationCode()));
            offer.setStatus(OfferStatus.WAITING_FOR_ACCEPT);

            Offer savedOffer = offerDao.save(offer);

            return offerMapper.toOfferDto(savedOffer);
        }
        else throw new RuntimeException("submit offer fail");
    }

    @Override
    public void editStartDateOffer(OfferDto offerDto, String startDateString) throws ParseException {
        Date startDate = new SimpleDateFormat("HH:mm").parse(startDateString);
        offerDto.setStartDate(startDate);
        updateOffer(offerDto);
    }

    @Override
    public void editOfferSuggestedPrice(OfferDto offerDto, String suggestedPrice) {
        offerDto.setSuggestedPrice(suggestedPrice);
        updateOffer(offerDto);
    }

    @Override
    public void editSuggestedDurationOfService(OfferDto offerDto, String suggestedDurationOfService) {
        offerDto.setSuggestedDurationOfService(suggestedDurationOfService);
        updateOffer(offerDto);
    }


    @Override
    public List<OfferDto> findOfferDtosOfOrder(Order order) {

        long orderId = orderService.findOrderId(order.getIdentificationCode());
        order.setId(orderId);
        List<Offer> offers = offerDao.findByOrder(order);

        /*if (offers.isEmpty())  چون نمیخوام تو کنترار به اکسپشن بخورم میخوام لیست خالی رو هم نشون بده
            throw new OfferNotFound();*/

        return offers
                .stream()
                .map(offerMapper::toOfferDto)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteOfferFromOrder(OfferDto offerDto) {
        if (controlEdition.isValidToEdit(offerDto.getOrder().getStatus())) ;
        {
            Offer offer = offerMapper.toOffer(offerDto);
            long offerId = findOfferId(offer.getIdentificationCode());
            offer.setId(offerId);
            offerDao.delete(offer);
        }
    }

    @Override
    public void acceptOfferForOrder(Offer offer) {
        long offerId = findOfferId(offer.getIdentificationCode());

        offer.setId(offerId);
        offer.setStatus(OfferStatus.ACCEPT);
        offerDao.save(offer);
        offer.getOrder().setExpert(offer.getExpert());
        offer.getOrder().setStatus(OrderStatus.WAITING_FOR_COMING_EXPERT_TO_YOUR_PLACE);
        orderService.updateOrder(offer.getOrder());
    }

    @Override
    public List<Offer> findByOfferSortedByPriceAndExpertRate(OrderDto orderDto) {
        Order order = orderMapper.toOrder(orderDto);
        long orderId = orderService.findOrderId(order.getIdentificationCode());
        order.setId(orderId);
        List<Offer> offers = offerDao.findByOrderOrderBySuggestedPriceAsc(order);
        offers.sort(Comparator.comparing(Offer::getExpert));
        return offers;
    }

    @Override
    public void updateOffer(OfferDto offerDto)  {
        Offer offer = offerMapper.toOffer(offerDto);

        if (controlEdition.isValidToEdit(offer.getOrder().getStatus())) {
            long offerId = findOfferId(offer.getIdentificationCode());
            offer.setId(offerId);
            offerDao.save(offer);
        } else {
            throw new EditionDenied();
        }
    }

    @Override
    public Offer findOfferByIdentificationCode(UUID identificationCode) {
        Optional<Offer> offer = offerDao.findByIdentificationCode(identificationCode);
        if (offer.isPresent())
            return offer.get();
        else
            throw new OfferNotFound();
    }

    @Override
    public long findOfferId(UUID identificationCode) {
        Offer offer = findOfferByIdentificationCode(identificationCode);
        return offer.getId();
    }

    @Override
    public Offer findOfferById(long id) {
        Optional<Offer> offer = offerDao.findById(id);
        if (offer.isPresent())
            return offer.get();
        else
            throw new OfferNotFound();
    }

    @Override
    public List<OfferDto> findExpertOffer(ExpertDto expertDto) {
        Expert expert = expertService.findExpertByEmail(expertDto.getEmail());
        return offerDao.findByExpert(expert)
                .stream()
                .map(offerMapper::toOfferDto)
                .collect(Collectors.toList());
    }
}



