package pl.crm.repository;

import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;
import pl.crm.model.Customer;

@Repository
public interface CustomerRepository extends ListCrudRepository<Customer, Long> {
}
