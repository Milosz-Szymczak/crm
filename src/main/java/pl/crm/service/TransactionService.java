package pl.crm.service;

import org.springframework.stereotype.Service;
import pl.crm.model.transaction.Transaction;
import pl.crm.model.transaction.TransactionType;
import pl.crm.model.users.User;

import java.util.List;

@Service
public interface TransactionService {


    List<Transaction> getTransactions();

    void save(Transaction transaction);

    List<TransactionType> getTransactionsType();

    Transaction getTransactionById(Long transactionId);

    void updateTransaction(Transaction transaction);
}
