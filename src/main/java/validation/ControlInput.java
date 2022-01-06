package validation;

import exceptions.*;
import models.entities.Service;

import java.io.File;

public class ControlInput {
    private static ControlInput controlInput;
    private static int maxPhotoSize =300 * 1024;

    public ControlInput() {
    }

    public static ControlInput instance() {
        if (controlInput == null)
            controlInput = new ControlInput();
        return controlInput;
    }

    public boolean isValidName(String name) {
        boolean isValid = false;
        try {
            if (!(name.matches("[a-zA-Z]*") && name.length() > 2)) {
                throw new InvalidName();
            } else {
                isValid = true;
            }
        } catch (InvalidName invalidName) {
            System.out.println(invalidName.getMessage());
        }
        return isValid;
    }

    public boolean isValidPassword(String password) {
        boolean isValid = false;
        try {
            if (password.matches("^(?=.*[0-9])(?=.*[a-z]).{8,32}$"))
                isValid = true;
            else {
                throw new InvalidPassword();
            }
        } catch (InvalidPassword invalidPassword) {
            System.out.println(invalidPassword.getMessage());
        }
        return isValid;
    }

    public boolean isValidEmail(String email) {
        try {

            if (email.matches("^((\"[\\w-\\s]+\")|([\\w-]+(?:\\.[\\w-]+)*)|(\"[\\w-\\s]+\")([\\w-]+(?:\\.[\\w-]+)*))(@((?:[\\w-]+\\.)*\\w[\\w-]{0,66})\\.([a-z]{2,6}(?:\\.[a-z]{2})?)$)|(@\\[?((25[0-5]\\.|2[0-4][0-9]\\.|1[0-9]{2}\\.|[0-9]{1,2}\\.))((25[0-5]|2[0-4][0-9]|1[0-9]{2}|[0-9]{1,2})\\.){2}(25[0-5]|2[0-4][0-9]|1[0-9]{2}|[0-9]{1,2})\\]?$)")) {
                return true;
            }

            throw new InvalidEmail();
        } catch (InvalidEmail i) {
            i.getMessage();
        }
        return false;
    }

    public boolean isValidPhoto(File file){
        boolean isValid = false;
        try {
            if (file.length()<maxPhotoSize)
                isValid = true;
            else {
                throw new TooLargePhotoSize();
            }
        } catch (TooLargePhotoSize invalidPassword) {
            System.out.println(invalidPassword.getMessage());
        }
        return isValid;
    }

    public boolean isValidSuggestedPrice(Service service , double suggestedPrice){
        if( suggestedPrice >= service.getBaseCost()){
            return true ;
        }else {
            throw new InvalidSuggestedPrice();
        }
    }

}





