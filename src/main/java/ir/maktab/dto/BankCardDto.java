package ir.maktab.dto;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class BankCardDto {
    @NotNull(message = "You can't leave this empty.")
    @NumberFormat
    @Size(min = 12,max = 12,message = "wrong cardNumber")
    private String cardNumber;

    @Size(min = 3,max = 4,message = "wrong ccv2")
    private String ccv2;

    @DateTimeFormat(pattern = "yy/mm")
    private String cardExpiration;
}
