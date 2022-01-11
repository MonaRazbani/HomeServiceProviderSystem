package validation;

import exceptions.InvalidRate;
import exceptions.InvalidSuggestedPrice;
import models.entities.SubService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;

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
    @Test
    void controlInput_CallIsValidPhoto_ResponseFalse() {
        File file = new File("C:\\Users\\Asus\\Desktop\\image\\expert1.png");
        boolean result = controlInput.isValidPhoto(file);
        Assertions.assertTrue(result);
    }

    @Test
    void controlInput_CallIsValidPhoto_ResponseTrue() {
        File file = new File("C:\\Users\\Asus\\Desktop\\image\\1.jpg");
        boolean result = controlInput.isValidPhoto(file);
        Assertions.assertTrue(result);
    }
    @Test
    void controlInput_CallIsValidSuggestedPrice_ReturnException() {
        SubService subService = new SubService();
        subService.setBaseCost(1000);
        InvalidSuggestedPrice result = Assertions.assertThrows(InvalidSuggestedPrice.class,()-> controlInput.isValidSuggestedPrice(subService,900));
        Assertions.assertEquals("invalid SuggestedPrice , SuggestedPrice must be more than subService base cost ",result.getMessage());
    }
    @Test
    void controlInput_CallIsValidValidSuggestedPrice_ResponseTrue() {
        SubService subService = new SubService();
        subService.setBaseCost(1000);
        boolean result = controlInput.isValidSuggestedPrice(subService,1200);
        Assertions.assertTrue(result);
    }

    @Test
    void controlInput_CallIsValidRate_ReturnException() {

        InvalidRate result = Assertions.assertThrows(InvalidRate.class,()-> controlInput.isValidRate(10));
        Assertions.assertEquals(" invalid rate , rate must between 5-0",result.getMessage());
    }
    @Test
    void controlInput_CallIsValidValidRate_ResponseTrue() {

        boolean result = controlInput.isValidRate(5);
        Assertions.assertTrue(result);
    }





}