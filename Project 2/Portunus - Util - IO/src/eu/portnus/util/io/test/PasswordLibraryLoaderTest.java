package eu.portnus.util.io.test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import eu.portnus.model.PasswordLibrary;
import eu.portunus.core.IPasswordEntry;
import eu.portunus.core.IPasswordEntryContainer;
import eu.portunus.core.IPasswordGroup;
import eu.portunus.core.IPasswordLibrary;
import eu.portunus.util.crypter.DecryptionFailedException;
import eu.portunus.util.io.PasswordLibraryLoader;

class PasswordLibraryLoaderTest {

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
		
	}

	@Test
	void test() throws FileNotFoundException, DecryptionFailedException {
		PasswordLibraryLoader libloader = new PasswordLibraryLoader();
		IPasswordLibrary library = new PasswordLibrary();
		File xmlFile = new File("C:\\Users\\malte\\git\\sdse_students\\Project 2\\Portunus - Util - IO\\ExamplePlain.portunus");
		library = libloader.load(xmlFile, "Fish", library);
		assertNotNull(library);
		System.out.println("-------------------------------------------------------");
		List<IPasswordEntry> entryList = library.getEntries();
		for(int i = 0; i < entryList.size();i++) {
			extracted(entryList.get(i), 0);
		}
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
