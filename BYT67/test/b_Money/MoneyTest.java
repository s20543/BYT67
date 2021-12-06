package b_Money;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class MoneyTest {
	Currency SEK, DKK, NOK, EUR;
	Money SEK100, EUR10, SEK200, EUR20, SEK0, EUR0, SEKn100;
	
	@Before
	public void setUp() throws Exception {
		SEK = new Currency("SEK", 0.15);
		DKK = new Currency("DKK", 0.20);
		EUR = new Currency("EUR", 1.5);
		SEK100 = new Money(10000.0, SEK);
		EUR10 = new Money(1000.0, EUR);
		SEK200 = new Money(20000.0, SEK);
		EUR20 = new Money(2000.0, EUR);
		SEK0 = new Money(0.0, SEK);
		EUR0 = new Money(0.0, EUR);
		SEKn100 = new Money(-10000.0, SEK);
	}

	@Test
	public void testGetAmount() {
		assertEquals("1000.0",EUR10.getAmount().toString() );
	}

	@Test
	public void testGetCurrency() {
		assertEquals("EUR",EUR10.getCurrency().getName());
	}

	@Test
	public void testToString() {
		assertEquals("10000.0 SEK", SEK100.toString());
	}

	@Test
	public void testGlobalValue() {
		assertEquals("1500.0", SEK100.universalValue().toString());
	}

	@Test
	public void testEqualsMoney() {
		assertEquals(false,SEK100.equals(EUR10) );
	}

	@Test
	public void testAdd() {
		assertEquals("30000.0 SEK", SEK100.add(SEK200).toString());
	}

	@Test
	public void testSub() {
		assertEquals("10000.0 SEK", SEK200.sub(SEK100).toString() );
	}

	@Test
	public void testIsZero() {
		assertTrue(SEK0.isZero());
	}

	@Test
	public void testNegate() {
		assertEquals("-10000.0 SEK", SEK100.negate().toString());
	}

	@Test
	public void testCompareTo() {
		assertEquals(0, SEK100.add(SEK100).compareTo(EUR10));
	}
}
