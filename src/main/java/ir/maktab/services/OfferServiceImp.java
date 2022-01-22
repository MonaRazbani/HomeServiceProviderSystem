package ir.maktab.services;

import ir.maktab.data.dao.OfferDao;
import ir.maktab.dto.modelDtos.OfferDto;
import ir.maktab.dto.modelDtos.OrderDto;
import ir.maktab.exceptions.EditionDenied;
import ir.maktab.exceptions.OfferNotFound;
import ir.maktab.data.models.entities.Offer;
import ir.maktab.data.models.entities.Order;
import ir.maktab.data.models.enums.OfferStatus;
import ir.maktab.data.models.enums.OrderStatus;
import ir.maktab.validation.ControlEdition;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
@RequiredArgsConstructor
public class OfferServiceImp implements OfferService{
    private final OrderServiceImp orderServiceImp;
    private final ExpertServiceImp expertService;
    private final OfferDao offerDao;
    private final ModelMapper modelMapper;
    private final ControlEdition controlEdition;


    @Override
    public OfferDto saveOffer(OfferDto offerDto, String startDateString) throws ParseException {
        if (controlEdition.isValidToEdit(offerDto.getOrder().getStatus())) {
            Offer offer = modelMapper.map(offerDto, Offer.class);
            Date startDate = new SimpleDateFormat("HH:mm").parse(startDateString);
            offer.setStartDate(startDate);
            offer.setIdentificationCode(UUID.randomUUID());
            offer.getExpert().setId(expertService.findExpertByEmail(offer.getExpert().getEmail()).getId());
            offer.getOrder().setId(orderServiceImp.findOrderId(offer.getOrder().getIdentificationCode()));
            offer.setStatus(OfferStatus.WAITING_FOR_ACCEPT);
            Offer save = offerDao.save(offer);
            offer.getOrder().setStatus(OrderStatus.WAITING_FOR_CHOOSING_EXPERT);
            orderServiceImp.updateOrder(offer.getOrder());

            return modelMapper.map(save,OfferDto.class);
        } else
            throw new RuntimeException("saving offer fail");
    }

    @Override
    public void editStartDateOffer(OfferDto offerDto, String startDateString) throws ParseException {
        Date startDate = new SimpleDateFormat("HH:mm").parse(startDateString);
        offerDto.setStartDate(startDate);
        updateOffer(offerDto);
    }

    @Override
    public void editOfferSuggestedPrice(OfferDto offerDto, double suggestedPrice) {
        offerDto.setSuggestedPrice(suggestedPrice);
        updateOffer(offerDto);
    }

    @Override
    public void editSuggestedDurationOfService(OfferDto offerDto, float suggestedDurationOfService) {
        offerDto.setSuggestedDurationOfService(suggestedDurationOfService);
        updateOffer(offerDto);
    }

    @Override
    public List<Offer> findOffersOfOrder(OrderDto orderDto) {
        Order order = modelMapper.map(orderDto, Order.class);
        long orderId = orderServiceImp.findOrderId(order.getIdentificationCode());
        order.setId(orderId);
        List<Offer> offers = offerDao.findByOrder(order);
        if (!offers.isEmpty()) {
            return offers;
        } else
            throw new OfferNotFound();
    }

    @Override
    public void deleteOfferFromOrder(OfferDto offerDto) {
        if (controlEdition.isValidToEdit(offerDto.getOrder().getStatus())) ;
        {
            Offer offer = modelMapper.map(offerDto, Offer.class);
            long offerId = findOfferId(offer.getIdentificationCode());
            offer.setId(offerId);
            offerDao.delete(offer);
        }
    }

    @Override
    public void acceptOfferForOrder(OfferDto offerDto) {
        Offer offer = modelMapper.map(offerDto, Offer.class);
        long offerId = findOfferId(offer.getIdentificationCode());
        offer.setId(offerId);
        offer.setStatus(OfferStatus.ACCEPT);
        offerDao.save(offer);
        offer.getOrder().setExpert(offer.getExpert());
        offer.getOrder().setStatus(OrderStatus.WAITING_FOR_COMING_EXPERT_TO_YOUR_PLACE);
        orderServiceImp.updateOrder(offer.getOrder());
    }

    @Override
    public List<Offer> findByOfferSortedByPriceAndExpertRate(Order orderDto) {
        Order order = modelMapper.map(orderDto, Order.class);
        long orderId = orderServiceImp.findOrderId(order.getIdentificationCode());
        order.setId(orderId);
        List<Offer> offers = offerDao.findByOrderOrderBySuggestedPriceAsc(order);
        offers.sort(Comparator.comparing(Offer::getExpert));
        return offers;
    }

    @Override
    public void updateOffer(OfferDto offerDto) {
        Offer offer = modelMapper.map(offerDto, Offer.class);
        if (controlEdition.isValidToEdit(offer.getOrder().getStatus())) {
            long offerId = findOfferId(offer.getIdentificationCode());
            offer.setId(offerId);
            offerDao.save(offer);
        } else {
            throw new EditionDenied();
        }
    }

    @Override
    public Offer findOrderByIdentificationCode(UUID identificationCode) {
        Optional<Offer> offer = offerDao.findByIdentificationCode(identificationCode);
        if (offer.isPresent())
            return offer.get();
        else
            throw new OfferNotFound();
    }

    @Override
    public long findOfferId(UUID identificationCode) {
        Offer offer = findOrderByIdentificationCode(identificationCode);
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
}



