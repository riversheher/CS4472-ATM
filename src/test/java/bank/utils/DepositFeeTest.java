package bank.utils;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class DepositFeeTest {

    static FeesCalculator calculator;

    @BeforeAll
    static void setUp() throws Exception {
        calculator = new FeesCalculator();
    }

    @Test
    public void depositCase1() {
        assertEquals(-0.005, calculator.calculateDepositInterest(-1, 50000, true));
    }

    @Test
    public void depositCase2() {
        assertEquals(0.025, calculator.calculateDepositInterest(25, 2500, false));
    }

    @Test
    public void depositCase3() {
        assertEquals(0.75, calculator.calculateDepositInterest(150, 3500, true));
    }

    @Test
    public void depositCase4() {
        assertEquals(1.5, calculator.calculateDepositInterest(300, 1500, true));
    }

    @Test
    public void depositCase5() {
        assertEquals(0.75, calculator.calculateDepositInterest(300, 250, true));
    }

    @Test
    public void depositCase6() {
        assertEquals(0.75, calculator.calculateDepositInterest(300, -1, true));
    }

}
