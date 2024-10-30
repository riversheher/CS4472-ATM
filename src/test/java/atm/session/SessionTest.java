package atm.session;

import atm.dispatcher.MessageDispatcher;
import atm.exceptions.InvalidAmountException;
import atm.session.transactions.ATMTransaction;
import atm.ui.panels.MainPanel;
import bank.transactions.utils.TransactionType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class SessionTest {

    private Session session;
    private MessageDispatcher dispatcher;
    private MainPanel mainPanel;
    private ATMTransaction transaction;

    @BeforeEach
    public void setUp() {
        dispatcher = Mockito.mock(MessageDispatcher.class);
        mainPanel = Mockito.mock(MainPanel.class);
        transaction = Mockito.mock(ATMTransaction.class);
        session = new Session(mainPanel, dispatcher);
    }

    // PIN FORMAT TESTS

    @Test
    public void testValidPINLength() {
        // Valid 4-digit PIN
        Assertions.assertDoesNotThrow(() -> session.addPin(new char[] { '1', '2', '3', '4' }));
    }

    @Test
    public void testLongPINLength() {
        // Invalid PIN with more than 4 digits
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> session.addPin(new char[] { '1', '2', '3', '4', '5' }));
    }

    @Test
    public void testShortPIN() {
        // Invalid PIN with less than 4 digits
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> session.addPin(new char[] { '1', '2', '3' }));
    }

    @Test
    public void testNonnumericPIN() {
        // PIN containing non-numeric characters
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> session.addPin(new char[] { '1', 'A', '!', '4' }));
    }

    // WITHDRAWAL AMOUNT TESTS

    @Test
    public void testValidProductOf20() throws InvalidAmountException {
        Mockito.when(transaction.getTransactionType()).thenReturn(TransactionType.Withdrawal);
        session.setTransaction(transaction);

        // Valid amount: 20
        Assertions.assertDoesNotThrow(() -> session.setAmount(20));
    }

    @Test
    public void testValidProductOf50() throws InvalidAmountException {
        Mockito.when(transaction.getTransactionType()).thenReturn(TransactionType.Withdrawal);
        session.setTransaction(transaction);

        // Valid amount: 250
        Assertions.assertDoesNotThrow(() -> session.setAmount(250));
    }

    @Test
    public void testInvalidAmountNotProductOf20Or50() {
        Mockito.when(transaction.getTransactionType()).thenReturn(TransactionType.Withdrawal);
        session.setTransaction(transaction);

        // Invalid amount: 35 (not divisible by 20 or 50)
        Assertions.assertThrows(InvalidAmountException.class, () -> session.setAmount(35));
    }

    @Test
    public void testWithdrawalEqualsDailyLimit() throws InvalidAmountException {
        Mockito.when(transaction.getTransactionType()).thenReturn(TransactionType.Withdrawal);
        session.setTransaction(transaction);

        // Valid amount: 1000 (daily limit)
        Assertions.assertDoesNotThrow(() -> session.setAmount(1000));
    }

    @Test
    public void testWithdrawalExceedsDailyLimit() {
        Mockito.when(transaction.getTransactionType()).thenReturn(TransactionType.Withdrawal);
        session.setTransaction(transaction);

        // Invalid amount: 1050 (exceeds daily limit)
        Assertions.assertThrows(InvalidAmountException.class, () -> session.setAmount(1050));
    }

    @Test
    public void testBelowDailyLimit() throws InvalidAmountException {
        Mockito.when(transaction.getTransactionType()).thenReturn(TransactionType.Withdrawal);
        session.setTransaction(transaction);

        // Valid amount: 990 (minimum valid withdrawal)
        Assertions.assertDoesNotThrow(() -> session.setAmount(990));
    }

    @Test
    public void testBelowSmallestMultiple() throws InvalidAmountException {
        Mockito.when(transaction.getTransactionType()).thenReturn(TransactionType.Withdrawal);
        session.setTransaction(transaction);

        // Invalid amount: 10 (below minimum valid withdrawal)
        Assertions.assertDoesNotThrow(() -> session.setAmount(10));
    }

    @Test
    public void testAboveAvailableBalance() throws InvalidAmountException {
        Mockito.when(transaction.getTransactionType()).thenReturn(TransactionType.Withdrawal);
        session.setTransaction(transaction);

        // Invalid amount: 6000 (below minimum valid withdrawal)
        Assertions.assertDoesNotThrow(() -> session.setAmount(6000));
    }
}
