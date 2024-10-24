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
	//Is Student
	@Test
	public void TransferFeeTest1() {
		
		//Amount transfered < $200, account from < $2000, account to < $1000
		//fee is 0.1% of amount transfered
		assertEquals(0.10, calculator.calculateTransferFee(100, 1999, 999, true));	
	}

	@Test
	public void TransferFeeTest2() {
		//Amount transfered < $200, account from < $2000, account to >= $1000
		//fee is 0.05% of amount transfered
		assertEquals(0.05, calculator.calculateTransferFee(100, 1999, 1000, true));
	}

	@Test
	public void TransferFeeTest3() {
		//Amount transfered < $200, account from >= $2000, account to < $1000
		//fee is 0.05% of amount transfered
		assertEquals(0.05, calculator.calculateTransferFee(100, 2000, 999, true));
	}

	@Test
	public void TransferFeeTest4() {
		//Amount transfered < $200, account from >= $2000, account to >= $1000
		//fee is 0.025% of amount transfered
		assertEquals(0.03, calculator.calculateTransferFee(100, 2000, 1000, true));
	}

	@Test
	public void TransferFeeTest5() {
		//Amount transfered >= $200, account from < $2000, account to < $1000
		//fee is 0.05% of amount transfered
		assertEquals(0.50, calculator.calculateTransferFee(1000, 1999, 999, true));
	}

	@Test
	public void TransferFeeTest6() {
		//Amount transfered >= $200, account from < $2000, account to >= $1000
		//fee is 0.025% of amount transfered
		assertEquals(0.25, calculator.calculateTransferFee(1000, 1999, 1000, true));
	}

	@Test
	public void TransferFeeTest7() {
		//Amount transfered >= $200, account from >= $2000, account to < $1000
		//fee is 0.25% of amount transfered
		assertEquals(2.50, calculator.calculateTransferFee(1000, 2000, 999, true));
	}

	@Test
	public void TransferFeeTest8() {
		//Amount transfered >= $200, account from >= $2000, account to >= $1000
		//fee is 0.125% of amount transfered
		assertEquals(1.25, calculator.calculateTransferFee(1000, 2000, 1000, true));
	}


	//Is not Student
	@Test
	public void TransferFeeTest9() {
	
		//Amount transfered < $100, account from < $4000, account to < $2000
		//fee is 0.2% of amount transfered
		assertEquals(0.02, calculator.calculateTransferFee(10, 3999, 1999, false));	
	}

	@Test
	public void TransferFeeTest10() {
		//Amount transfered < $100, account from < $4000, account to >= $2000
		//fee is 0.01% of amount transfered
		assertEquals(0.01, calculator.calculateTransferFee(10, 3999, 2000, false));
	}

	@Test
	public void TransferFeeTest11() {
		//Amount transfered < $100, account from >= $4000, account to < $2000
		//fee is 1% of amount transfered
		assertEquals(0.1, calculator.calculateTransferFee(10, 4000, 1999, false));
	}

	@Test
	public void TransferFeeTest12() {
		//Amount transfered < $100, account from >= $4000, account to >= $2000
		//fee is 0.5% of amount transfered
		assertEquals(0.05, calculator.calculateTransferFee(10, 4000, 2000, false));
	}

	@Test
	public void TransferFeeTest13() {
		//Amount transfered >= $100, account from < $2000, account to < $1000
		//fee is 0.2% of amount transfered
		assertEquals(1000.2, calculator.calculateTransferFee(100, 1999, 999, false));
	}

	@Test
	public void TransferFeeTest14() {
		//Amount transfered >= $100, account from < $2000, account to >= $1000
		//fee is 0.1% of amount transfered
		assertEquals(100.1, calculator.calculateTransferFee(100, 1999, 1000, false));
	}

	@Test
	public void TransferFeeTest15() {
		//Amount transfered >= $100, account from >= $2000, account to < $1000
		//fee is 0.5% of amount transfered
		assertEquals(100.5, calculator.calculateTransferFee(100, 2000, 999, false));
	}

	@Test
	public void TransferFeeTest16() {
		//Amount transfered >= $200, account from >= $2000, account to >= $1000
		//fee is 0.25% of amount transfered
		assertEquals(100.25, calculator.calculateTransferFee(100, 2000, 1000, false));
	}


}
