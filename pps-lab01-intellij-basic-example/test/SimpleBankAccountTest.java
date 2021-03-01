import lab01.example.model.AccountHolder;
import lab01.example.model.BankAccount;
import lab01.example.model.SimpleBankAccount;

class SimpleBankAccountTest extends BaseBankAccountTest<BankAccount> {
    @Override
    BankAccount createBankAccount(AccountHolder holder, double initialBalance) {
        return new SimpleBankAccount(holder, initialBalance);
    }
}
