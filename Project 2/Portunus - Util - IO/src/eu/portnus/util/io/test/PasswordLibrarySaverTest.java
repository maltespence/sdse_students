package eu.portnus.util.io.test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import eu.portnus.model.PasswordLibrary;
import eu.portnus.model.PasswordRecord;
import eu.portunus.core.IPasswordEntry;
import eu.portunus.core.IPasswordGroup;
import eu.portunus.core.IPasswordLibrary;
import eu.portunus.util.io.PasswordLibraryLoader;
import eu.portunus.util.io.PasswordLibrarySaver;

class PasswordLibrarySaverTest {
	IPasswordLibrary library;
	File xmlFile;
	File outputxmlFile;
	
	@BeforeEach
	void setUp() throws Exception {
		PasswordLibraryLoader libloader = new PasswordLibraryLoader();
		library = new PasswordLibrary();
		xmlFile = new File("C:\\Users\\malte\\git\\sdse_students\\Project 2\\Portunus - Util - IO\\Example_encrypted.portunus");
		outputxmlFile = new File("C:\\Users\\malte\\git\\sdse_students\\Project 2\\Portunus - Util - IO\\Example_encrypted.portunus");
		library = libloader.load(xmlFile, "Fish", library);
		assertNotNull(library);
		System.out.println("-------------------------------------------------------");
		List<IPasswordEntry> entryList = library.getEntries();
		for(int i = 0; i < entryList.size();i++) {
			extracted(entryList.get(i), 0);
		}
	}

	@AfterEach
	void tearDown() throws Exception {
		library = null;
	}

	@Test
	void testSave() throws Exception {
		PasswordRecord newEntry = new PasswordRecord();
		library.addEntry(newEntry);
		newEntry.setTitle("New Entry");
		PasswordLibrarySaver libsaver = new PasswordLibrarySaver();
		libsaver.save(library, outputxmlFile, "Fish");
	}
	
	private void extracted(IPasswordEntry entry, int indent) {
		System.out.println(indent + " : " + entry.getTitle());
		if(entry instanceof IPasswordGroup) {
			List<IPasswordEntry> subList = ((IPasswordGroup) entry).getEntries();
			for(IPasswordEntry subEntry : subList) {
				extracted(subEntry, indent + 1);
			}
		}
	}

}
