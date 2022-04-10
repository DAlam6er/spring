package ru.specialist.bank2;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

// класс-сущность
@Entity
@Table(name = "accounts")
public class Account {

    // преимущество - не надо дважды описывать ключ
    @EmbeddedId
    private AccountId accountID;


    private double balance;

    public Account() {}

    public Account(AccountId accountID, double balance) {
        super();
        this.accountID = accountID;
        this.balance = balance;
    }

    public AccountId getAccountID() {
        return accountID;
    }

    public void setAccountID(AccountId accountID) {
        this.accountID = accountID;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String accountNumber()
    {
        return getAccountID().getAccountNumber();
    }



}
