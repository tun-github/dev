package com.calculator.roman;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class RomanNumeralTest {
	@Rule
	public final ExpectedException exception = ExpectedException.none();
	
	@Test
	public void newRomanNumeralWithInteger() {
		int intValue = 3491;
		String romanValue = "MMMCDXCI";
		RomanNumeral romanNumeral = new RomanNumeral(intValue);
		assertThat(romanNumeral.getRomanValue(), equalTo(romanValue));
	}
	
	@Test
	public void newRomanNumeralWithString() {
		int intValue = 3491;
		String romanValue = "MMMCDXCI";
		RomanNumeral romanNumeral = new RomanNumeral(romanValue);
		assertThat(romanNumeral.getIntValue(), equalTo(intValue));
	}
	
	@Test
	public void addTwoRomanNumeral() {
		String roman1 = "MDCCCXCIX";
		String roman2 = "DCCCLVIII";
		RomanNumeral sum = new RomanNumeral(roman1).add(new RomanNumeral(roman2));
		assertThat(sum.getRomanValue(), equalTo("MMDCCLVII"));
	}
	
	@Test
	public void newRomanNumeral_withBeyondUpperLimit() {
		exception.expect(IllegalArgumentException.class);
		new RomanNumeral(4000);
	}
	
	@Test
	public void newRomanNumeral_withBeyondLowerLimit() {
		exception.expect(IllegalArgumentException.class);
		new RomanNumeral(0);
	}
}
