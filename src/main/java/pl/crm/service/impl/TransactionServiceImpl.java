package pl.crm.service.impl;

import pl.crm.model.transaction.Transaction;
import pl.crm.model.transaction.TransactionType;
import pl.crm.model.users.User;
import pl.crm.repository.TransactionRepository;
import org.springframework.stereotype.Service;
import pl.crm.service.TransactionService;

import java.util.*;

@Service
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;

    public TransactionServiceImpl(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @Override
    public List<Transaction> getTransactions() {
        return transactionRepository.findAll();
    }

    @Override
    public void save(Transaction transaction) {
        transactionRepository.save(transaction);
    }

    @Override
    public List<TransactionType> getTransactionsType() {
        TransactionType[] transactionTypes = TransactionType.values();
        return Arrays.stream(transactionTypes).toList();
    }

    public Transaction getTransactionById(Long transactionId) {
        return transactionRepository.findById(transactionId)
                .orElseThrow(() -> new NoSuchElementException("Transaction with ID " + transactionId + " not found"));
    }

    public void updateTransaction(Transaction transaction) {
        Long transactionId = transaction.getOrderId();
        Transaction existingTransaction = transactionRepository.findById(transactionId)
                .orElseThrow(() -> new IllegalStateException("Transaction with ID " + transactionId + " does not exist."));

        existingTransaction.setQuantity(transaction.getQuantity());
        existingTransaction.setNotes(transaction.getNotes());

        transactionRepository.save(existingTransaction);
    }
}
