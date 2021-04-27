package toXml;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

//SAX classes.
import org.xml.sax.*;
import org.xml.sax.helpers.*;

import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.stream.*;
import javax.xml.transform.sax.*;

public class convertToXml  {

	BufferedReader in;
	StreamResult out;

	TransformerHandler transhandler;
	AttributesImpl attributes;

//	public static void main (String args[]) {
//		new convertToXml().readFile("./res.txt");
//	}

	public String readFile (String filePath) {
		try{
			in = new BufferedReader(new FileReader(filePath));
			out = new StreamResult("out.xml");

			String statement = "";
			List<String> contents = new ArrayList<String>();
			while((statement = in.readLine()) != null) {
				contents.add(statement);
			}
			in.close();
			int i = 0;
			initXML();
			System.out.println(contents.size());
			while(i < contents.size()) {
				if(contents.get(i).contains("Formatting") && contents.get(i).endsWith(".c")) {
//					System.out.println(contents.get(i)); // 파일 이름
					xmlFileTagStart(contents.get(i));
					if(i+1 >= contents.size()) {
						xmlFileTagEnd();
						break;
					}
					i = i + 1;
					while(contents.get(i).contains("Formatting") != true && contents.get(i) != null) {
						// 수정 필요 내용들
//						System.out.println(contents.get(i));
//						System.out.println(contents.get(i+1));
//						System.out.println(contents.get(i+2));
						String data[] = contents.get(i).split(":");
						String code = contents.get(i+1);
						String position = contents.get(i+2);
						xmlForm(data, code, position);
						i = i + 3;
					}
					xmlFileTagEnd();
				}
			}
			closeXML();
		}
		catch (Exception e) { 
			e.printStackTrace(); 
		}
		return "./out.xml";
	}


	public void initXML() throws ParserConfigurationException, TransformerConfigurationException, SAXException {
		SAXTransformerFactory tf = (SAXTransformerFactory) SAXTransformerFactory.newInstance();

		transhandler = tf.newTransformerHandler();
		Transformer serializer = transhandler.getTransformer();
		serializer.setOutputProperty(OutputKeys.ENCODING,"ISO-8859-1");
		serializer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
		serializer.setOutputProperty(OutputKeys.INDENT,"yes");
		transhandler.setResult(out);
		transhandler.startDocument();
		attributes = new AttributesImpl();
		transhandler.startElement("","","TESTCASES",attributes);
	}
	
	public void xmlFileTagStart(String fileName) throws SAXException {
		attributes.clear();
		attributes.addAttribute("", "", "file", "", "" + fileName);
		transhandler.startElement("","","FILETITLE",attributes);
		//transhandler.characters(fileName.toCharArray(),0,fileName.length());
	}

	public void xmlForm (String data[], String code, String position) throws SAXException {
		attributes.clear();
		transhandler.startElement("","","CASE",attributes);

		transhandler.startElement("","","FILE",attributes);
		transhandler.characters(data[0].trim().toCharArray(),0,data[0].length());
		transhandler.endElement("","","FILE");
		
		transhandler.startElement("","","LINE",attributes);
		transhandler.characters(data[1].trim().toCharArray(),0,data[1].length());
		transhandler.endElement("","","LINE");
		
		transhandler.startElement("","","RULE",attributes);
		transhandler.characters(data[2].trim().toCharArray(),0,data[2].length());
		transhandler.endElement("","","RULE");
		
		transhandler.startElement("","","SEVERITY",attributes);
		transhandler.characters(data[3].toCharArray(),0,data[3].length());
		transhandler.endElement("","","SEVERITY");
		
		transhandler.startElement("","","MESSAGE",attributes);
		transhandler.characters(data[4].toCharArray(),0,data[4].length());
		transhandler.endElement("","","MESSAGE");
		
		transhandler.startElement("","","CODE",attributes);
		transhandler.characters(code.toCharArray(),0,code.length());
		transhandler.endElement("","","CODE");

		transhandler.startElement("","","POSITION",attributes);
		transhandler.characters(position.toCharArray(),0,position.length());
		transhandler.endElement("","","POSITION");

		transhandler.endElement("","","CASE");
	}
	
	public void xmlFileTagEnd() throws SAXException {
		transhandler.endElement("","","FILETITLE");
	}

	public void closeXML() throws SAXException {
		transhandler.endElement("","","TESTCASES");
		transhandler.endDocument();  
	}
}
