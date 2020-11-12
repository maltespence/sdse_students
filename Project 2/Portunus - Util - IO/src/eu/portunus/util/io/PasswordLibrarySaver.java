package eu.portunus.util.io;

import java.io.StringWriter;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import eu.portunus.core.IPasswordEntry;
import eu.portunus.core.IPasswordGroup;
import eu.portunus.core.IPasswordLibrary;
import eu.portunus.core.IPasswordRecord;
import eu.portunus.util.crypter.AESCrypter;
import eu.portunus.util.crypter.EncryptionFailedException;
import eu.portunus.util.crypter.ICrypter;

public class PasswordLibrarySaver extends AbstractPasswordLibrarySaver {
	@Override
	protected String encodeAsXML(IPasswordLibrary passwordLibrary) {
		String output = null;
		//TODO: Write the password library to (plain text) XML by encoding the respective records and groups.
		try {
			// Set up DocumentBuilder and rootElement
				DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
		        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
		        Document doc = docBuilder.newDocument();
		        Element rootElement = doc.createElement("PasswordLibrary");
		        doc.appendChild(rootElement);				
			
	        // If Library has entries
		        if(passwordLibrary.getEntries() != null) {
		        	saveEntriesToXML(passwordLibrary.getEntries(),rootElement, doc);
		        }
		        
       
	        // Transform to XML String
		        TransformerFactory transformerFactory = TransformerFactory.newInstance();
		        Transformer transformer = transformerFactory.newTransformer();
		        DOMSource source = new DOMSource(doc);
		        StringWriter outWriter = new StringWriter();
		        StreamResult result = new StreamResult(outWriter);
	
		        transformer.transform(source, result);
		        StringBuffer sb = outWriter.getBuffer();
		        output = sb.toString();
	        // Output string for testing
		        System.out.println(output);
	        System.out.println("\nLibrary successfully saved!");
			
		} catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		} catch (TransformerException tfe) {
			tfe.printStackTrace();
	    }
		return output;
	}
	
	private void saveEntriesToXML(List<IPasswordEntry> entryList, Element parentElement, Document doc) {
		Element currentElement = parentElement;
		for(IPasswordEntry entry : entryList) {
			if(entry instanceof IPasswordGroup) {
				currentElement = savePasswordGroup(((IPasswordGroup) entry), parentElement, doc);
				if(((IPasswordGroup) entry).getEntries() != null) {
					saveEntriesToXML(((IPasswordGroup) entry).getEntries(),currentElement, doc);
				}
			}
			else if(entry instanceof IPasswordRecord) {
				savePasswordRecord(((IPasswordRecord) entry), parentElement, doc);
			}
		}
	}
	
	private Element savePasswordGroup(IPasswordGroup entry, Element parentElement, Document doc) {
		// Create element
			Element element = doc.createElement("PasswordGroup");
		// Add attributes
			if(entry.getTitle()!=null) {element.setAttribute("title", entry.getTitle());}
		// Add element to parent
			parentElement.appendChild(element);
		return element;
	}
	
	private void savePasswordRecord(IPasswordRecord entry, Element parentElement, Document doc) {
		// Create element
			Element element = doc.createElement("PasswordRecord");
		// Add attributes
			if(entry.getTitle()!=null) {element.setAttribute("title", entry.getTitle());}
			if(entry.getUser()!=null) {element.setAttribute("user", entry.getUser());}
			if(entry.getPassword()!=null) {element.setAttribute("password", entry.getPassword());}
			if(entry.getUrl()!=null) {element.setAttribute("url", entry.getUrl());}
			if(entry.getNotes()!=null) {element.setAttribute("notes", entry.getNotes());}
		// Add element to parent
			parentElement.appendChild(element);
	}
	
	@Override
	protected String encryptXMLContent(String xmlContent, String masterPassword) throws EncryptionFailedException {
		//Encrypt XML content.
		ICrypter crypter = new AESCrypter();
		String encryptedContent = crypter.encrypt(xmlContent, masterPassword);		
		return encryptedContent;
	}
}