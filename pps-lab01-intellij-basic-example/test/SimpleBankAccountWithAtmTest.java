import lab01.example.model.AccountHolder;
import lab01.example.model.BankAccount;
import lab01.example.model.SimpleBankAccountWithAtm;

class SimpleBankAccountWithAtmTest extends BaseBankAccountTest {
    @Override
    BankAccount createBankAccount(AccountHolder holder, double initialBalance) {
        return new SimpleBankAccountWithAtm(holder, initialBalance);
    }


}
