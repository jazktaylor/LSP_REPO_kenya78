package org.howard.edu.lsp.finale.question1;

import java.util.Random;
/**
 * Part A: Basic password generator 
 * Digits only (0-9)
 */
public class BasicAlgorithm implements PasswordGeneratorAlgorithm{
	private static final String DIGITS = "0123456789";
	private Random random = new Random();
	
	@Override
	public String generate(int length) {
		StringBuilder sb =new StringBuilder(); 
		for (int i = 0; i < length; i++) {
			sb.append(DIGITS.charAt(random.nextInt(DIGITS.length())));
		}
		return sb.toString();
	}
}
