package pl.crm.service;

import org.springframework.stereotype.Service;
import pl.crm.model.Customer;
import pl.crm.repository.CustomerRepository;

import java.util.List;

@Service
public interface CustomerService {


    void saveCustomer(Customer customer);

    List<Customer> getCustomer();

    Customer getCustomerById(Long customerId);

    void updateCustomer(Customer customer);
}
