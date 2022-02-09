package ir.maktab.services;

import ir.maktab.data.models.entities.Offer;
import ir.maktab.data.models.entities.Order;
import ir.maktab.data.models.enums.OfferStatus;
import ir.maktab.data.models.enums.OrderStatus;
import ir.maktab.dto.modelDtos.OfferDto;
import ir.maktab.dto.modelDtos.OrderDto;
import ir.maktab.dto.modelDtos.roles.ExpertDto;
import ir.maktab.exceptions.EditionDenied;
import ir.maktab.exceptions.OfferNotFound;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public interface OfferService {
     OfferDto saveOffer(OfferDto offerDto) throws ParseException;

    OfferDto editStartDateOffer(OfferDto offerDto, String startDate) throws ParseException;

    OfferDto editOfferSuggestedPrice(OfferDto offerDto, String suggestedPrice) throws ParseException;

    OfferDto editSuggestedDurationOfService(OfferDto offerDto, String suggestedDurationOfService)throws ParseException;

    OfferDto changeOrderStatus (OfferDto offerDto ,String orderStatus) throws ParseException;

    List<OfferDto> findOfferDtosOfOrder(OrderDto orderDto);

    OfferDto findAcceptedOfferOfOrder (OrderDto orderDto);

    void deleteOfferFromOrder(OfferDto offerDto) throws ParseException;

    void acceptOfferForOrder(Offer offer) throws ParseException;

    List<OfferDto> findByOfferSortedByPriceAndExpertRate(OrderDto orderDto);

    Offer updateOffer(OfferDto offerDto) throws ParseException;

    Offer findOfferByIdentificationCode(UUID identificationCode);

    OfferDto findOfferDtoByIdentificationCode(UUID identificationCode);

    long findOfferId(UUID identificationCode);

    Offer findOfferById(long id) ;

    List<OfferDto> findExpertOffer(ExpertDto expertDto);
}

