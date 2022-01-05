package validation;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ControlInputTest {
    ControlInput controlInput;

    @BeforeEach
    void init() {
        controlInput = ControlInput.instance();
    }


    @Test
    void controlInput_callsInstanceMethod_ResponseNotNull() {
        ControlInput controlInput = ControlInput.instance();
        Assertions.assertNotNull(controlInput);
    }

    @Test
    void controlInput_CallIsValidName_ResponseFalse() {
        String name = "mona1";
        boolean result = controlInput.isValidName(name);
        Assertions.assertFalse(result);
    }
    @Test
    void controlInput_CallIsValidName_ResponseTrue() {
        String name = "mona";
        boolean result = controlInput.isValidName(name);
        Assertions.assertTrue(result);
    }
    @Test
    void controlInput_CallIsValidPassword_lengthLessThan8_ResponseFalse() {
        String password = "mona";
        boolean result = controlInput.isValidPassword(password);
        Assertions.assertFalse(result);
    }
    @Test
    void controlInput_CallIsValidPassword_WithoutNumber_ResponseFalse() {
        String password = "monamonamona";
        boolean result = controlInput.isValidPassword(password);
        Assertions.assertFalse(result);
    }
    @Test
    void controlInput_CallIsValidPassword_WithoutWord_ResponseFalse() {
        String password = "1234567890";
        boolean result = controlInput.isValidPassword(password);
        Assertions.assertFalse(result);
    }
    @Test
    void controlInput_CallIsValidPassword_ResponseTrue() {
        String password = "mona123456";
        boolean result = controlInput.isValidPassword(password);
        Assertions.assertTrue(result);
    }
    @Test
    void controlInput_CallIsValidEmail_WithoutAssign_ResponseFalse() {
        String email = "mona123456";
        boolean result = controlInput.isValidEmail(email);
        Assertions.assertFalse(result);
    }
    @Test
    void controlInput_CallIsValidEmail_WithoutCom_ResponseFalse() {
        String email = "mona123456@";
        boolean result = controlInput.isValidEmail(email);
        Assertions.assertFalse(result);
    }
    @Test
    void controlInput_CallIsValidEmail_ResponseTrue() {
        String email = "mona_razbani@yahoo.com";
        boolean result = controlInput.isValidEmail(email);
        Assertions.assertTrue(result);
    }

}