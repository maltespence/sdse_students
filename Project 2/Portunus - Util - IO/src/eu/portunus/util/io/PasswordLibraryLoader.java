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
import eu.portunus.core.IPasswordLibrary;
import eu.portunus.util.crypter.DecryptionFailedException;

public class PasswordLibraryLoader extends AbstractPasswordLibraryLoader {
	
	@Override
	protected String decryptXMLContent(String encryptedXMLContent, String masterPassword) throws DecryptionFailedException {
		// TODO: Decrypt XML content.		
		return encryptedXMLContent;
	}
	
	private void addPasswordGroup(Node tempNode) {
		PasswordGroup entry = new PasswordGroup();
		if (tempNode.hasAttributes()) {

            // get attributes names and values
            NamedNodeMap nodeMap = tempNode.getAttributes();            
            for (int i = 0; i < nodeMap.getLength(); i++) {
                Node node = nodeMap.item(i);
                switch(node.getNodeName()) {
                	case "title":
                		entry.setTitle(node.getNodeValue());
                		break;
                }	
                System.out.println(node.getNodeName() + " : " + node.getNodeValue() + "[GROUP CREATED]");
            }
        }
		//TODO: Add to group
	}
	private void addPasswordRecord(Node tempNode) {
		PasswordRecord entry = new PasswordRecord();
		if (tempNode.hasAttributes()) {
            // get attributes names and values
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
                		entry.setTitle(node.getNodeValue());
                		break;
                	case "url":
                		entry.setTitle(node.getNodeValue());
                		break;
                	case "notes":
                		entry.setTitle(node.getNodeValue());
                		break;
                }	
                System.out.println(node.getNodeName() + " : " + node.getNodeValue() + "[RECORD CREATED]");
            }
        }
		//TODO: Add to group.
	}
	
	
	private void parseToPasswordLibrary(NodeList nodeList) {
		// Itterate through each node in nodelist
		for (int count = 0; count < nodeList.getLength(); count++) {

		    Node tempNode = nodeList.item(count);

		    // make sure it's element node.
		    if (tempNode instanceof Element) {
		        // get node name and value
		        System.out.println("\nNode Name =" + tempNode.getNodeName() + " [OPEN]");
		        
		        if(tempNode.getNodeName() == "PasswordGroup") {
		        	addPasswordGroup(tempNode);
		        }
		        else if(tempNode.getNodeName() == "PasswordRecord") {
		        	addPasswordRecord(tempNode);
				}

		        if (tempNode.hasChildNodes()) {
		            // loop again if has child nodes
		            parseToPasswordLibrary(tempNode.getChildNodes());

		        }
		        
		        System.out.println("Node Name = " + tempNode.getNodeName() + " [CLOSE]");
		    }
		}
	}
	
	
	@Override
	protected void decodeFromXML(String xmlContent, IPasswordLibrary passwordLibrary) {
		//TODO: Read (plain text) XML content and fill the password library with the respective records and groups.
		try {
			// Set up Document from XML String
		    DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		    DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		    Document doc = dBuilder.parse(new InputSource(new StringReader(xmlContent)));
		    //optional, but recommended. Kept this in, to clean up the XML.
		    doc.getDocumentElement().normalize();

		    if(doc.hasChildNodes()) {
		    	parseToPasswordLibrary(doc.getChildNodes());
		    }
		    
	    } catch (Exception e) {
	    	e.printStackTrace();
	    }
		//You can use the (plain-XML) example data in the root of the project to test the loader. 
	}
}
