package com.revature.repository;

import com.revature.entity.Account;

import java.util.List;

public interface AccountRepository {
   List<Account> loadAccount(String number);
   void updateAccount(String number,double balance);
}
