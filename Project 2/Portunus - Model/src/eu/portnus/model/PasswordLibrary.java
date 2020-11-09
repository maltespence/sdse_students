package eu.portnus.model;

import java.util.List;

import eu.portunus.core.IPasswordEntry;
import eu.portunus.core.IPasswordLibrary;

public class PasswordLibrary implements IPasswordLibrary {
	private PasswordEntryContainer pEContainer;
	
	public PasswordLibrary() {
		this.pEContainer= new PasswordEntryContainer();
	}

	@Override
	public List<IPasswordEntry> getEntries() {
		return pEContainer.getEntries();
	}

	@Override
	public boolean addEntry(IPasswordEntry entry) {
		return pEContainer.addEntry(entry);
	}

	@Override
	public boolean removeEntry(IPasswordEntry entry) {
		return pEContainer.removeEntry(entry);
	}

}
