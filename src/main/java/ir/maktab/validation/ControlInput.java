package ir.maktab.validation;

import ir.maktab.dto.modelDtos.roles.CustomerDto;
import ir.maktab.dto.modelDtos.roles.ExpertDto;
import ir.maktab.exceptions.*;
import ir.maktab.data.models.entities.SubService;
import org.springframework.stereotype.Component;

import java.io.File;

@Component
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
        int maxPhotoSize = 300000;
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

    public boolean isValidCustomerDtoInfo(CustomerDto customerDto) {
        return isValidName(customerDto.getFirstName())
                && isValidName(customerDto.getLastName())
                && isValidEmail(customerDto.getEmail())
                && isValidPassword(customerDto.getPassword());
    }

    public boolean isValidExpertDtoInfo(ExpertDto expertDto) {
        return isValidName(expertDto.getFirstName())
                && isValidName(expertDto.getLastName())
                && isValidEmail(expertDto.getEmail());
//                && isValidPhoto(file)
//                && isValidPassword(password);
    }
}





