package eu.portnus.model;

import java.util.List;

import eu.portunus.core.IPasswordEntry;
import eu.portunus.core.IPasswordGroup;

public class PasswordGroup extends PasswordEntry implements IPasswordGroup {
	private PasswordEntryContainer pEContainer;
	
	public PasswordGroup() {
		 pEContainer= new PasswordEntryContainer();
	}

	@Override
	public String getTitle() {
		return super.getTitle();
	}

	@Override
	public void setTitle(String title) {
		super.setTitle(title);;
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
