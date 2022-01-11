package validation;

import dto.modelDtos.roles.CustomerDto;
import dto.modelDtos.roles.ExpertDto;
import exceptions.*;
import models.entities.SubService;
import org.springframework.context.annotation.Configuration;

import java.io.File;

@Configuration
public class ControlInput {

    public boolean isValidName(String name) {
        if ((name.matches("[a-zA-Z]*") && name.length() > 2))
            return true;
        else
            throw new InvalidName();
    }

    public boolean isValidPassword(String password) {
        if (password.matches("^(?=.*[0-9])(?=.*[a-z]).{8,32}$"))
            return true;
        else
            throw new InvalidPassword();
    }

    public boolean isValidEmail(String email) {
        if (email.matches("^((\"[\\w-\\s]+\")|([\\w-]+(?:\\.[\\w-]+)*)|(\"[\\w-\\s]+\")([\\w-]+(?:\\.[\\w-]+)*))(@((?:[\\w-]+\\.)*\\w[\\w-]{0,66})\\.([a-z]{2,6}(?:\\.[a-z]{2})?)$)|(@\\[?((25[0-5]\\.|2[0-4][0-9]\\.|1[0-9]{2}\\.|[0-9]{1,2}\\.))((25[0-5]|2[0-4][0-9]|1[0-9]{2}|[0-9]{1,2})\\.){2}(25[0-5]|2[0-4][0-9]|1[0-9]{2}|[0-9]{1,2})\\]?$)"))
            return true;
        throw new InvalidEmail();
    }

    public boolean isValidPhoto(File file) {
        int maxPhotoSize = 300;
        if (file.length() < maxPhotoSize)
            return true;
        else
            throw new TooLargePhotoSize();
    }

    public boolean isValidSuggestedPrice(SubService subService, double suggestedPrice) {
        if (suggestedPrice >= subService.getBaseCost())
            return true;
        else
            throw new InvalidSuggestedPrice();
    }

    public boolean isValidRate(double rate) {
        if (rate <= 5 && rate >= 0)
            return true;
        else return false;
    }

    public boolean isValidCustomerDtoInfo(CustomerDto customerDto, String password) {
        return isValidName(customerDto.getFirstName())
                && isValidName(customerDto.getLastName())
                && isValidEmail(customerDto.getEmail())
                && isValidPassword(password);
    }

    public boolean isValidExpertDtoInfo(ExpertDto expertDto, String password, File file) {
        return isValidName(expertDto.getFirstName())
                && isValidName(expertDto.getLastName())
                && isValidEmail(expertDto.getEmail())
                && isValidPhoto(file)
                && isValidPassword(password);
    }
}





