package ir.maktab.dto.modelDtos;

import ir.maktab.data.models.enums.TransactionStatus;
import ir.maktab.data.models.enums.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class TransactionDto {

    private UUID identificationCode;

    private Date creationDate;

    private OrderDto order;

    private double price;

    private TransactionStatus status;

    private TransactionType type;
}

