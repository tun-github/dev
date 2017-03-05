package com.calculator.roman;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.naming.OperationNotSupportedException;

public class RomanCalculator {
	public static final String PLUS = "+";
	public static final String MATH_OPERATOR = String.format("[\\%1$s]", PLUS) ;
	public static final String SPLITTER = String.format("(?=%1$s)|(?<=%1$s)", MATH_OPERATOR, MATH_OPERATOR);
	
	private boolean printOutput = true;
	
	public static void main(String[] args) {
		if (args.length == 0) {
			System.out.println("No input file provided.\nUsage: java RomanCalculator /path/to/input/file");
			System.exit(1);
		}
		Path inputPath = Paths.get(args[0]);
		RomanCalculator calculator = new RomanCalculator();
		calculator.calculateInputFile(inputPath);
	}
	
	public List<String> calculateInputFile(Path inputPath) {
		List<String> inputList = readInput(inputPath);
		List<String> resultList = calculate(inputList);
		
		if (printOutput) {
			printOutput(resultList);
		}
		
		return resultList;
	}
	
	public List<String> readInput(Path path) {
		List<String> inputList = new ArrayList<>();
		try {
			Files.lines(path).forEach(input -> {
				inputList.add(input);
			});
		} catch (IOException ex) {
			System.out.println("Error occured while reading input file: " + ex.getMessage());
			ex.printStackTrace();
			System.exit(1);
		}
		return inputList;
	}
	
	public List<String> calculate(List<String> inputList) {
		List<String> resultList = new ArrayList<String>();
		for (String input : inputList) {
			String result;
			try {
				String[] parsedInput = parseInput(input);
				RomanNumeral number1 = new RomanNumeral(parsedInput[0]);
				RomanNumeral number2 = new RomanNumeral(parsedInput[2]);
				
				String operator = parsedInput[1];
				RomanNumeral calculatedValue;
				switch(operator) {
				case PLUS:
					calculatedValue = number1.add(number2);
					break;
				default:
					throw new OperationNotSupportedException(String.format("Operator \"%1$s\" is not supported", operator));
				}
				result = calculatedValue.getRomanValue();
			} catch(Exception ex) {
				result = ex.getMessage();
			}
			
			resultList.add(result);
		}
		return resultList;
	}
	
	private String[] parseInput(String input) {
		// validate input must not be empty
		if (input == null || input.trim().isEmpty()) {
			throw new RuntimeException("Invalid input: input is empty");
		}
		
		// validate input format
		String[] parsedInput = input.split(SPLITTER);
		if (parsedInput.length != 3) {
			throw new RuntimeException("Invalid input: incorrect input format");
		}
		
		return parsedInput;
	}

	public void printOutput(List<String> resultList) {
		for (String result : resultList) {
			System.out.println(result);
		}
	}
	
	public void setPrintOutput(boolean printOutput) {
		this.printOutput = printOutput;
	}
	
}
