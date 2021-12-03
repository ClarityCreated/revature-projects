package com.revature.repository;

import com.revature.entity.Account;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

public class JpaAccountRepository implements AccountRepository {

    private EntityManagerFactory entityManagerFactory;

    public JpaAccountRepository() {
    }

    public void JpaAccountRepository(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    @Override
    public List<Account> loadAccount(String number) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        String jpql="from Account";
        Query query=entityManager.createQuery(jpql);
        List<Account> accounts= query.getResultList();
        entityManager.getTransaction().commit();
        entityManager.close();
        return accounts;
    }

    @Override
    public void updateAccount(String number, double balance) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        Account account = entityManager.find(Account.class,number).setBalance(balance);

        entityManager.getTransaction().commit();
        entityManager.close();

        entityManager=entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        entityManager.merge(account); // Managed

        entityManager.getTransaction().commit();
        entityManager.close();

    }
}
