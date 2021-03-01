package lab01.example.model;

public abstract class BaseBankAccount implements BankAccount {

    private double balance;
    private final AccountHolder holder;

    public BaseBankAccount(final AccountHolder holder, final double balance) {
        this.holder = holder;
        this.balance = balance;
    }

    @Override
    public final AccountHolder getHolder() {
        return this.holder;
    }

    @Override
    public final double getBalance() {
        return this.balance;
    }

    protected final void deposit(final int usrID, final double amount, final double fee) {
        validateAmountFee(amount, fee);
        if (checkUser(usrID) && isDepositAllowed(amount, fee)) {
            this.balance += amount - fee;
        }
    }

    protected final void withdraw(final int usrID, final double amount, final double fee) {
        validateAmountFee(amount, fee);
        if (checkUser(usrID) && isWithdrawAllowed(amount, fee)) {
            this.balance -= amount + fee;
        }
    }

    private void validateAmountFee(double amount, double fee) {
        if (amount < 0 || fee < 0) {
            throw new IllegalArgumentException("Amount and fee must be positive");
        }
    }

    private boolean isDepositAllowed(final double amount, final double fee) {
        return amount > fee;
    }

    private boolean isWithdrawAllowed(final double amount, final double fee) {
        return this.balance >= amount + fee;
    }

    private boolean checkUser(final int id) {
        return this.holder.getId() == id;
    }
}
