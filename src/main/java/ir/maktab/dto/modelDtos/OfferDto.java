package ir.maktab.dto.modelDtos;

import ir.maktab.dto.modelDtos.roles.ExpertDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OfferDto {

    private long id ;
    private ExpertDto expert ;
    private double suggestedPrice ;
    private float suggestedDurationOfService ;
    private Date startDate ;
    private OrderDto order;
}
