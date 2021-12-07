package com.revature.repository;

import com.revature.entity.Transaction;

import java.util.List;

public interface TransactionRepository {
    List<Transaction> findAllTransactions();
}
