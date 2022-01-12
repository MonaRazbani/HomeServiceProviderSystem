package ir.maktab.dto.modelDtos;

import ir.maktab.dto.modelDtos.roles.CustomerDto;
import ir.maktab.dto.modelDtos.roles.ExpertDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ir.maktab.models.enums.OrderStatus;

import java.util.Date;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {

    private long id ;
    private CustomerDto customer;
    private double suggestedPrice ;
    private String explanation ;
    private SubServiceDto subService;
    private Date doneService;
    private AddressDto address;
    private ExpertDto expert;
    private OrderStatus status;
    private CommentDto comment;

}
