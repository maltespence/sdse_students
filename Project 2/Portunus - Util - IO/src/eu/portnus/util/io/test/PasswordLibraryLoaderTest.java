package eu.portnus.util.io.test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.FileNotFoundException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import eu.portnus.model.PasswordLibrary;
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
		System.out.println(library);
	}

}
