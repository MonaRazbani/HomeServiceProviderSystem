package ir.maktab.dto.filterDto;

import ir.maktab.data.models.enums.OrderStatus;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class OrderCategoryDto extends BasePaginationDto{
    @DateTimeFormat(pattern = "yyyy/mm/dd")
    private Date startDate;

    @DateTimeFormat(pattern = "yyyy/mm/dd")
    private Date endDate;

    private String serviceCategoryName ;

    private String subServiceName ;

    private OrderStatus orderStatus ;

}
