package eu.portnus.model.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import eu.portnus.model.PasswordEntry;
import eu.portnus.model.PasswordGroup;
import eu.portnus.model.PasswordRecord;
import eu.portunus.core.IPasswordEntry;

class PasswordEntryTest {

	@Test
	void test() {
		PasswordGroup group1 = new PasswordGroup();
		PasswordGroup group2 = new PasswordGroup();
		PasswordRecord entry1 = new PasswordRecord();
		PasswordRecord entry2 = new PasswordRecord();
		group1.addEntry(entry1);
		group1.addEntry(entry2);
		group1.addEntry(group2);
		entry1.setTitle("Entry Title 1");
		entry1.setUrl("google.com");
		entry1.setUser("admin");
		entry1.setPassword("password12345");
		group2.setTitle("Group Title 2");
		
		List<IPasswordEntry> entrylist = group1.getEntries();
		ArrayList<String> titleArray = new ArrayList<String>();
		for (IPasswordEntry entry : entrylist) {
			System.out.println(entry.getTitle());
			titleArray.add(entry.getTitle());
		}
		System.out.println(titleArray); 
		assertNotNull(group1);
	}

}
