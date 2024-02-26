package pl.crm.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import pl.crm.model.Customer;
import pl.crm.model.Product;
import pl.crm.model.transaction.Transaction;
import pl.crm.model.transaction.TransactionType;
import pl.crm.model.users.User;
import pl.crm.service.CustomerService;
import pl.crm.service.ProductService;
import pl.crm.service.TransactionService;
import pl.crm.security.service.UserService;

import java.util.HashMap;
import java.util.List;

@Controller
public class TransactionController {

    private final TransactionService transactionService;
    private final CustomerService customerService;
    private final UserService userService;
    private final ProductService productService;

    public TransactionController(TransactionService transactionService, CustomerService customerService, UserService userService, ProductService productService) {
        this.transactionService = transactionService;
        this.customerService = customerService;
        this.userService = userService;
        this.productService = productService;
    }

    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @GetMapping("/transaction")
    public String transaction(Model model) {
        List<Transaction> transactions = transactionService.getTransactions();

        HashMap<Transaction,Double> transactionPrice = new HashMap<>();
        for (Transaction transaction : transactions) {
            transactionPrice.put(transaction,transaction.getProduct().getPrice() * transaction.getQuantity());
        }

        model.addAttribute("transaction", transactions);
        model.addAttribute("transactionPrice", transactionPrice);
        return "transaction";
    }

    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @GetMapping("/form-add-transaction")
    public String saveTransaction(Model model) {
        List<Customer> customers = customerService.getCustomer();

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String user = authentication.getName();
        User currentUser = userService.findByUsername(user);

        List<Product> products = productService.getProducts();
        List<TransactionType> transactionsType = transactionService.getTransactionsType();

        model.addAttribute("transaction", new Transaction());
        model.addAttribute("transactionType", transactionsType);
        model.addAttribute("customer", customers);
        model.addAttribute("user", currentUser);
        model.addAttribute("product", products);
        return "form-add-transaction";
    }

    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @PostMapping("/form-add-transaction")
    public String saveProduct(@ModelAttribute("transaction") Transaction transaction) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String user = authentication.getName();
        User currentUser = userService.findByUsername(user);
        transaction.setUser(currentUser);
        transactionService.save(transaction);
        return "redirect:/transaction";
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GetMapping("/form-update-transaction/{transactionId}")
    public String showUpdateForm(@PathVariable("transactionId") Long transactionId, Model model) {
        Transaction transaction = transactionService.getTransactionById(transactionId);
        model.addAttribute("transaction", transaction);

        return "form-update-transaction";
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @PostMapping("/update-transaction")
    public String updateTransaction(@ModelAttribute("transaction") Transaction transaction) {
        transactionService.updateTransaction(transaction);
        return "redirect:/transaction";
    }
}
