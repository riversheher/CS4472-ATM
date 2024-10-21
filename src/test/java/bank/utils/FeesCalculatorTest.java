package bank.utils;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

// Use of Parameterized helps in this case, since multiple runs of same test are required
class FeesCalculatorTest {
	FeesCalculator calculator = new FeesCalculator();

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void withdrawalTest() {
		assertEquals(0.2, calculator.calculateWithdrawalFee(200, 1000, false, 0));		//pass
		//assertEquals(0.01, calculator.calculateWithdrawalFee(200, 1000, false, 0));	//fail
	}

	@Test
	public void TransferFeeTest() {
		//Is Student
		//Amount transfered < $200, account from < $2000, account to < $1000
		//fee is 0.1% of amount transfered
		assertEquals(100.10, calculator.calculateTransferFee(100, 1999, 999, true));	
		
		//Amount transfered < $200, account from < $2000, account to >= $1000
		//fee is 0.05% of amount transfered
		assertEquals(100.05, calculator.calculateTransferFee(100, 1999, 1000, true));
		
		//Amount transfered < $200, account from >= $2000, account to < $1000
		//fee is 0.05% of amount transfered
		assertEquals(100.05, calculator.calculateTransferFee(100, 2000, 999, true));
		
		//Amount transfered < $200, account from >= $2000, account to >= $1000
		//fee is 0.025% of amount transfered
		assertEquals(100.03, calculator.calculateTransferFee(100, 2000, 1000, true));
		
		//Amount transfered >= $200, account from < $2000, account to < $1000
		//fee is 0.05% of amount transfered
		assertEquals(1000.50, calculator.calculateTransferFee(1000, 1999, 999, true));
		
		//Amount transfered >= $200, account from < $2000, account to >= $1000
		//fee is 0.025% of amount transfered
		assertEquals(1000.25, calculator.calculateTransferFee(1000, 1999, 1000, true));
		
		//Amount transfered >= $200, account from >= $2000, account to < $1000
		//fee is 0.25% of amount transfered
		assertEquals(1002.50, calculator.calculateTransferFee(1000, 2000, 999, true));
		
		//Amount transfered >= $200, account from >= $2000, account to >= $1000
		//fee is 0.125% of amount transfered
		assertEquals(1001.25, calculator.calculateTransferFee(1000, 2000, 1000, true));
		
	}

}
