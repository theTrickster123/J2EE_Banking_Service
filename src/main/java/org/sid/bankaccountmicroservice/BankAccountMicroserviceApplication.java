package org.sid.bankaccountmicroservice;

import org.sid.bankaccountmicroservice.entities.BankAccount;
import org.sid.bankaccountmicroservice.entities.Customer;
import org.sid.bankaccountmicroservice.enums.AccountType;
import org.sid.bankaccountmicroservice.repositories.BankAccountRepository;
import org.sid.bankaccountmicroservice.repositories.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.util.UUID;
import java.util.stream.Stream;

@SpringBootApplication
public class BankAccountMicroserviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(BankAccountMicroserviceApplication.class, args);
    }

    @Bean
    CommandLineRunner start(BankAccountRepository repository, CustomerRepository customerRepository) {
        return args -> {

            Stream.of("Mohammed","Mehdi", "Jmi3a").forEach(name -> {
                Customer customer = Customer.builder()
                        .name(name)
                        .build();
                customerRepository.save(customer);

            });

            customerRepository.findAll().forEach(customer -> {
                for(int i=1; i<=10; i++) {
                    BankAccount bankaccount = BankAccount.builder()
                            .id(UUID.randomUUID().toString())
                            .type(Math.random()>0.5? AccountType.CURRENT_ACCOUNT :AccountType.SAVINGS_ACCOUNT)
                            .balance(Math.random()*90000)
                            .createdAt(new Date())
                            .currency("MAD")
                            .customer(customer)
                            .build();
                    repository.save(bankaccount);

                }
            });

        };
    }
}
