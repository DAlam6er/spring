package ru.specialist.bank;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

// класс-сущность
@Entity
@Table(name = "accounts")
@IdClass(AccountId.class)
public class Account {
    // Составной ключ "номер счета-тип"
    @Id
    private String accountNumber;

    @Id
    private String accountType;

    private double balance;

    public Account() {}

    public Account(String accountNumber, String accoutType, double balance) {
        super();
        this.accountNumber = accountNumber;
        this.accountType = accoutType;
        this.balance = balance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getAccoutType() {
        return accountType;
    }

    public void setAccoutType(String accoutType) {
        this.accountType = accoutType;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }



}
