package io.jenkins.plugins.sample;
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

public class converXmlCode{
	Document xmldoc;
	Element root;
	BufferedReader in;
	StreamResult out;
	public String readFile(String filePath) throws IOException, ParserConfigurationException, TransformerException {
		in = new BufferedReader(new FileReader(filePath));
		out = new StreamResult("out.xml");

		String statement = "";
		initXmlForm();
		while((statement = in.readLine()) != null) {
			String data[] = statement.split(":");
		//	for(int i = 0; i < data.length; i++) {
				//System.out.println(data[i]);
		//	}
			//System.out.println(statement);
			String code = in.readLine();
			//System.out.println(code);
			String position = in.readLine();
			//System.out.println(position);
			xmlForm(data, code, position);
		}
		in.close();
		writeXml();
		return "./out.xml";
	}
	
	public void initXmlForm() throws ParserConfigurationException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	    DocumentBuilder builder = factory.newDocumentBuilder();
	    DOMImplementation impl = builder.getDOMImplementation();
	    
	    xmldoc = impl.createDocument(null, "testcase", null);
	    root = xmldoc.getDocumentElement();
	}
	
	public void xmlForm(String data[], String code, String position) {
		Element caseIdentifier = xmldoc.createElement("case");
		Element fileName = xmldoc.createElement("file");
		Element fileLine = xmldoc.createElement("line");
		Element ruleNumber = xmldoc.createElement("rule");
		Element codeSeverity = xmldoc.createElement("severity");
		Element wrongCode = xmldoc.createElement("code");
		Element wrongPosition = xmldoc.createElement("position");
		
		Node file = xmldoc.createTextNode(data[0]);
		Node line = xmldoc.createTextNode(data[1]);
		Node rule = xmldoc.createTextNode(data[2]);
		Node seve = xmldoc.createTextNode(data[3].trim());
		Node codeStatement = xmldoc.createTextNode(code);
		Node codePosition = xmldoc.createTextNode(position);
		
		fileName.appendChild(file);
		fileLine.appendChild(line);
		ruleNumber.appendChild(rule);
		codeSeverity.appendChild(seve);
		wrongCode.appendChild(codeStatement);
		wrongPosition.appendChild(codePosition);
		
		caseIdentifier.appendChild(fileName);
		caseIdentifier.appendChild(fileLine);
		caseIdentifier.appendChild(ruleNumber);
		caseIdentifier.appendChild(codeSeverity);
		caseIdentifier.appendChild(wrongCode);
		caseIdentifier.appendChild(wrongPosition);
		
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
//	    BufferedReader a = new BufferedReader(new FileReader("./out.xml"));
//	    String s;
//	    while((s = a.readLine()) != null) {
//	    	System.out.println(s);
//	    }
	    transformer.transform(domSource, out);
	}
	
//	public static void main(String args[]) throws IOException, ParserConfigurationException, TransformerException{
//		new converXmlCode().readFile("./res.txt");
//		// /usr/lib/terminfo/text.txt
//	}
}
