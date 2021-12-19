package org.example.repository;

import org.example.entity.Account;

public interface AccountRepository{
    Account loadAccount(String number);
    Account updateAccount(Account account);

}
