package eu.portunus.util.passwordgenerator;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;

public class PasswordGenerator implements IPasswordGenerator {
	
	HashMap<CharacterSet,String> characterMap = new HashMap<CharacterSet,String>();
	
	public void generateCharacterMap(HashSet<CharacterSet> characterSets) {
		// Generate hashmap containing only allowed characters
		for(CharacterSet charset : characterSets) {
			switch(charset) {
			case DIGITS:
				if(!characterMap.containsKey(charset)) {
					characterMap.put(CharacterSet.DIGITS,"0123456789");
				}
				break;
			case LOWER_CASE:
				if(!characterMap.containsKey(charset)) {
				characterMap.put(CharacterSet.LOWER_CASE,"abcdefghijklmnopqrstuvwxyz");
				}
				break;
			case UPPER_CASE:
				if(!characterMap.containsKey(charset)) {
				characterMap.put(CharacterSet.UPPER_CASE,"ABCDEFGHIJKLMNOPQRSTUVWXYZ");
				}
				break;
			case MINUS:
				if(!characterMap.containsKey(charset)) {
				characterMap.put(CharacterSet.MINUS,"-");
				}
				break;
			case UNDERSCORE:
				if(!characterMap.containsKey(charset)) {
				characterMap.put(CharacterSet.UNDERSCORE,"_");
				}
				break;
			case SPACE:
				if(!characterMap.containsKey(charset)) {
				characterMap.put(CharacterSet.SPACE," ");
				}
				break;
			}
		}
	}
	
	@Override
	public String generatePassword(int length, Collection<CharacterSet> characterSets) {
		String pw = "";
		String characterString = "";
		
		// Check if charactersets have been set, and length is >= 0
		if (characterSets == null || characterSets.isEmpty() || length<0) {
			return "";
		}

		//Generate HashSet from characterSets to ensure no duplicates. Then generate hashmap
		HashSet<CharacterSet> characterSetsCleaned = new HashSet<CharacterSet>(characterSets);
		generateCharacterMap(characterSetsCleaned);
		
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
