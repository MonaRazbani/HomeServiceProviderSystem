package ir.maktab.dto.modelDtos;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
public class AdminDto {

    @NotNull(message = "You can't leave this empty.")
    private String username ;

    @NotNull(message = "You can't leave this empty.")
    @Pattern(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$", message = "Invalid email")
    private String password ;
}
