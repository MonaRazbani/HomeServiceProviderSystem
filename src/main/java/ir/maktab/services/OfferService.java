package ir.maktab.services;

import ir.maktab.dao.OrderDao;
import ir.maktab.dao.OfferDao;
import ir.maktab.dto.mappingMethod.MapperObject;
import ir.maktab.dto.modelDtos.OfferDto;
import ir.maktab.dto.modelDtos.OrderDto;
import ir.maktab.dto.modelDtos.roles.ExpertDto;
import ir.maktab.exceptions.OfferNotFound;
import ir.maktab.models.entities.Order;
import ir.maktab.models.entities.Offer;
import ir.maktab.models.entities.roles.Expert;
import ir.maktab.models.enums.OfferStatus;
import ir.maktab.models.enums.OrderStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ir.maktab.validation.ControlEdition;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class OfferService {
    private final OrderDao orderDao;
    private final OfferDao offerDao;
    private final MapperObject mapperObject;
    private final ControlEdition controlEdition;

    @Autowired
    public OfferService(OrderDao orderDao, OfferDao offerDao, MapperObject mapperObject, ControlEdition controlEdition) {
        this.orderDao = orderDao;
        this.offerDao = offerDao;
        this.mapperObject = mapperObject;
        this.controlEdition = controlEdition;
    }

    public void saveOffer(ExpertDto expertDto, OrderDto orderDto, double suggestedPrice, int suggestedDurationOfService, String startDateString) throws ParseException {
        if (controlEdition.isValidToEdit(orderDto.getStatus())) {
            Expert expert = mapperObject.expertDtoMapToExpert(expertDto);
            Order order = mapperObject.orderDtoMapToOrder(orderDto);
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
        }else
            throw new RuntimeException("saving offer fail");
    }

    public void editStartDateOffer(OfferDto offerDto, String startDateString) throws ParseException {
                if (controlEdition.isValidToEdit(offerDto.getOrder().getStatus())) {
                    Date startDate = new SimpleDateFormat("HH:mm").parse(startDateString);
                    Offer offer = mapperObject.offerDtoMapToOffer(offerDto);
                    offer.setStartDate(startDate);
                    offerDao.save(offer);
            } else
                throw new RuntimeException("edit StartDate Offer");
    }

    public void editOfferSuggestedPrice(OfferDto offerDto, double suggestedPrice) {
        if (controlEdition.isValidToEdit(offerDto.getOrder().getStatus())) {
            Offer offer = mapperObject.offerDtoMapToOffer(offerDto);
            offer.setSuggestedPrice(suggestedPrice);
            offerDao.save(offer);
        } else
            throw new RuntimeException("edit suggested Offer");
    }

    public void editSuggestedDurationOfService(OfferDto offerDto, float suggestedDurationOfService) {
        if (controlEdition.isValidToEdit(offerDto.getOrder().getStatus())) {
            Offer offer = mapperObject.offerDtoMapToOffer(offerDto);
            offer.setSuggestedDurationOfService(suggestedDurationOfService);
            offerDao.save(offer);
        } else
            throw new RuntimeException("edit suggested Offer fail");
    }

    public List<Offer> findOffersOfOrder(OrderDto orderDto) {
        Order order = mapperObject.orderDtoMapToOrder(orderDto);
        List<Offer> offers = offerDao.findByOrder(order);
        if (!offers.isEmpty()){
            return offers;
        }else
            throw new OfferNotFound();
    }

    public void deleteOfferFromOrder(OfferDto offerDto) {
        if (controlEdition.isValidToEdit(offerDto.getOrder().getStatus()));
        {
            Offer offer = mapperObject.offerDtoMapToOffer(offerDto);
            offerDao.delete(offer);
        }
    }

    public void acceptOfferForOrder(OfferDto offerDto){
        Offer offer = mapperObject.offerDtoMapToOffer(offerDto);
        offer.setStatus(OfferStatus.ACCEPT);
        offerDao.save(offer);
        offer.getOrder().setExpert(offer.getExpert());
        offer.getOrder().setStatus(OrderStatus.WAITING_FOR_COMING_EXPERT_TO_YOUR_PLACE);
        orderDao.save(offer.getOrder());


    }



}



