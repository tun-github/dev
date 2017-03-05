package com.calculator.roman;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class RomanCalculatorTest {
	
	@Test
	public void calculateInputFileTest() throws URISyntaxException {
		String inputFilePath = "test_data/input.txt";
		String expectedOutputFilePath = "test_data/expected_output.txt";
		
		ClassLoader classLoader = getClass().getClassLoader();
		File inputFile = new File(classLoader.getResource(inputFilePath).getFile());
		File outputFile = new File(classLoader.getResource(expectedOutputFilePath).getFile());
		
		List<String> expectedResultList = readFile(outputFile);
		
		RomanCalculator calculator = new RomanCalculator();
		calculator.setPrintOutput(false);
		List<String> resultList = calculator.calculateInputFile(inputFile.toPath());
		assertThat(resultList, equalTo(expectedResultList));
	}
	
	private List<String> readFile(File file) {
		List<String> listOfString = new ArrayList<>();
		Path path = file.toPath();
		try {
			Files.lines(path).forEach(input -> {
				listOfString.add(input);
			});
		} catch (IOException ex) {
			System.out.println("Error occured while reading input file: " + ex.getMessage());
			ex.printStackTrace();
			System.exit(1);
		}
		return listOfString;
	}
}
