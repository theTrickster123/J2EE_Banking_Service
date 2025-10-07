package org.sid.bankaccountmicroservice.services;


import org.sid.bankaccountmicroservice.dto.BankAccountRequestDTO;
import org.sid.bankaccountmicroservice.dto.BankAccountResponseDTO;

import java.util.List;


public interface AccountService {

    BankAccountResponseDTO addAccount(BankAccountRequestDTO bankAccountDTO );
    List<BankAccountResponseDTO> getAllAccounts();
    BankAccountResponseDTO getAccountById(String id);
    BankAccountResponseDTO updateAccount(String id, BankAccountRequestDTO bankAccountDTO);
    void deleteAccount(String id);

}
