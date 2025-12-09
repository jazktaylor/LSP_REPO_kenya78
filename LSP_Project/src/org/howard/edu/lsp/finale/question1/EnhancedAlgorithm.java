package org.howard.edu.lsp.finale.question1;

import java.security.SecureRandom;
/**
 *  Part A: Enhanced password generator using SecureRandom
 *  Allows A-Z, a-z, 0-9
 */
public class EnhancedAlgorithm implements PasswordGeneratorAlgorithm{
	private static final String ALLOWED = 
			"ABCDEFGHIJKLMNOPQRSTUVWXYZ" + 
			"abcdefghijklmnopqrstuvwxyz" + 
			"0123456789";
	
	private SecureRandom secure = new SecureRandom();
	
	@Override
	public String generate(int length) {
		StringBuilder sb = new StringBuilder(); 
		for (int i = 0; i < length; i++) {
			sb.append(ALLOWED.charAt(secure.nextInt(ALLOWED.length())));
		}
		return sb.toString();
		
	}
	
}
