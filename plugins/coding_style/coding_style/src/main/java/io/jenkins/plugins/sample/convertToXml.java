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

	TransformerHandler th;
	AttributesImpl atts;

//	public static void main (String args[]) {
//		new convertToXml().doit("./res.txt");
//	}

	public String readFile (String filePath) {
		try{
			in = new BufferedReader(new FileReader(filePath));
			out = new StreamResult("out.xml");

			String statement = "";
			//initXmlForm();
			List<String> contents = new ArrayList<String>();
			while((statement = in.readLine()) != null) {
				contents.add(statement);
			}
			in.close();
			int i = 0;
			initXML();
			while(i < contents.size()) {
				if(contents.get(i).contains("Formatting") && contents.get(i).endsWith(".c")) {
//					System.out.println(contents.get(i)); // 파일 이름
					xmlFileTagStart(contents.get(i));
					if(i+1 >= contents.size()) break;
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
			//return "./out.xml";
		}
		catch (Exception e) { e.printStackTrace(); }
		return "./out.xml";
	}


	public void initXML() throws ParserConfigurationException, TransformerConfigurationException, SAXException {

		SAXTransformerFactory tf = (SAXTransformerFactory) SAXTransformerFactory.newInstance();

		th = tf.newTransformerHandler();
		Transformer serializer = th.getTransformer();
		serializer.setOutputProperty(OutputKeys.ENCODING,"ISO-8859-1");

		serializer.setOutputProperty
		("{http://xml.apache.org/xslt}indent-amount", "4");
		serializer.setOutputProperty(OutputKeys.INDENT,"yes");
		th.setResult(out);
		th.startDocument();
		atts = new AttributesImpl();
		th.startElement("","","TESTCASES",atts);
	}
	
	public void xmlFileTagStart(String fileName) throws SAXException {
		atts.clear();
		atts.addAttribute("", "", "file", "", "" + fileName);
		th.startElement("","","FILETITLE",atts);
	}

	public void xmlForm (String data[], String code, String position) throws SAXException {
		atts.clear();
		th.startElement("","","CASE",atts);

		th.startElement("","","FILE",atts);
		th.characters(data[0].trim().toCharArray(),0,data[0].length());
		th.endElement("","","FILE");
		
		th.startElement("","","LINE",atts);
		th.characters(data[1].trim().toCharArray(),0,data[1].length());
		th.endElement("","","LINE");
		
		th.startElement("","","RULE",atts);
		th.characters(data[2].trim().toCharArray(),0,data[2].length());
		th.endElement("","","RULE");
		
		th.startElement("","","SEVERITY",atts);
		th.characters(data[3].toCharArray(),0,data[3].length());
		th.endElement("","","SEVERITY");
		
		th.startElement("","","MESSAGE",atts);
		th.characters(data[4].toCharArray(),0,data[4].length());
		th.endElement("","","MESSAGE");
		
		th.startElement("","","CODE",atts);
		th.characters(code.toCharArray(),0,code.length());
		th.endElement("","","CODE");

		th.startElement("","","POSITION",atts);
		th.characters(position.toCharArray(),0,position.length());
		th.endElement("","","POSITION");

		th.endElement("","","CASE");
	}
	
public void xmlFileTagEnd() throws SAXException {
		th.endElement("","","FILETITLE");
	}

	public void closeXML() throws SAXException {
		th.endElement("","","TESTCASES");
		th.endDocument();  }
	}
