package pl.crm.controller;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.crm.model.Customer;
import pl.crm.model.transaction.Transaction;
import pl.crm.service.ManagementService;
import pl.crm.service.TransactionService;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;


@Controller
public class ManagementController {


    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @GetMapping("/")
    public String showProfitSummary() {
        return "index";
    }

    @Getter
    @Setter
    public static class ProfitSummary {
        private Customer customer;
        private BigDecimal profit;

        public ProfitSummary(Customer customer, BigDecimal profit) {
            this.customer = customer;
            this.profit = profit;
        }

    }
}