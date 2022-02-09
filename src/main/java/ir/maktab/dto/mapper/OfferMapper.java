package ir.maktab.dto.mapper;

import ir.maktab.data.models.entities.Offer;
import ir.maktab.data.models.entities.roles.Expert;
import ir.maktab.dto.modelDtos.OfferDto;
import ir.maktab.dto.modelDtos.roles.ExpertDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Configuration
public class OfferMapper {
    private final ModelMapper modelMapper;
    private final OrderMapper orderMapper;

    @Autowired
    public OfferMapper(ModelMapper modelMapper, OrderMapper orderMapper) {
        this.modelMapper = modelMapper;
        this.orderMapper = orderMapper;
    }

    public Offer toOffer(OfferDto offerDto) throws ParseException {
        Offer offer = Offer.builder()
                .expert(modelMapper.map(offerDto.getExpert(), Expert.class))
                .suggestedDurationOfService(Float.parseFloat(offerDto.getSuggestedDurationOfService()))
                .startDate(new SimpleDateFormat("HH:mm").parse(offerDto.getStartDate()))
                .suggestedPrice(Double.parseDouble(offerDto.getSuggestedPrice()))
                .order(orderMapper.toOrder(offerDto.getOrder()))
                .status(offerDto.getStatus())
                .build();

        if (offerDto.getIdentificationCode() != null)
            offer.setIdentificationCode(offerDto.getIdentificationCode());

        return offer;
    }

    public OfferDto toOfferDto(Offer offer) {
        return OfferDto.builder()
                .identificationCode(offer.getIdentificationCode())
                .order(orderMapper.toOrderDto(offer.getOrder()))
                .expert(modelMapper.map(offer.getExpert(), ExpertDto.class))
                .suggestedPrice(Double.toString(offer.getSuggestedPrice()))
                .startDate(offer.getStartDate().toString())
                .status(offer.getStatus())
                .suggestedDurationOfService(Float.toString(offer.getSuggestedDurationOfService()))
                .build();
    }
}
