package ir.maktab.services;

import ir.maktab.data.dao.OfferDao;
import ir.maktab.data.models.entities.Offer;
import ir.maktab.data.models.entities.Order;
import ir.maktab.data.models.entities.roles.Expert;
import ir.maktab.data.models.enums.OfferStatus;
import ir.maktab.data.models.enums.OrderStatus;
import ir.maktab.dto.mapper.OfferMapper;
import ir.maktab.dto.mapper.OrderMapper;
import ir.maktab.dto.modelDtos.OfferDto;
import ir.maktab.dto.modelDtos.OrderDto;
import ir.maktab.dto.modelDtos.roles.ExpertDto;
import ir.maktab.exceptions.EditionDenied;
import ir.maktab.exceptions.OfferNotFound;
import ir.maktab.validation.ControlEdition;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OfferServiceImp implements OfferService {
    private final OrderService orderService;
    private final ExpertService expertService;
    private final OfferDao offerDao;
    private final ControlEdition controlEdition;
    private final OrderMapper orderMapper;
    private final OfferMapper offerMapper;


    @Override
    public OfferDto saveOffer(OfferDto offerDto) throws ParseException {

        if (controlEdition.isValidToEdit(offerDto.getOrder().getStatus())) {

            Offer offer = offerMapper.toOffer(offerDto);
            offer.setIdentificationCode(UUID.randomUUID());
            offer.getExpert().setId(expertService.findExpertByEmail(offer.getExpert().getEmail()).getId());
            offer.getOrder().setId(orderService.findOrderId(offer.getOrder().getIdentificationCode()));
            offer.setStatus(OfferStatus.WAITING_FOR_ACCEPT);

            Offer savedOffer = offerDao.save(offer);

            return offerMapper.toOfferDto(savedOffer);
        } else throw new RuntimeException("submit offer fail");
    }

    @Override
    public OfferDto editStartDateOffer(OfferDto offerDto, String startDate) throws ParseException {
        offerDto.setStartDate(startDate);
      return   offerMapper.toOfferDto(updateOffer(offerDto));
    }

    @Override
    public OfferDto editOfferSuggestedPrice(OfferDto offerDto, String suggestedPrice) throws ParseException {
        offerDto.setSuggestedPrice(suggestedPrice);
        Offer offer = updateOffer(offerDto);
        return   offerMapper.toOfferDto(offer);
        }

    @Override
    public OfferDto editSuggestedDurationOfService(OfferDto offerDto, String suggestedDurationOfService) throws ParseException {
        offerDto.setSuggestedDurationOfService(suggestedDurationOfService);

       return offerMapper.toOfferDto(updateOffer(offerDto));
    }

    @Override
    public OfferDto changeOrderStatus(OfferDto offerDto, String orderStatus) throws ParseException {
        Offer offer = findOfferByIdentificationCode(offerDto.getIdentificationCode());
        orderService.changeOrderStatus(offer.getOrder(),OrderStatus.valueOf(orderStatus));

        return
                offerMapper.toOfferDto(findOfferByIdentificationCode(offerDto.getIdentificationCode()));
    }


    @Override
    public List<OfferDto> findOfferDtosOfOrder(OrderDto orderDto) {
        Order order = orderMapper.toOrder(orderDto);
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
    public OfferDto findAcceptedOfferOfOrder(OrderDto orderDto) {

        Order order = orderService.findOrderByIdentificationCode(orderDto.getIdentificationCode());

        Optional<Offer> offer = offerDao.findByOrderAndStatus(order, OfferStatus.ACCEPT);
        if(offer.isPresent())
            return offerMapper.toOfferDto(offer.get());
        else throw  new OfferNotFound();
    }

    @Override
    public void deleteOfferFromOrder(OfferDto offerDto) throws ParseException {
        if (controlEdition.isValidToEdit(offerDto.getOrder().getStatus())) {
            Offer offer = offerMapper.toOffer(offerDto);
            long offerId = findOfferId(offer.getIdentificationCode());
            offer.setId(offerId);
            offerDao.delete(offer);
        }
    }

    @Override
    public void acceptOfferForOrder(Offer offer) {

        List<Offer> offers = offerDao.findByOrder(offer.getOrder());
        for (Offer offerOfOrder : offers) {
            if (offerOfOrder.equals(offer)) {
                offerOfOrder.setStatus(OfferStatus.ACCEPT);
            } else {
                offerOfOrder.setStatus(OfferStatus.NOT_ACCEPT);
            }
            offerDao.save(offerOfOrder);
        }
        offer.getOrder().setExpert(offer.getExpert());
        orderService.changeOrderStatus(offer.getOrder(),OrderStatus.WAITING_FOR_COMING_EXPERT_TO_YOUR_PLACE);
    }

    @Override
    public List<OfferDto> findByOfferSortedByPriceAndExpertRate(OrderDto orderDto) {
        Order order = orderMapper.toOrder(orderDto);
        long orderId = orderService.findOrderId(order.getIdentificationCode());
        order.setId(orderId);
        List<Offer> offers = offerDao.findByOrderOrderBySuggestedPriceAsc(order);
        offers.sort(Comparator.comparing(Offer::getExpert));
        return offers
                .stream()
                .map(offerMapper::toOfferDto)
                .collect(Collectors.toList());
    }

    @Override
    public Offer updateOffer(OfferDto offerDto) throws ParseException {
        Offer offer = offerMapper.toOffer(offerDto);

        if (controlEdition.isValidToEdit(offer.getOrder().getStatus())) {
            long offerId = findOfferId(offer.getIdentificationCode());
            offer.setId(offerId);
            offer.setOrder(orderService.findOrderByIdentificationCode(offer.getOrder().getIdentificationCode()));
            offer.setExpert(expertService.findExpertByEmail(offer.getExpert().getEmail()));
            return offerDao.save(offer);
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
    public OfferDto findOfferDtoByIdentificationCode(UUID identificationCode) {
        Optional<Offer> offer = offerDao.findByIdentificationCode(identificationCode);
        if (offer.isPresent())
            return offerMapper.toOfferDto(offer.get());
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



