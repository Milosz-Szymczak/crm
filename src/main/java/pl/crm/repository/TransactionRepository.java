package pl.crm.repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;
import pl.crm.controller.ManagementController;
import pl.crm.model.transaction.Transaction;

import java.util.List;

@Repository
public interface TransactionRepository extends ListCrudRepository<Transaction, Long> {

}
