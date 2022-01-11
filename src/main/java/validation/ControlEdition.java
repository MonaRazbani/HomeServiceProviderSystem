package validation;

import exceptions.EditionDenied;
import models.enums.OrderStatus;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ControlEdition {

    public boolean isValidToEdit(OrderStatus status) {
        if (status.equals(OrderStatus.STARTED) || status.equals(OrderStatus.WAITING_FOR_CHOOSING_EXPERT)) {
            return true;
        } else throw new EditionDenied();
    }
}
