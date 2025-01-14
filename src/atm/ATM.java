package atm;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import account.Account;

public class ATM{

    private List<Account> accounts;
    private Account currentAccount;

    public ATM() {
        accounts = new ArrayList<>();
        // Sample accounts
        accounts.add(new Account("12345", 1234, 3000.0));
        accounts.add(new Account("67890", 5678, 2000.0));
    }

    public void start(){

        Scanner sc = new Scanner(System.in);
        System.out.println("Welcome to the ATM");

        if(authenticateUser(sc)){
            boolean flag = true;

            while(flag){
                System.out.println("\n ATM Menu : ");
                System.out.println("1. Check Balance");
                System.out.println("2. Withdraw Money");
                System.out.println("3. Deposit Money");
                System.out.println("4. View Transaction History");
                System.out.println("5. Exit");

                System.out.println("\n Choose your option : ");
                int option = sc.nextInt();
                
                switch (option) {
                    case 1:
                        System.out.println("Current Balance : " + currentAccount.getBalance());
                        break;

                    case 2:
                        System.out.println("Enter Amount : ");
                        double amountToWithdraw = sc.nextDouble();

                        if(currentAccount.withdraw(amountToWithdraw)){
                            System.out.println("Withdrew " + amountToWithdraw + "Rs.");
                        }
                        else{
                            System.out.println("Insufficient Balance");
                        }
                        break;

                    case 3:
                        System.out.println("Enter Amount : ");
                        double amountToDeposit = sc.nextDouble();

                        currentAccount.deposit(amountToDeposit);
                        System.out.println("Deposited : " + amountToDeposit + "Rs.");
                        break;

                    case 4:
                        System.out.println("Transaction History : \n");
                        for(String Transaction : currentAccount.getTransactionHistory()){
                            System.out.println(Transaction);
                        }
                        break;
                        
                    case 5:
                        System.out.println("Thank you for using the ATM.");
                        flag = false;
                        break;
                
                    default:
                        System.out.println("Invalid option. Try Again.");
                }
            }
        }
        sc.close();
    }

    public boolean authenticateUser(Scanner sc){
        
        System.out.print("Enter Account number : ");
        String accountNumber = sc.next();
        System.out.print("Enter PIN : ");
        int pin = sc.nextInt();

        for(Account account : accounts){

            if(account.getAccountNumber().equals(accountNumber) && account.authenticate(pin)){
                currentAccount = account;
                System.out.println("Logged In");
                return true;
            }
        }
        System.out.println("Invalid PIN or Account");
        return false;
    }
}