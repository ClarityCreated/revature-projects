package org.example.service;

import org.example.entity.Account;
import org.example.entity.Transaction;
import org.example.web.TransferBalanceRequest;

import java.util.List;

public interface TransferService {




    List<Account> findAll();


    Account save(Account account);
        Transaction transfer(
                TransferBalanceRequest transferBalanceRequest
        );

    Account findByAccountNumber(String accountNumber);
}
