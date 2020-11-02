package eu.portunus.util.passwordgenerator;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;

public class PasswordGenerator implements IPasswordGenerator {
	
	HashMap<CharacterSet,String> characterMap = new HashMap<CharacterSet,String>();
	
	public void generateCharacterMap() {
		// Generate hashmap containing only allowed characters
		characterMap.put(CharacterSet.DIGITS,"0123456789");
		characterMap.put(CharacterSet.LOWER_CASE,"abcdefghijklmnopqrstuvwxyz");
		characterMap.put(CharacterSet.UPPER_CASE,"ABCDEFGHIJKLMNOPQRSTUVWXYZ");
		characterMap.put(CharacterSet.MINUS,"-");
		characterMap.put(CharacterSet.UNDERSCORE,"_");
		characterMap.put(CharacterSet.SPACE," ");
	}
	
	@Override
	public String generatePassword(int length, Collection<CharacterSet> characterSets) {
		String pw = "";
		String characterString = "";
		
		// Check if characterSets have been set, and length is >= 0
		if (characterSets == null || characterSets.isEmpty() || length<0) {
			return "";
		}

		//Generate HashSet from characterSets to ensure no duplicates. Then generate hashmap
		HashSet<CharacterSet> characterSetsCleaned = new HashSet<CharacterSet>(characterSets);
		generateCharacterMap();
		
		//Iterate through the positions in the password.
		for(CharacterSet charset : characterSetsCleaned) {
			characterString = characterString + characterMap.get(charset);
		}
		
		Random randomizer = new Random();
		for(int i = 0; i<length; i++) {
			int l = characterString.length();
			int r = randomizer.nextInt(l);
			pw = pw + characterString.charAt(r);
		}
		return pw;
	}
}
