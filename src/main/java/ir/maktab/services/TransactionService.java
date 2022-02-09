package ir.maktab.services;


import ir.maktab.dto.modelDtos.TransactionDto;

import java.util.UUID;

public interface TransactionService {

    TransactionDto save (TransactionDto transactionDto);

    TransactionDto findTransactionByIdentificationCode(UUID identificationCode );


}
