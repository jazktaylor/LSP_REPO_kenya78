package org.howard.edu.lsp.finale.question1;

import java.security.SecureRandom;
/**
 * Letters-only password generator
 * A_Z and a-z only
 */
public class LettersAlgorithm implements PasswordGeneratorAlgorithm{
	private static final String LETTERS = 
			"ABCDEFGHIJKLMNOPQRSTUVWXYZ" + 
			"abcdefghijklmnopqrstuvwxyz";
	
	private SecureRandom secure = new SecureRandom();
	
	
	@Override
	public String generate(int length) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < length; i++) {
			sb.append(LETTERS.charAt(secure.nextInt(LETTERS.length())));
		}
		return sb.toString();
	}
}
