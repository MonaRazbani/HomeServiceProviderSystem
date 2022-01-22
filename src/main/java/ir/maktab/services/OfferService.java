package ir.maktab.services;

import ir.maktab.data.models.entities.Offer;
import ir.maktab.data.models.entities.Order;
import ir.maktab.data.models.enums.OfferStatus;
import ir.maktab.data.models.enums.OrderStatus;
import ir.maktab.dto.modelDtos.OfferDto;
import ir.maktab.dto.modelDtos.OrderDto;
import ir.maktab.exceptions.EditionDenied;
import ir.maktab.exceptions.OfferNotFound;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public interface OfferService {
     OfferDto saveOffer(OfferDto offerDto, String startDateString) throws ParseException;

    void editStartDateOffer(OfferDto offerDto, String startDateString) throws ParseException;

    void editOfferSuggestedPrice(OfferDto offerDto, double suggestedPrice);

    void editSuggestedDurationOfService(OfferDto offerDto, float suggestedDurationOfService)throws ParseException;

    List<Offer> findOffersOfOrder(OrderDto orderDto);

    void deleteOfferFromOrder(OfferDto offerDto);

    void acceptOfferForOrder(OfferDto offerDto);

    List<Offer> findByOfferSortedByPriceAndExpertRate(Order orderDto);

    void updateOffer(OfferDto offerDto);

    Offer findOrderByIdentificationCode(UUID identificationCode);

    long findOfferId(UUID identificationCode);

    Offer findOfferById(long id) ;
}

