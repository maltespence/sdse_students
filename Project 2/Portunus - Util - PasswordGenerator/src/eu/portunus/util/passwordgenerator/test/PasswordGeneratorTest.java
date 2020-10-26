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
	public void passwordLengthCorrectTest() {
		charsets.clear();
		charsets.add(CharacterSet.LOWER_CASE);
		String pw = pwgen.generatePassword(10,charsets);
		assertEquals(10, pw.length());
	}

	// Check output if no characterSets provided. Output must be 0.
	@Test
	public void noCharacterSetsTest() {
		charsets.clear();
		String pw = pwgen.generatePassword(10,charsets);
		assertEquals("", pw);
	}
	
	// Check output if pw length is negative. Output must be empty string.
	@Test
	public void passwordLengthNegativeTest() {
		charsets.clear();
		charsets.add(CharacterSet.LOWER_CASE);
		String pw = pwgen.generatePassword(-2,charsets);
		assertEquals("", pw);
		System.out.println("passwordLengthNegativeTest: " + pw);
	}
	
	//Test if password length can be 0. Output must be empty string.
	@Test
	public void passwordLengthZero() {
		charsets.clear();
		charsets.add(CharacterSet.DIGITS);
		charsets.add(CharacterSet.LOWER_CASE);
		charsets.add(CharacterSet.UPPER_CASE);
		String pw = pwgen.generatePassword(0,charsets);
		assertEquals("", pw);
		
	}
	
	//Test if user can input the same collection multiple times. Must not be possible.
	@Test
	public void duplicateCharacterSetsTest() {
		charsets.clear();
		charsets.add(CharacterSet.LOWER_CASE);
		charsets.add(CharacterSet.LOWER_CASE);
		charsets.add(CharacterSet.LOWER_CASE);
		charsets.add(CharacterSet.UPPER_CASE);
		String pw = pwgen.generatePassword(20,charsets);
		System.out.println("duplicateCharsetsTest: " + pw);
		// I do not know how to automatically validate this test. As such, it is manual.
	}
	
	@Test
	public void allCharacterSets() {
		charsets.clear();
		charsets.add(CharacterSet.DIGITS);
		charsets.add(CharacterSet.LOWER_CASE);
		charsets.add(CharacterSet.UPPER_CASE);
		charsets.add(CharacterSet.MINUS);
		charsets.add(CharacterSet.UNDERSCORE);
		charsets.add(CharacterSet.SPACE);
		String pw = pwgen.generatePassword(20,charsets);
		System.out.println("allCharacterSets: " + pw);
		// I do not know how to automatically validate this test. As such, it is manual.
		// I cannot guarantee, that one entry from each CharacterSet is part of the password.
	}

}
