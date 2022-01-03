package models.enums;

import models.entities.roles.Expert;

public enum OrderStatus {
    WAITING_FOR_CHOOSING_EXPERT,
    WAITING_FOR_COMING_EXPERT_TO_YOUR_PLACE,
    STARTED,
    DONE,
    PAID
}
