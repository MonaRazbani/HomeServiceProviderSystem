package validation;

import exceptions.InvalidEmail;
import exceptions.InvalidName;
import exceptions.InvalidPassword;

public class ControlInput {
    private static ControlInput controlInput;

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
        boolean isValid = false;
        try {
            if (!email.matches("^((\"[\\w-\\s]+\")|([\\w-]+(?:\\.[\\w-]+)*)|(\"[\\w-\\s]+\")([\\w-]+(?:\\.[\\w-]+)*))(@((?:[\\w-]+\\.)*\\w[\\w-]))")) {
                throw new InvalidEmail();
            } else {
                isValid = true;
            }
        } catch (InvalidEmail invalidEmail) {
            System.out.println(invalidEmail.getMessage());
        }
        return isValid;
    }
}




