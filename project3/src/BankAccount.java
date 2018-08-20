/*
Project03   BankAccount

Program Description:
This program can set up new bank account and perform transactions between account(s).

Mar.8   Amber He
Lab Sec 18
 */
import java.util.ArrayList;
import java.util.Random;


public class BankAccount {
    //instance variables
    private int accountNumber;
    private String name;
    private double balance;

    //static properties
    private static double interest = 0.3;
    private static ArrayList<BankAccount> accounts = new ArrayList<BankAccount>();

    //The first constructor receives a name and creates bank account with balance = 0
    public  BankAccount (String name) {
        //TODO 1.1
        this(name,0);
        accounts.add(this);
    }

    //The second constructor receives name and balance, creates a bank amount and updates balance
    public BankAccount (String name, double balance) {
        //TODO 1.2
        this.name = name;
        this.balance=balance;
        Random random = new Random();
        int index=0;

        while(index!=-1){
            accountNumber= 99999+random.nextInt(900001);
            if (accountNumber>=100000 && accountNumber<1000000)
                index=-1;
        }
    }

    //This method performs deposit operation
    public double deposit(double money) {
        //TODO 2
        if (money>=0) {
            balance = balance + money;
            return money;
        }
        else
            return -1;
    }

    //This method performs withdraw operation
    public double withdrawMoney(double money) {
        //TODO 3
        if (balance>=money) {
            balance = balance - money;
            return money;
        }
        else
            return -1;
    }

    //This method returns account number
    public int getAccountNumber() {
        //TODO 4
        return accountNumber;
    }

    //This method returns interest rate
    public static double getInterestRate() {
        //TODO 5
        int interestNum = accounts.size() / 5;
        interest = 0.3;
        interest = interest-0.02 * interestNum;
        return interest;
    }

    //This method performs a transfer operation to a single bank account
    public double transfer(BankAccount destinationBankAccount, double amount) {
        //TODO 6
        if (destinationBankAccount!=null && amount>=0) {
            if (balance >= amount) {
                destinationBankAccount.deposit(amount);
                this.withdrawMoney(amount);
            }
            else
                return -1;
        }
        else
            return -1;

        return amount;
    }

    //This method performs a transfer operation to multiple accounts
    public double transfer(BankAccount[] destinationBankAccount, double amount) {
        //TODO 7
        int numTransfer=0;
        if (destinationBankAccount == null)
            return -1;

        if (destinationBankAccount.length > 0 ) {
            for (int i = 0; i < destinationBankAccount.length; i++) {
                    if (amount >= 0) {
                        if (this.balance >= amount) {
                            this.withdrawMoney(amount);
                            destinationBankAccount[i].deposit(amount);
                            numTransfer++;
                        } else
                            return -1;
                    }
            }
        }
        else
            return -1;

        return amount*numTransfer;
    }

    public static void main(String args[]) {
        //Feel free to add code that will help you test your methods
        BankAccount bankAccountOne = new BankAccount("Bob", 1000);
        BankAccount bankAccountTwo = new BankAccount("Sara");
        BankAccount bankAccountThree = new BankAccount("Ell");
        BankAccount[] d = new BankAccount[2];
        d[0]=bankAccountTwo;
        d[1]=bankAccountThree;

        accounts.add(bankAccountOne);
        accounts.add(bankAccountTwo);
        BankAccount.getInterestRate();

        System.out.println(bankAccountOne.transfer(d,400));
    }
}