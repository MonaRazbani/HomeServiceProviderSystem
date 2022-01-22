package ir.maktab.validation;

import ir.maktab.exceptions.EditionDenied;
import ir.maktab.data.models.enums.OrderStatus;
import org.springframework.stereotype.Component;

@Component
public class ControlEdition {

    public boolean isValidToEdit(OrderStatus status) {
        if (status.equals(OrderStatus.STARTED) || status.equals(OrderStatus.WAITING_FOR_CHOOSING_EXPERT)) {
            return true;
        } else throw new EditionDenied();
    }
}
