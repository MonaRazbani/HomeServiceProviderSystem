package dto.modelDtos;

import dto.modelDtos.roles.CustomerDto;
import dto.modelDtos.roles.ExpertDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import models.enums.OrderStatus;

import java.util.Date;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {

    private long id ;
    private CustomerDto customerDto ;
    private double suggestedPrice ;
    private String explanation ;
    private SubServiceDto subServiceDto;
    private Date doneService;
    private AddressDto addressDto;
    private ExpertDto expertDto;
    private OrderStatus status;
    private CommentDto commentDto ;

}
