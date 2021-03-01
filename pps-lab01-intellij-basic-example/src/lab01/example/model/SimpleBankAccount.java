package lab01.example.model;

/**
 * This class represent a particular instance of a BankAccount.
 * In particular, a Simple Bank Account allows always the deposit
 * while the withdraw is allowed only if the balance greater or equal the withdrawal amount
 */
public class SimpleBankAccount extends BaseBankAccount {
    
    public SimpleBankAccount(final AccountHolder holder, final double balance) {
        super(holder, balance);
    }

    @Override
    public final void deposit(final int usrID, final double amount) {
        deposit(usrID, amount, 0);
    }

    @Override
    public final void withdraw(final int usrID, final double amount) {
        withdraw(usrID, amount, 0);
    }
}
