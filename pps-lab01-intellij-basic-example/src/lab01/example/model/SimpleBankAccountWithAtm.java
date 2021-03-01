package lab01.example.model;

public class SimpleBankAccountWithAtm extends SimpleBankAccount implements BankAccountWithAtm {

    private static final int ATM_FEE = 1;

    public SimpleBankAccountWithAtm(AccountHolder holder, double balance) {
        super(holder, balance);
    }

    @Override
    public void depositWithAtm(int usrID, double amount) {
        deposit(usrID, amount, ATM_FEE);
    }

    @Override
    public void withdrawWithAtm(int usrID, double amount) {
        withdraw(usrID, amount, ATM_FEE);
    }
}
