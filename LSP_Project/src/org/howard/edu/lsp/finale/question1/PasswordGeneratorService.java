package org.howard.edu.lsp.finale.question1;
/**
 * Service providing password generation via a Singleton and 
 * interchangeable algorithms
 */
public class PasswordGeneratorService {
	
	private static PasswordGeneratorService instance = null;
	private PasswordGeneratorAlgorithm algorithm = null;
	
	/**
	 * Part C: Required design pattern documentation
	 * 
	 * Patterns used: 
	 * 
	 * 1. Singleton Pattern: 
	 * 		Ensures only one PasswordGeneratorService instance exists, 
	 * 		satisfying the requirement for a single shared access point. 
	 * 
	 * 2. Strategy Pattern: 
	 * 		Each password generation approach is implemented as a separate class sharing a common interface. 
	 * 		Algorithms can be swapped at run time through setAlgorithm(), enabling future expansion 
	 * 		without modifying client code. 
	 * 
	 */
	
	private PasswordGeneratorService() {}
	
	//REQUIRED SIGNATURE
	public static PasswordGeneratorService getInstance() {
		if (instance == null) { 
			instance = new PasswordGeneratorService();
		}
		return instance;
	}
	
	//REQUIRED SIGNATURE
	public void setAlgorithm(String name){
		switch (name.toLowerCase()) {
			case "basic" -> algorithm = new BasicAlgorithm();
			case "enhanced" -> algorithm = new EnhancedAlgorithm();
			case "letters" -> algorithm = new LettersAlgorithm(); 
			default -> throw new IllegalArgumentException("Unknown algorithm: "+ name);	
		}
	}
	//REQUIRED SIGNATURE
	public String generatePassword(int length) {
		if (algorithm == null) {
			throw new IllegalStateException("Algorithm not set.");
		}
		return algorithm.generate(length);
	}
}

