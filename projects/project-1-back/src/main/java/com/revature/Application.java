package com.revature;

import com.revature.entity.Account;
import com.revature.entity.Transaction;
import com.revature.entity.TransactionType;
import io.javalin.Javalin;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Date;

public class Application {
    public static void main(String[] args) {

        Javalin app = Javalin.create(config -> {
            // your config here
            config.enableCorsForAllOrigins();
        }).start(8080);

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("my-pu");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        Account fromAccount=entityManager.find(Account.class,"1");
        Account toAccount=entityManager.find(Account.class,"2");

        fromAccount.balance= fromAccount.getBalance()-100;
        toAccount.balance= toAccount.getBalance()+100;

        entityManager.merge(fromAccount);
        entityManager.merge(toAccount);

        Transaction debitTxn=new Transaction();
        debitTxn.type= TransactionType.DEBIT;
        debitTxn.amount=100;
        debitTxn.date=new Date();
        debitTxn.account=fromAccount;

        Transaction creditTxn=new Transaction();
        creditTxn.type= TransactionType.CREDIT;
        creditTxn.amount=100;
        creditTxn.date=new Date();
        creditTxn.account=toAccount;

        entityManager.persist(debitTxn);
        entityManager.persist(creditTxn);

        entityManager.getTransaction().commit();
        entityManager.close();
        entityManagerFactory.close();
    }
}
