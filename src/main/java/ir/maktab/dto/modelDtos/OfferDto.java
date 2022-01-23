package ir.maktab.dto.modelDtos;

import ir.maktab.dto.modelDtos.roles.ExpertDto;
import ir.maktab.data.models.enums.OfferStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OfferDto {

    private UUID identificationCode;

    private ExpertDto expert ;

    @NotNull
    @NumberFormat
    private double suggestedPrice ;

    @NotNull
    @NumberFormat
    private float suggestedDurationOfService ;
    @DateTimeFormat(pattern = "hh:mm")
    @NotNull
    private Date startDate ;

    private OrderDto order;
    private OfferStatus status;
}
