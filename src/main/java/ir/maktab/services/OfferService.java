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
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class OfferService {
    private final OrderService orderService;
    private final ExpertServiceImp expertService;
    private final OfferDao offerDao;
    private final ModelMapper modelMapper;
    private final ControlEdition controlEdition;

    @Autowired
    public OfferService(OrderService orderService,
                        ExpertServiceImp expertService, OfferDao offerDao,
                        ModelMapper modelMapper, ControlEdition controlEdition) {
        this.orderService = orderService;
        this.expertService = expertService;
        this.offerDao = offerDao;
        this.modelMapper = modelMapper;
        this.controlEdition = controlEdition;
    }

    public void saveOffer(OfferDto offerDto, String startDateString) throws ParseException {
        if (controlEdition.isValidToEdit(offerDto.getOrder().getStatus())) {
            Offer offer = modelMapper.map(offerDto, Offer.class);
            Date startDate = new SimpleDateFormat("HH:mm").parse(startDateString);
            offer.setStartDate(startDate);
            offer.setIdentificationCode(UUID.randomUUID());
            offer.getExpert().setId(expertService.findExpertByEmail(offer.getExpert().getEmail()).getId());
            offer.getOrder().setId(orderService.findOrderId(offer.getOrder().getIdentificationCode()));
            offer.setStatus(OfferStatus.WAITING_FOR_ACCEPT);
            offerDao.save(offer);

            offer.getOrder().setStatus(OrderStatus.WAITING_FOR_CHOOSING_EXPERT);
            orderService.updateOrder(offer.getOrder());
        } else
            throw new RuntimeException("saving offer fail");
    }

    public void editStartDateOffer(OfferDto offerDto, String startDateString) throws ParseException {
        Date startDate = new SimpleDateFormat("HH:mm").parse(startDateString);
        offerDto.setStartDate(startDate);
        updateOffer(offerDto);
    }

    public void editOfferSuggestedPrice(OfferDto offerDto, double suggestedPrice) {
        offerDto.setSuggestedPrice(suggestedPrice);
        updateOffer(offerDto);
    }

    public void editSuggestedDurationOfService(OfferDto offerDto, float suggestedDurationOfService) {
        offerDto.setSuggestedDurationOfService(suggestedDurationOfService);
        updateOffer(offerDto);
    }

    public List<Offer> findOffersOfOrder(OrderDto orderDto) {
        Order order = modelMapper.map(orderDto, Order.class);
        long orderId = orderService.findOrderId(order.getIdentificationCode());
        order.setId(orderId);
        List<Offer> offers = offerDao.findByOrder(order);
        if (!offers.isEmpty()) {
            return offers;
        } else
            throw new OfferNotFound();
    }

    public void deleteOfferFromOrder(OfferDto offerDto) {
        if (controlEdition.isValidToEdit(offerDto.getOrder().getStatus())) ;
        {
            Offer offer = modelMapper.map(offerDto, Offer.class);
            long offerId = findOfferId(offer.getIdentificationCode());
            offer.setId(offerId);
            offerDao.delete(offer);
        }
    }

    public void acceptOfferForOrder(OfferDto offerDto) {
        Offer offer = modelMapper.map(offerDto, Offer.class);
        long offerId = findOfferId(offer.getIdentificationCode());
        offer.setId(offerId);
        offer.setStatus(OfferStatus.ACCEPT);
        offerDao.save(offer);
        offer.getOrder().setExpert(offer.getExpert());
        offer.getOrder().setStatus(OrderStatus.WAITING_FOR_COMING_EXPERT_TO_YOUR_PLACE);
        orderService.updateOrder(offer.getOrder());
    }

    public List<Offer> findByOfferSortedByPriceAndExpertRate(Order orderDto) {
        Order order = modelMapper.map(orderDto, Order.class);
        long orderId = orderService.findOrderId(order.getIdentificationCode());
        order.setId(orderId);
        List<Offer> offers = offerDao.findByOrderOrderBySuggestedPriceAsc(order);
        offers.sort(Comparator.comparing(Offer::getExpert));
        return offers;
    }

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

    public Offer findOrderByIdentificationCode(UUID identificationCode) {
        Optional<Offer> offer = offerDao.findByIdentificationCode(identificationCode);
        if (offer.isPresent())
            return offer.get();
        else
            throw new OfferNotFound();
    }

    public long findOfferId(UUID identificationCode) {
        Offer offer = findOrderByIdentificationCode(identificationCode);
        return offer.getId();
    }


    public Offer findOfferById(long id) {
        Optional<Offer> offer = offerDao.findById(id);
        if (offer.isPresent())
            return offer.get();
        else
            throw new OfferNotFound();
    }
}



