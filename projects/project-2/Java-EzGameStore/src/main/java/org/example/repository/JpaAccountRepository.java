package org.example.repository;

import org.example.entity.Account;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
@Component
@Qualifier("jpa")
public class JpaAccountRepository implements AccountRepository {

    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public Account loadAccount(String number) {

        return entityManager.find(Account.class, number);
    }

    @Override
    public Account updateAccount(Account account) {
        return entityManager.merge(account);
    }


}
