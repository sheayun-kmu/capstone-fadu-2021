package io.jenkins.plugins.sample;

import java.lang.String;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileOutputStream;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.stream.*;
import javax.xml.transform.sax.*;
import org.xml.sax.*;

import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.*;
import javax.xml.transform.dom.*;

public class convertToXml{
	Document xmldoc;
	Element root;
	BufferedReader in;
	StreamResult out;
	public String readFile(String filePath) throws IOException, ParserConfigurationException, TransformerException {
		in = new BufferedReader(new FileReader(filePath));
		out = new StreamResult("/var/jenkins_home/workspace/out.xml");

		String statement = "";
		initXmlForm();
		while((statement = in.readLine()) != null) {
			String data[] = statement.split(":");
			String code = in.readLine();
			String position = in.readLine();
			xmlForm(data, code, position);
		}
		in.close();
		writeXml();
		return "/var/jenkins_home/workspace/github_test/out.xml";
	}
	
	public void initXmlForm() throws ParserConfigurationException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	    DocumentBuilder builder = factory.newDocumentBuilder();
	    DOMImplementation impl = builder.getDOMImplementation();
	    
	    xmldoc = impl.createDocument(null, "report", null);
	    root = xmldoc.getDocumentElement();
	}
	
	public void xmlForm(String data[], String code, String position) {
		Element caseIdentifier = xmldoc.createElement("issue");
		Element type = xmldoc.createElement("type"); 
		Element category = xmldoc.createElement("category");

		Element codeSeverity = xmldoc.createElement("severity");
		Element message = xmldoc.createElement("message");
		Element fileLineStart = xmldoc.createElement("lineStart");
                Element fileLineEnd = xmldoc.createElement("lineEnd");
                Element columnStart = xmldoc.createElement("columnStart");
                Element columnEnd = xmldoc.createElement("columnEnd");
		Element fileName = xmldoc.createElement("fileName");
		
		Node Category = xmldoc.createTextNode("Coding style");
		Node Type = xmldoc.createTextNode("Code stlye check");
		Node Severity = xmldoc.createTextNode(data[3].trim());
		Node Message = xmldoc.createTextNode(data[4]);
		Node LineStart = xmldoc.createTextNode(data[1]);
		Node LineEnd = xmldoc.createTextNode(data[1]);

		int columnRangeStart = position.indexOf("^");
		Node ColumnStart = xmldoc.createTextNode(Integer.toString(columnRangeStart));
		int columnRangeEnd = position.length()-1;
		Node ColumnEnd = xmldoc.createTextNode(Integer.toString(columnRangeEnd));
		Node FileName = xmldoc.createTextNode(data[0]);
		
		category.appendChild(Category);
		type.appendChild(Type);
		codeSeverity.appendChild(Severity);
		message.appendChild(Message);
		fileLineStart.appendChild(LineStart);
		fileLineEnd.appendChild(LineEnd);
		columnStart.appendChild(ColumnStart);
		columnEnd.appendChild(ColumnEnd);
		fileName.appendChild(FileName);

		caseIdentifier.appendChild(category);
		caseIdentifier.appendChild(type);
		caseIdentifier.appendChild(codeSeverity);
		caseIdentifier.appendChild(message);
		caseIdentifier.appendChild(fileLineStart);
		caseIdentifier.appendChild(fileLineEnd);
		caseIdentifier.appendChild(columnStart);
		caseIdentifier.appendChild(columnEnd);
		caseIdentifier.appendChild(fileName);
		
		root.appendChild(caseIdentifier);
	}
	
	public void writeXml() throws TransformerException, IOException {
		 DOMSource domSource = new DOMSource(xmldoc);
	    TransformerFactory tf = TransformerFactory.newInstance();
	    
	    Transformer transformer = tf.newTransformer();
	    transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");
	    transformer.setOutputProperty(OutputKeys.METHOD, "xml");
	    transformer.setOutputProperty(OutputKeys.ENCODING,"UTF-8");
	    transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
	    transformer.setOutputProperty(OutputKeys.INDENT, "yes");
	    transformer.transform(domSource, out);
	}
}
