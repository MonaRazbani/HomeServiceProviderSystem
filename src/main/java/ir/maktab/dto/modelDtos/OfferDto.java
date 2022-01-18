package ir.maktab.dto.modelDtos;

import ir.maktab.dto.modelDtos.roles.ExpertDto;
import ir.maktab.models.enums.OfferStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.util.Date;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OfferDto {

    private UUID identificationCode;
    private ExpertDto expert ;
    private double suggestedPrice ;
    private float suggestedDurationOfService ;
    private Date startDate ;
    private OrderDto order;
    private OfferStatus status;
}
