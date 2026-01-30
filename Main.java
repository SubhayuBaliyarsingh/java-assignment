
class BankAccount {
    private String accountNumber;
    private String accountHolderName;
    protected double balance;

    public BankAccount(String accountNumber, String accountHolderName) {
        this.accountNumber = accountNumber;
        this.accountHolderName = accountHolderName;
        this.balance = 0.0;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.printf("Deposited $%.2f into account %s.%n", amount, accountNumber);
        }
    }

    public void withdraw(double amount) {
        if (amount > 0 && balance >= amount) {
            balance -= amount;
            System.out.printf("Withdrew $%.2f from account %s.%n", amount, accountNumber);
        }
    }

    public String getAccountDetails() {
        return "Account #" + accountNumber + " | Balance: $" + balance;
    }
}

class SavingsAccount extends BankAccount {
    private double interestRate;

    public SavingsAccount(String accNo, String holder, double rate) {
        super(accNo, holder);
        this.interestRate = rate;
    }

    public void applyInterest() {
        deposit(balance * interestRate);
    }
}

class CheckingAccount extends BankAccount {
    private double overdraftLimit;

    public CheckingAccount(String accNo, String holder, double limit) {
        super(accNo, holder);
        this.overdraftLimit = limit;
    }

    @Override
    public void withdraw(double amount) {
        if (balance - amount >= -overdraftLimit) {
            balance -= amount;
        }
    }
}


public class Main {
    public static void main(String[] args) {

        SavingsAccount sa = new SavingsAccount("1234567", "Alice", 0.02);
        CheckingAccount ca = new CheckingAccount("12345678", "Bob", 500);

        sa.deposit(200);
        ca.withdraw(100);
        ca.withdraw(600);
        sa.applyInterest();

        System.out.println(sa.getAccountDetails());
        System.out.println(ca.getAccountDetails());
    }
}
