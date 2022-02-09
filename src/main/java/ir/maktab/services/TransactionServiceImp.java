package ir.maktab.services;

import ir.maktab.data.dao.TransactionDao;
import ir.maktab.data.models.entities.Transaction;
import ir.maktab.dto.mapper.TransactionMapper;
import ir.maktab.dto.modelDtos.TransactionDto;
import ir.maktab.exceptions.TransactionNotFound;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TransactionServiceImp implements TransactionService {
    private final TransactionDao transactionDao;
    private final TransactionMapper transactionMapper;


    @Override
    public TransactionDto save(TransactionDto transactionDto) {
        Transaction transaction = transactionMapper.toTransaction(transactionDto);
        transaction.setIdentificationCode(UUID.randomUUID());
        Transaction save = transactionDao.save(transaction);
        return transactionMapper.toTransactionDto(save);

    }

    @Override
    public TransactionDto findTransactionByIdentificationCode(UUID identificationCode) {
        Optional<Transaction> transaction = transactionDao.findByIdentificationCode(identificationCode);
        if(transaction.isEmpty())
            throw new TransactionNotFound();
        return transactionMapper.toTransactionDto(transaction.get());
    }
}
