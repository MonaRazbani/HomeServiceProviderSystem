package ir.maktab.dto.mapper;

import ir.maktab.data.models.entities.Transaction;
import ir.maktab.dto.modelDtos.TransactionDto;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class TransactionMapper {
    private final OrderMapper orderMapper;

    public Transaction toTransaction (TransactionDto transactionDto){
        Transaction transaction =Transaction.builder()
                .order(orderMapper.toOrder(transactionDto.getOrder()))
                .price(transactionDto.getPrice())
                .status(transactionDto.getStatus())
                .type(transactionDto.getType())
                .build();
        if (transactionDto.getIdentificationCode()!=null)
            transaction.setIdentificationCode(transactionDto.getIdentificationCode());
        if (transactionDto.getCreationDate()!=null)
            transaction.setCreationDate(transactionDto.getCreationDate());

        return transaction;
    }

    public TransactionDto toTransactionDto (Transaction transaction){

        return TransactionDto.builder()
                .order(orderMapper.toOrderDto(transaction.getOrder()))
                .price(transaction.getPrice())
                .type(transaction.getType())
                .status(transaction.getStatus())
                .identificationCode(transaction.getIdentificationCode())
                .creationDate(transaction.getCreationDate())
                .build();
    }
}
