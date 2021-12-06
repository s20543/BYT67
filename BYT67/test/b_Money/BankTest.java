package b_Money;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class BankTest {
	Currency SEK, DKK;
	Bank SweBank, Nordea, DanskeBank;
	
	@Before
	public void setUp() throws Exception {
		DKK = new Currency("DKK", 0.20);
		SEK = new Currency("SEK", 0.15);
		SweBank = new Bank("SweBank", SEK);
		Nordea = new Bank("Nordea", SEK);
		DanskeBank = new Bank("DanskeBank", DKK);
		SweBank.openAccount("s2", new Account("Ulfrik", SEK));
		SweBank.openAccount("s3", new Account("Bob", SEK));
		Nordea.openAccount("s3", new Account("Bob", SEK));
		DanskeBank.openAccount("s1", new Account("Grunduk", DKK));
	}

	@Test
	public void testGetName() {
		assertEquals("SweBank", SweBank.getName().toString());
	}

	@Test
	public void testGetCurrency() {
		assertEquals(SweBank.getCurrency(), SEK);
	}

	@Test
	public void testOpenAccount() throws AccountExistsException, AccountDoesNotExistException {
		SweBank.openAccount("s1", new Account("Ulfrik Burevestnik", SEK));
		assertTrue(SweBank.AccExist("s1"));
	}

	@Test
	public void testDeposit() throws AccountDoesNotExistException {
		SweBank.deposit("s2", new Money(10, SEK));
		assertEquals("10.0",SweBank.getBalance("s2").toString() );
	}

	@Test
	public void testWithdraw() throws AccountDoesNotExistException {
		SweBank.withdraw("s2", new Money(10, SEK));
		assertEquals("-10.0",SweBank.getBalance("s2").toString() );
	}
	
	@Test
	public void testGetBalance() throws AccountDoesNotExistException {
		assertEquals("0.0", SweBank.getBalance("s2").toString());
	}
	
	@Test
	public void testTransfer() throws AccountDoesNotExistException {
		SweBank.transfer("s2", SweBank, "s3", new Money(10, SEK));
		assertEquals("-10.0", SweBank.getBalance("s2").toString());
		assertEquals("10.0", SweBank.getBalance("s3").toString());
	}
	
	@Test
	public void testTimedPayment() throws AccountDoesNotExistException {
		SweBank.getAccountlist().get("s2").addTimedPayment("1", 2, 3, new Money(10, SEK), SweBank, "s2");
		assertTrue(SweBank.getAccountlist().get("s2").timedPaymentExists("1"));
	}
}
