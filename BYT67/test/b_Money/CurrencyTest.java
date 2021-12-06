package b_Money;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import java.util.Optional;

public class CurrencyTest {
	Currency SEK, DKK, NOK, EUR;
	
	@Before
	public void setUp() throws Exception {
		/* Setup currencies with exchange rates */
		SEK = new Currency("SEK", 0.15);
		DKK = new Currency("DKK", 0.20);
		EUR = new Currency("EUR", 1.5);
	}

	@Test
	public void testGetName() {

		assertEquals("SEK", SEK.getName());
	}
	
	@Test
	public void testGetRate() {
		assertEquals("0.15",SEK.getRate().toString());
	}
	
	@Test
	public void testSetRate() {

		SEK.setRate(0.16);
		assertEquals("0.16", SEK.getRate().toString());
	}
	
	@Test
	public void testGlobalValue() {

		assertEquals("15.0", SEK.universalValue(100.0).toString());
	}
	
	@Test
	public void testValueInThisCurrency() {
		assertEquals("1.0", SEK.valueInThisCurrency(10.0, EUR).toString());
	}

}
