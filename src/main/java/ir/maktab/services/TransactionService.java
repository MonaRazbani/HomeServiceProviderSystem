package ir.maktab.services;


import ir.maktab.data.models.entities.Order;
import ir.maktab.dto.modelDtos.TransactionDto;

import java.util.UUID;

public interface TransactionService {

    TransactionDto save (TransactionDto transactionDto, Order order);

    TransactionDto findTransactionByIdentificationCode(UUID identificationCode );


}
