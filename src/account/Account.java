package account;

import java.util.ArrayList;
import java.util.List;

public class Account{
    
    private String accountNumber;
    private int pin;
    private double balance;
    private List<String> transactionHistory;

    public Account(String accountNumber, int pin, double initialBalance){
        this.accountNumber = accountNumber;
        this.pin = pin;
        this.balance = initialBalance;
        this.transactionHistory = new ArrayList<>();
    }

    public boolean authenticate(int enteredPIN){
        return enteredPIN == pin;
    }

    public void deposit(double amount){
        balance += amount;
        transactionHistory.add("Deposited : " + amount + "Rs.");
    }

    public boolean withdraw(double amount){
        if(amount > balance) return false;
        
        balance -= amount;
        transactionHistory.add("Withdrew : " + amount + "Rs.");
        return true;
    }

    public double getBalance(){
        return balance;
    }

    public List<String> getTransactionHistory(){
        return transactionHistory;
    }

    public String getAccountNumber(){
        return accountNumber;
    }
}