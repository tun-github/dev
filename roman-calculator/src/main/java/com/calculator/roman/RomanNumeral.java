package com.calculator.roman;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class RomanNumeral {

	public static final int LOWER_BOUND = 1;
	public static final int UPPER_BOUND = 3999;
	
	public static final String ROMAN_NUMERIC_PATTERN = "M{0,3}(?:CM|CD|D?C{0,3})(?:XC|XL|L?X{0,3})(?:IX|IV|V?I{0,3})";
	public static final Map<Character, Integer> ROMAN_TO_INT_MAP = new HashMap<Character, Integer>();
	public static final Map<Integer, String> INT_TO_ROMAN_MAP = new LinkedHashMap<Integer, String>();
	static {
		ROMAN_TO_INT_MAP.put('I',1);
		ROMAN_TO_INT_MAP.put('V',5);
		ROMAN_TO_INT_MAP.put('X',10);
		ROMAN_TO_INT_MAP.put('L',50);
		ROMAN_TO_INT_MAP.put('C',100);
		ROMAN_TO_INT_MAP.put('D',500);
		ROMAN_TO_INT_MAP.put('M',1000);
		
		INT_TO_ROMAN_MAP.put(1000,"M");
		INT_TO_ROMAN_MAP.put(900,"CM");
		INT_TO_ROMAN_MAP.put(500,"D");
		INT_TO_ROMAN_MAP.put(400,"CD");
		INT_TO_ROMAN_MAP.put(100,"C");
		INT_TO_ROMAN_MAP.put(90,"XC");
		INT_TO_ROMAN_MAP.put(50,"L");
		INT_TO_ROMAN_MAP.put(40,"XL");
		INT_TO_ROMAN_MAP.put(10,"X");
		INT_TO_ROMAN_MAP.put(9,"IX");
		INT_TO_ROMAN_MAP.put(5,"V");
		INT_TO_ROMAN_MAP.put(4,"IV");
		INT_TO_ROMAN_MAP.put(1,"I");
	}
	
	private Integer intValue;
	private String romanValue;
	
	public RomanNumeral(int intValue) {
		if (intValue < LOWER_BOUND || intValue > UPPER_BOUND) {
			throw new IllegalArgumentException(String.format("%1$d is out of bound (%2$d to %3$d)", intValue, LOWER_BOUND, UPPER_BOUND));
		}
		this.intValue = intValue;
		this.romanValue = intToRoman(intValue);
	}
	
	public RomanNumeral(String romanValue) {
		if (!(romanValue = romanValue.trim()).matches(ROMAN_NUMERIC_PATTERN)) {
			throw new IllegalArgumentException(String.format("\"%1$s\" is not a valid Roman numeral", romanValue));
		}
		this.intValue = romanToInt(romanValue);
		this.romanValue = romanValue;
	}
	
	public int getIntValue() {
		return this.intValue;
	}
	
	public String getRomanValue() {
		return this.romanValue;
	}
	
	public RomanNumeral add(RomanNumeral another) {
		return new RomanNumeral(this.getIntValue() + another.getIntValue());
	}
	
	// Utility method for converting integer to Roman string
	public static String intToRoman(int intNumber) {
		int romanInt;
		String romanString;
		for (Map.Entry<Integer, String> map : INT_TO_ROMAN_MAP.entrySet()) {
			romanInt = map.getKey().intValue();
			romanString = map.getValue();
			if (intNumber < romanInt) {
				continue;
			}
			if (romanInt == intNumber) {
				return romanString;
			} else {
				return romanString + intToRoman(intNumber - romanInt);
			}
		}
		return "";
	}
		
	// Utility method for converting Roman string to integer 
	public static int romanToInt(String romanNumber) {
		int sum = 0;
		int previous = 0;
		
		for (int i=romanNumber.length()-1; i>=0; i--) {
			int current = ROMAN_TO_INT_MAP.get(romanNumber.charAt(i));
			if (current < previous) {
				sum -= current;
			} else {
				sum += current;
			}
			previous = current;
		}
		
		return sum;
	}
}
