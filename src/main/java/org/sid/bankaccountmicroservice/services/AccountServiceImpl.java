package org.sid.bankaccountmicroservice.services;

import lombok.RequiredArgsConstructor;
import org.sid.bankaccountmicroservice.dto.BankAccountRequestDTO;
import org.sid.bankaccountmicroservice.dto.BankAccountResponseDTO;
import org.sid.bankaccountmicroservice.entities.BankAccount;
import org.sid.bankaccountmicroservice.mappers.AccountMapper;
import org.sid.bankaccountmicroservice.repositories.BankAccountRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final BankAccountRepository bankAccountRepository;
    private final AccountMapper accountMapper;
    @Override
    public BankAccountResponseDTO addAccount(BankAccountRequestDTO bankAccountDTO) {
        BankAccount bankAccount = accountMapper.toBankAccount(bankAccountDTO);
        BankAccount savedBankAccount = bankAccountRepository.save(bankAccount);

        return accountMapper.fromBankAccount(savedBankAccount);
    }

    @Override
    public List<BankAccountResponseDTO> getAllAccounts() {
        return bankAccountRepository.findAll()
                .stream()
                .map(accountMapper::fromBankAccount)
                .collect(Collectors.toList());
    }

    @Override
    public BankAccountResponseDTO getAccountById(String id) {
        return accountMapper.fromBankAccount(bankAccountRepository.findById(id).orElseThrow(()-> new RuntimeException("Account id not found")));
    }

    @Override
    public BankAccountResponseDTO updateAccount(String id, BankAccountRequestDTO bankAccountDTO) {
        BankAccount account = bankAccountRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Account not found"));

        if (bankAccountDTO.getBalance() != null) account.setBalance(bankAccountDTO.getBalance());
        if (bankAccountDTO.getCurrency() != null) account.setCurrency(bankAccountDTO.getCurrency());
        if (bankAccountDTO.getType() != null) account.setType(bankAccountDTO.getType());
        account.setCreatedAt(new Date());

        BankAccount updated = bankAccountRepository.save(account);
        return accountMapper.fromBankAccount(updated);
    }

    @Override
    public void deleteAccount(String id) {
        bankAccountRepository.deleteById(id);
    }
}
