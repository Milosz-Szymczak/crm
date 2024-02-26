package pl.crm.service.impl;

import pl.crm.model.Customer;
import pl.crm.repository.CustomerRepository;
import pl.crm.service.CustomerService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public void saveCustomer(Customer customer) {
        customerRepository.save(customer);
    }

    @Override
    public List<Customer> getCustomer() {
        return customerRepository.findAll();
    }

    @Override
    public Customer getCustomerById(Long customerId) {
        return customerRepository.findById(customerId)
                .orElseThrow(() -> new NoSuchElementException("Customer with ID " + customerId + " not found"));
    }

    @Override
    public void updateCustomer(Customer customer) {
        Long customerId = customer.getCustomerId();
        Customer existingCustomer = customerRepository.findById(customerId)
                .orElseThrow(() -> new IllegalStateException("Customer with ID " + customerId + " does not exist."));

        existingCustomer.setFirstName(customer.getFirstName());
        existingCustomer.setLastName(customer.getLastName());
        existingCustomer.setEmail(customer.getEmail());
        existingCustomer.setPhoneNumber(customer.getPhoneNumber());
        existingCustomer.setAddress(customer.getAddress());
        existingCustomer.setNip(customer.getNip());

        customerRepository.save(existingCustomer);
    }
}
