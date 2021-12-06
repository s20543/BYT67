package b_Money;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class AccountTest {
	Currency SEK, DKK;
	Bank Nordea;
	Bank DanskeBank;
	Bank SweBank;
	Account testAccount;
	
	@Before
	public void setUp() throws Exception {
		SEK = new Currency("SEK", 0.15);
		SweBank = new Bank("SweBank", SEK);
		SweBank.openAccount("s1", new Account("Alice" , SEK));
		testAccount = new Account("Hans", SEK);
		//testAccount.deposit(new Money(10000000.0, SEK));

		//SweBank.deposit("Alice", new Money(1000000.0, SEK));
	}
	
	@Test
	public void testAddRemoveTimedPayment() {
	testAccount.addTimedPayment("1", 2, 3, new Money(10, SEK), SweBank, "s1");
	assertTrue(testAccount.timedPaymentExists("1"));
	testAccount.removeTimedPayment("1");
	assertFalse(testAccount.timedPaymentExists("1"));
	}
	
	@Test
	public void testTimedPayment() throws AccountDoesNotExistException {
		testAccount.addTimedPayment("1", 2, 3, new Money(10, SEK), SweBank, "s1");
		assertTrue(testAccount.timedPaymentExists("1"));
	}

	@Test
	public void testAddWithdraw() {
		testAccount.deposit(new Money(10, SEK));
		assertEquals("10.0 SEK", testAccount.getBalance().toString());
	}
	
	@Test
	public void testGetBalance() {
		assertEquals(0, testAccount.getBalance().compareTo(new Money(0.0, SEK)));
	}
}
