package eu.portunus.util.passwordgenerator.test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import eu.portunus.util.passwordgenerator.CharacterSet;
import eu.portunus.util.passwordgenerator.PasswordGenerator;

public class PasswordGeneratorTest {
	PasswordGenerator pwgen = new PasswordGenerator();
	ArrayList<CharacterSet> charsets = new ArrayList<CharacterSet>();
	
	// Check if password length matches the input required length
	@Test
	public void passwordLengthTest() {
		charsets.clear();
		charsets.add(CharacterSet.LOWER_CASE);
		String pw = pwgen.generatePassword(10,charsets);
		assertEquals(10, pw.length());
	}

	// Check output if no charactersets provided. Output must be 0.
	@Test
	public void passwordNoCharacterSetsTest() {
		charsets.clear();
		String pw = pwgen.generatePassword(10,charsets);
		assertEquals("", pw);
	}
	
	// Check output if pw length is negative. Output must be empty.
	@Test
	public void passwordLengthNegativeTest() {
		charsets.clear();
		charsets.add(CharacterSet.LOWER_CASE);
		String pw = pwgen.generatePassword(-2,charsets);
		assertEquals("", pw);
		System.out.println("passwordLengthNegativeTest: " + pw);
	}
	
	//TODO: Test if user can input the same collection multiple times. Must not be possible.
	@Test
	public void duplicateCharsetsTest() {
		//TODO: Implement this test
		charsets.clear();
		charsets.add(CharacterSet.LOWER_CASE);
		charsets.add(CharacterSet.LOWER_CASE);
		charsets.add(CharacterSet.LOWER_CASE);
		charsets.add(CharacterSet.UPPER_CASE);
		String pw = pwgen.generatePassword(20,charsets);
		System.out.println("duplicateCharsetsTest: " + pw);
		
	}
}
