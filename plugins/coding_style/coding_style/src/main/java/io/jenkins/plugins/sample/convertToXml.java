package io.jenkins.plugins.sample;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

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
			out = new StreamResult("/var/jenkins_home/workspace/github_test/out.xml");

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
		serializer.setOutputProperty(OutputKeys.ENCODING,"UTF-8");
		serializer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
		serializer.setOutputProperty(OutputKeys.INDENT,"yes");
		transhandler.setResult(out);
		transhandler.startDocument();
		attributes = new AttributesImpl();
		transhandler.startElement("","","analysisReport",attributes);
	}
	
	public void xmlFileTagStart(String fileName) throws SAXException {
		attributes.clear();
		//attributes.addAttribute("", "", "class", "", "linked-hash-set");
		transhandler.startElement("","","elements",attributes);
		//transhandler.characters(fileName.toCharArray(),0,fileName.length());
	}

	public void xmlForm (String data[], String code, String position) throws SAXException {
		attributes.clear();
		transhandler.startElement("","","issue",attributes);

		transhandler.startElement("","","category",attributes);
		transhandler.characters("category".toCharArray(),0,8);
		transhandler.endElement("","","category");
		
		transhandler.startElement("","","type",attributes);
		transhandler.characters("type".toCharArray(),0,4);
		transhandler.endElement("","","type");
		
		transhandler.startElement("","","severity",attributes);
		transhandler.characters(data[3].toCharArray(),0,data[3].length());
		transhandler.endElement("","","severity");
		
		transhandler.startElement("","","message",attributes);
		transhandler.characters(data[4].toCharArray(),0,data[4].length());
		transhandler.endElement("","","message");
		
		transhandler.startElement("","","lineStart",attributes);
		transhandler.characters(data[1].trim().toCharArray(),0,data[1].length());
		transhandler.endElement("","","lineStart");
		
		transhandler.startElement("","","lineEnd",attributes);
		transhandler.characters(data[1].trim().toCharArray(),0,data[1].length());
		transhandler.endElement("","","lineEnd");
		
		transhandler.startElement("","","columnStart",attributes);
		transhandler.characters(data[2].trim().toCharArray(),0,data[2].length());
		transhandler.endElement("","","columnStart");
		
		transhandler.startElement("","","columnEnd",attributes);
		transhandler.characters(data[2].trim().toCharArray(),0,data[2].length());
		transhandler.endElement("","","columnEnd");
		
//		transhandler.startElement("","","CODE",attributes);
//		transhandler.characters(code.toCharArray(),0,code.length());
//		transhandler.endElement("","","CODE");
//
//		transhandler.startElement("","","POSITION",attributes);
//		transhandler.characters(position.toCharArray(),0,position.length());
//		transhandler.endElement("","","POSITION");

		transhandler.endElement("","","issue");
	}
	
	public void xmlFileTagEnd() throws SAXException {
		transhandler.endElement("","","elements");
	}

	public void closeXML() throws SAXException {
		transhandler.endElement("","","analysisReport");
		transhandler.endDocument();  
	}
}
