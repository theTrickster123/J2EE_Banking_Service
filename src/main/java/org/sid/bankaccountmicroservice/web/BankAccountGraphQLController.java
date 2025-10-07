package org.sid.bankaccountmicroservice.web;


import lombok.RequiredArgsConstructor;
import org.sid.bankaccountmicroservice.dto.BankAccountRequestDTO;
import org.sid.bankaccountmicroservice.dto.BankAccountResponseDTO;
import org.sid.bankaccountmicroservice.entities.BankAccount;
import org.sid.bankaccountmicroservice.entities.Customer;
import org.sid.bankaccountmicroservice.repositories.BankAccountRepository;
import org.sid.bankaccountmicroservice.repositories.CustomerRepository;
import org.sid.bankaccountmicroservice.services.AccountService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class BankAccountGraphQLController {

    private final BankAccountRepository bankAccountRepository;
    private final AccountService accountService;
    private final CustomerRepository customerRepository;

    @QueryMapping
    public List<BankAccount> accountsList() {
        return bankAccountRepository.findAll();
    }

    @QueryMapping
    public BankAccount bankAccountById(@Argument String id) {
        return bankAccountRepository.findById(id).orElseThrow(() -> new RuntimeException("Bank account not found"));
    }

    @MutationMapping
    public BankAccountResponseDTO createBankAccount(@Argument BankAccountRequestDTO bankAccount) {
        return accountService.addAccount(bankAccount);
    }

    @MutationMapping
    public BankAccountResponseDTO updateAccount( @Argument String id, @Argument BankAccountRequestDTO bankAccount) {
        return accountService.updateAccount(id, bankAccount);
    }

    @MutationMapping
    public Boolean deleteAccount(@Argument String id) {
        accountService.deleteAccount(id);
        return true;
    }

    @QueryMapping
    public List<Customer> customers() {
        return customerRepository.findAll();
    }

    @QueryMapping
    public Customer customerById(@Argument Long id) {
        return customerRepository.findById(id).orElseThrow(() -> new RuntimeException("Customer not found"));
    }


}
