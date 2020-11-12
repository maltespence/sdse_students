package eu.portunus.util.io;

import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import eu.portnus.model.PasswordGroup;
import eu.portnus.model.PasswordRecord;
import eu.portunus.core.IPasswordEntryContainer;
import eu.portunus.core.IPasswordGroup;
import eu.portunus.core.IPasswordLibrary;
import eu.portunus.util.crypter.DecryptionFailedException;

public class PasswordLibraryLoader extends AbstractPasswordLibraryLoader {
	
	@Override
	protected String decryptXMLContent(String encryptedXMLContent, String masterPassword) throws DecryptionFailedException {
		// TODO: Decrypt XML content.		
		return encryptedXMLContent;
	}
	
	private IPasswordGroup addPasswordGroup(Node tempNode, IPasswordEntryContainer parentContainer) {
		// Create Entry
			IPasswordGroup entry = new PasswordGroup();
		
		// Add entry to parentObject (Library or group)
			parentContainer.addEntry(entry);
		
		// Add Attributes to entry
			if (tempNode.hasAttributes()) {
	            NamedNodeMap nodeMap = tempNode.getAttributes();            
	            for (int i = 0; i < nodeMap.getLength(); i++) {
	                Node node = nodeMap.item(i);
	                switch(node.getNodeName()) {
	                	case "title":
	                		entry.setTitle(node.getNodeValue());
	                		break;
	                }	
	            }
	        }
		
		//System.out.println("[CREATED GROUP] : " + entry.getTitle() +" inside "+ parentObject.getClass());
		return  entry;
	}
	private void addPasswordRecord(Node tempNode, IPasswordEntryContainer parentContainer) {
		
		// Create Entry
			PasswordRecord entry = new PasswordRecord();
		
		// Add entry to parent Object (Library or group)
			parentContainer.addEntry(entry);
		
		// Add attributes to new entry
			if (tempNode.hasAttributes()) {
	            NamedNodeMap nodeMap = tempNode.getAttributes();            
	            for (int i = 0; i < nodeMap.getLength(); i++) {
	                Node node = nodeMap.item(i);
	                switch(node.getNodeName()) {
	                	case "title":
	                		entry.setTitle(node.getNodeValue());
	                		break;
	                	case "user":
	                		entry.setUser(node.getNodeValue());
	                		break;
	                	case "password":
	                		entry.setPassword(node.getNodeValue());
	                		break;
	                	case "url":
	                		entry.setUrl(node.getNodeValue());
	                		break;
	                	case "notes":
	                		entry.setNotes(node.getNodeValue());
	                		break;
	                }	
	            }
	        }
		//System.out.println("[CREATED RECORD] : " + entry.getTitle() +" inside "+ parentObject.getClass());
	}
	
	private void parseToPasswordLibrary(NodeList nodeList, IPasswordEntryContainer parentContainer) {
		IPasswordEntryContainer currentObject = parentContainer;
		//System.out.println("[INSIDE CONTAINER] : " + parentContainer.getClass());
		
		// Iterate through each node in nodeList
		for (int count = 0; count < nodeList.getLength(); count++) {

		    Node tempNode = nodeList.item(count);

		    // make sure it's element node.
		    if (tempNode instanceof Element) {
		        // Open node
		        	//System.out.println("[OPEN NODE] : " + tempNode.getNodeName());
		        
		        // If PasswordGroup, create new PasswordGroup and return object.
			        if(tempNode.getNodeName() == "PasswordGroup") {
			        	currentObject = addPasswordGroup(tempNode, parentContainer);
			        }
		        // Else if PasswordRecord, create new PasswordRecord (void)
			        else if(tempNode.getNodeName() == "PasswordRecord") {
			        	addPasswordRecord(tempNode, parentContainer);
					} 
			    // Handle child entries
			        if (tempNode.hasChildNodes()) {
		        		//System.out.println("[OPEN CHILD NODE OF] : " + tempNode.getNodeName());
		        		parseToPasswordLibrary(tempNode.getChildNodes(),currentObject);
			            //System.out.println("[CLOSE CHILD NODE OF]" + tempNode.getNodeName());
			        }
			        //System.out.println("[CLOSE NODE] : " + tempNode.getNodeName() + " [CLOSE]");
		    }
		}
	}
		
	@Override
	protected void decodeFromXML(String xmlContent, IPasswordLibrary passwordLibrary) {
		try {
			// Set up Document from XML String
		    DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		    DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		    Document doc = dBuilder.parse(new InputSource(new StringReader(xmlContent)));
		    //optional, but recommended. Kept this in, to clean up the XML.
		    doc.getDocumentElement().normalize();

		    if(doc.hasChildNodes()) {
		    	parseToPasswordLibrary(doc.getChildNodes(), passwordLibrary);
		    }
		    System.out.println("Library successfully loaded!");
	    } catch (Exception e) {
	    	e.printStackTrace();
	    }
	}
}
