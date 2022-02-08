package ir.maktab.dto.modelDtos;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.NumberFormat;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CommentDto {

    private UUID identificationCode;

    @NotNull(message = "You can't leave this empty.")
    @NumberFormat
    @Min(value = 0,message = "rate between 1-5")
    @Max(value = 5 ,message = "rate between 1-5")
    private Double rate ;
    private String comment ;
    private OrderDto order;
}
