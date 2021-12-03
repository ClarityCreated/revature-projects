package com.revature.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "transactions")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int id;
    public double amount;
    @Enumerated(EnumType.STRING)
    public TransactionType type;
    @Temporal(TemporalType.TIMESTAMP)
    public Date date;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="account_number")
    public Account account;
}
