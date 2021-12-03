package com.revature.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="accounts")
public class Account {
        @Id
        public String number;
        public double balance;

    @OneToMany(
            mappedBy ="account",
            targetEntity = Transaction.class,
            fetch = FetchType.LAZY
    )
    public List<Transaction> transactions;

    public Account() {

    }

    public List<Transaction> getTransactions(){
        return transactions;
    }

    public Account(String number) {
        this.number = number;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public double getBalance() {
        return balance;
    }

    public Account setBalance(double balance) {
        this.balance = balance;
        return null;
    }

    @Override
    public String toString() {
        return "Account{" +
                "number='" + number + '\'' +
                ", balance=" + balance +
                '}';
    }
}
