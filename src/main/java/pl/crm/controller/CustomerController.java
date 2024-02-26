package pl.crm.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.crm.model.Customer;

import pl.crm.service.CustomerService;

import java.util.List;

@Controller
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @GetMapping("/form-add-customer")
    public String addNewCustomer(Model model) {
        model.addAttribute("customer", new Customer());
        return "form-add-customer";
    }

    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @PostMapping("/form-add-customer")
    public String saveCustomer(@ModelAttribute("customer") Customer customer) {
        customerService.saveCustomer(customer);
        return "redirect:/customers";
    }

    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @GetMapping("/customers")
    public String customer(Model model) {
        List<Customer> customers = customerService.getCustomer();
        model.addAttribute("customers",customers);
        return "customers";
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GetMapping("/form-update-customer/{customerId}")
    public String showUpdateForm(@PathVariable("customerId") Long customerId, Model model) {
        Customer customer = customerService.getCustomerById(customerId);
        model.addAttribute("customer", customer);
        return "form-update-customer";
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @PostMapping("/update-customer")
    public String updateCustomer(@ModelAttribute("customer") Customer customer) {
        customerService.updateCustomer(customer);
        return "redirect:/customers";
    }

}
