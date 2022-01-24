package ir.maktab.dto.modelDtos;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
public class AdminDto {

    @NotNull(message = "You can't leave this empty.")
    private String username ;

    @NotNull(message = "You can't leave this empty.")
    private String password ;
}
