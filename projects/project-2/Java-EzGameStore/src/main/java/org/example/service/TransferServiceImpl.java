package org.example.service;

import org.example.entity.Account;
import org.example.entity.Transaction;
import org.example.repository.AccountRepository;
import org.example.repository.TransactionRepository;
import org.example.web.TransferBalanceRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


@Service
public class TransferServiceImpl implements TransferService {


    private AccountRepository accountRepository;
    private TransactionRepository transactionRepository;
    private TransferBalanceRequest transferBalanceRequest;

    @Autowired
    public TransferServiceImpl(@Qualifier("jpa") AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }
    public Account save(Account account){
        accountRepository.updateAccount(account);
        return accountRepository.loadAccount(account.getAccountNumber());
    }
    @Override
    public Account findByAccountNumber(String accountNumber) {
        Account account = accountRepository.loadAccount(accountNumber);
        return account;

    }

        @Override
    @Transactional(transactionManager = "transactionManager")
    public Transaction transfer(
            TransferBalanceRequest transferBalanceRequest
    ) {
        String fromAccountNumber = transferBalanceRequest.getFromAccountNumber();
        String toAccountNumber = transferBalanceRequest.getToAccountNumber();
        double amount = transferBalanceRequest.getAmount();
        Account fromAccount = accountRepository.loadAccount(
                fromAccountNumber
        );
        Account toAccount = accountRepository.loadAccount(toAccountNumber);

            fromAccount.setCurrentBalance(fromAccount.getCurrentBalance()-(amount));
            accountRepository.updateAccount(fromAccount);
            toAccount.setCurrentBalance(toAccount.getCurrentBalance()+(amount));
            accountRepository.updateAccount(toAccount);
            Transaction transaction = transactionRepository.save(new Transaction(0,fromAccountNumber,amount, new Date()));
            return transaction;
    }


    @Override
    public List<Account> findAll() {
        return null;
    }

}