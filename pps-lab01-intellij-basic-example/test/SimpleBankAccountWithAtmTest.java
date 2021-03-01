import lab01.example.model.AccountHolder;
import lab01.example.model.BankAccountWithAtm;
import lab01.example.model.SimpleBankAccountWithAtm;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

class SimpleBankAccountWithAtmTest extends BaseBankAccountTest<BankAccountWithAtm> {
    @Override
    BankAccountWithAtm createBankAccount(AccountHolder holder, double initialBalance) {
        return new SimpleBankAccountWithAtm(holder, initialBalance);
    }

    @Test
    void testDepositWithAtm() {
        bankAccount.depositWithAtm(accountHolder.getId(),  100);
        assertEquals(99, bankAccount.getBalance());
    }

    @Test
    void testWithdrawWithAtm() {
        bankAccount.deposit(accountHolder.getId(), 100);
        bankAccount.withdrawWithAtm(accountHolder.getId(), 50);
        assertEquals(49, bankAccount.getBalance());
    }

    @Test
    void testWrongDepositWithAtm() {
        bankAccount.depositWithAtm(accountHolder.getId(), 0.5);
        bankAccount.depositWithAtm(2, 100);
        assertEquals(0, bankAccount.getBalance());
    }

    @Test
    void testWrongWithdrawWithAtm() {
        bankAccount.withdrawWithAtm(accountHolder.getId(), 100);
        bankAccount.deposit(accountHolder.getId(), 100);
        bankAccount.withdrawWithAtm(accountHolder.getId(), 100);
        bankAccount.withdrawWithAtm(2, 100);
        assertEquals(100, bankAccount.getBalance());
    }
}
