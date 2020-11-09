package eu.portnus.model;

import java.util.ArrayList;
import java.util.List;

import eu.portunus.core.IPasswordEntry;
import eu.portunus.core.IPasswordEntryContainer;

public class PasswordEntryContainer implements IPasswordEntryContainer {
	private ArrayList<IPasswordEntry> passwordEntryList;
	
	public PasswordEntryContainer() {
		passwordEntryList = new ArrayList<IPasswordEntry>();
	}

	@Override
	public List<IPasswordEntry> getEntries() {
		return passwordEntryList;
	}

	@Override
	public boolean addEntry(IPasswordEntry entry) {
		try {
			// Add entry to passwordEntryList
			passwordEntryList.add(entry);
		}
		catch(Exception e) {
			System.out.println("An error occured. Entry " + entry + " not added.");
			return false;
		}
		return true;
	}

	@Override
	public boolean removeEntry(IPasswordEntry entry) {
		// Remove entry from passwordEntryList
		try {
			passwordEntryList.remove(entry);
		}
		catch(Exception e) {
			System.out.println("An error occured. Entry " + entry +" not removed.");
			return false;
		}
		return true;
	}

}