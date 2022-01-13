package ir.maktab.services;

import ir.maktab.dao.OfferDao;
import ir.maktab.dao.OrderDao;
import ir.maktab.dto.modelDtos.OfferDto;
import ir.maktab.dto.modelDtos.OrderDto;
import ir.maktab.dto.modelDtos.roles.ExpertDto;
import ir.maktab.exceptions.EditionDenied;
import ir.maktab.exceptions.OfferNotFound;
import ir.maktab.models.entities.Offer;
import ir.maktab.models.entities.Order;
import ir.maktab.models.entities.roles.Expert;
import ir.maktab.models.enums.OfferStatus;
import ir.maktab.models.enums.OrderStatus;
import ir.maktab.validation.ControlEdition;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class OfferService {
    private final OrderDao orderDao;
    private final OfferDao offerDao;
    private final ModelMapper modelMapper;
    private final ControlEdition controlEdition;

    @Autowired
    public OfferService(OrderDao orderDao, OfferDao offerDao, ModelMapper modelMapper, ControlEdition controlEdition) {
        this.orderDao = orderDao;
        this.offerDao = offerDao;
        this.modelMapper = modelMapper;
        this.controlEdition = controlEdition;
    }

    public void saveOffer(ExpertDto expertDto, OrderDto orderDto, double suggestedPrice, int suggestedDurationOfService, String startDateString) throws ParseException {
        if (controlEdition.isValidToEdit(orderDto.getStatus())) {
            Expert expert = modelMapper.map(expertDto, Expert.class);
            Order order = modelMapper.map(orderDto, Order.class);
            Offer offer = new Offer();
            offer.setExpert(expert);
            offer.setSuggestedDurationOfService(suggestedDurationOfService);
            offer.setSuggestedPrice(suggestedPrice);
            offer.setOrder(order);
            Date startDate = new SimpleDateFormat("HH:mm").parse(startDateString);
            offer.setStartDate(startDate);
            offer.setStatus(OfferStatus.WAITING_FOR_ACCEPT);
            offerDao.save(offer);

            offer.getOrder().setStatus(OrderStatus.WAITING_FOR_CHOOSING_EXPERT);
            orderDao.save(offer.getOrder());
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
            offerDao.delete(offer);
        }
    }

    public void acceptOfferForOrder(OfferDto offerDto) {
        Offer offer = modelMapper.map(offerDto, Offer.class);
        offer.setStatus(OfferStatus.ACCEPT);
        offerDao.save(offer);
        offer.getOrder().setExpert(offer.getExpert());
        offer.getOrder().setStatus(OrderStatus.WAITING_FOR_COMING_EXPERT_TO_YOUR_PLACE);
        orderDao.save(offer.getOrder());
    }

    public List<Offer> findByOfferSortedByPriceAndExpertRate(Order orderDto) {
        Order order = modelMapper.map(orderDto, Order.class);
        List<Offer> offers = offerDao.findByOrderOrderBySuggestedPriceAsc(order);
        offers.sort(Comparator.comparing(Offer::getExpert));
        return offers;
    }

    public void updateOffer(OfferDto offerDto) {
        Offer offer = findOfferById(offerDto.getId());
        if (controlEdition.isValidToEdit(offer.getOrder().getStatus())) {
            offerDao.save(offer);
        } else
            throw new EditionDenied();
    }

    public OfferDto findOfferDtoById(long id) {
        Optional<Offer> offer = offerDao.findById(id);
        if (offer.isPresent()) {
            OfferDto offerDto = modelMapper.map(offer.get(), OfferDto.class);
            return offerDto;
        } else
            throw new OfferNotFound();
    }

    public Offer findOfferById(long id) {
        Optional<Offer> offer = offerDao.findById(id);
        if (offer.isPresent())
            return offer.get();
        else
            throw new OfferNotFound();
    }
}



