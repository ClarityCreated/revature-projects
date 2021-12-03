package com.revature.web;

import com.revature.entity.Account;
import com.revature.repository.AccountRepository;
import com.revature.repository.JpaAccountRepository;
import io.javalin.http.Handler;
import org.eclipse.jetty.http.HttpStatus;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class AccountController {
    static EntityManagerFactory entityManagerFactory;

    static {
        entityManagerFactory = Persistence.createEntityManagerFactory("my-pu");
    }

    static AccountRepository accountRepository = new JpaAccountRepository();

    public static Handler loadAccount = ctx -> {
        String number = ctx.queryParam("number");
        List<Account> accounts = accountRepository.loadAccount(number);
        ctx.json(accounts);
    };
    public static Handler updateAccount = ctx -> {
        double balance = Integer.parseInt(ctx.pathParam("balance"));
        accountRepository.updateAccount("",balance);
        ctx.status(HttpStatus.OK_200);
    };
}
