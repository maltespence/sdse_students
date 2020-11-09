package eu.portnus.model;

import eu.portunus.core.IPasswordEntry;

public abstract class PasswordEntry implements IPasswordEntry{
	private String title;
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
}