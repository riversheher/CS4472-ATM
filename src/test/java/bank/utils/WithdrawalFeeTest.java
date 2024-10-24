package bank.utils;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class WithdrawalFeeTest {

    private static FeesCalculator calculator;

    @BeforeAll
    static void setUp() throws Exception {
        calculator = new FeesCalculator();
    }

    @Test
    public void withdrawalCaseA1() {
        assertEquals(-0.001, calculator.calculateWithdrawalFee(-1, 5000, false, 4));
    }

    @Test
    public void withdrawalCaseA2() {
        assertEquals(0, calculator.calculateWithdrawalFee(0, 5000, false, 4));
    }

    @Test
    public void withdrawalCaseA3() {
        assertEquals(0.001, calculator.calculateWithdrawalFee(1, 5000, false, 4));
    }

    @Test
    public void withdrawalCaseA4() {
        assertEquals(1, calculator.calculateWithdrawalFee(1000, 5000, false, 4));
    }

    @Test
    public void withdrawalCaseA5() {
        assertEquals(4.999, calculator.calculateWithdrawalFee(4999, 5000, false, 4));
    }

    @Test
    public void withdrawalCaseA6() {
        assertEquals(5, calculator.calculateWithdrawalFee(5000, 5000, false, 4));
    }

    @Test
    public void withdrawalCaseA7() {
        assertEquals(0, calculator.calculateWithdrawalFee(5001, 5000, false, 4));
    }

    @Test
    public void withdrawalCaseB1() {
        assertEquals(-0.0015, calculator.calculateWithdrawalFee(-0.5, -1, false, 4));
    }

    @Test
    public void withdrawalCaseB2() {
        assertEquals(0, calculator.calculateWithdrawalFee(0, 0, false, 4));
    }

    @Test
    public void withdrawalCaseB3() {
        assertEquals(0.0015, calculator.calculateWithdrawalFee(0.5, 1, false, 4));
    }

    @Test
    public void withdrawalCaseB4() {
        assertEquals(0.5, calculator.calculateWithdrawalFee(500, 1000, false, 4));
    }

    @Test
    public void withdrawalCaseS1() {
        assertEquals(0.5, calculator.calculateWithdrawalFee(500, 1000, false, 4));
    }

    @Test
    public void withdrawalCaseS2() {
        assertEquals(0, calculator.calculateWithdrawalFee(500, 1000, true, 4));
    }

    @Test
    public void withdrawalCaseD1() {
        assertEquals(0.5, calculator.calculateWithdrawalFee(500, 1000, false, 0));
    }

    @Test
    public void withdrawalCaseD2() {
        assertEquals(0.5, calculator.calculateWithdrawalFee(500, 1000, false, 1));
    }

    @Test
    public void withdrawalCaseD3() {
        assertEquals(0.5, calculator.calculateWithdrawalFee(500, 1000, false, 2));
    }

    @Test
    public void withdrawalCaseD4() {
        assertEquals(0.5, calculator.calculateWithdrawalFee(500, 1000, false, 4));
    }

    @Test
    public void withdrawalCaseD5() {
        assertEquals(0.5, calculator.calculateWithdrawalFee(500, 1000, false, 6));
    }

    @Test
    public void withdrawalCaseD6() {
        assertEquals(0.5, calculator.calculateWithdrawalFee(500, 1000, false, 7));
    }

    @Test
    public void withdrawalCaseD7() {
        assertEquals(0.5, calculator.calculateWithdrawalFee(500, 1000, false, 8));
    }

}
