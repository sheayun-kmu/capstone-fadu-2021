package io.jenkins.plugins.sample;

import java.io.BufferedReader;

import java.io.FileReader;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import org.xml.sax.InputSource;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import java.util.ArrayList;
import java.util.List;

public class xmlParser {
	BufferedReader read;
	//String filePath;
//	public xmlParser(String filePath) throws ParserConfigurationException, SAXException, IOException {
//		this.filePath = filePath;
//	}

	/* xml 파일에서 정보 뽑고 객체화 */
	// parsing 객체화 할건지 아니면 지금처럼 plain text로 뽑을건지 결정해야함
	public Report extractInfo(String filePath) throws ParserConfigurationException, SAXException, IOException {
		InputSource file = new InputSource(new FileReader(filePath));

		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		Document normalFile = db.parse(file);
		
		normalFile.getDocumentElement().normalize();
		
		Element root = normalFile.getDocumentElement();
		NodeList elements = root.getChildNodes();
		Report report = new Report();
		List<List> tmp = new ArrayList();
		if(elements.getLength() > 0) {
			for(int i = 0; i < elements.getLength(); i++) {
				NodeList children = elements.item(i).getChildNodes();  // 각 case별로 어느 자료구조로 저장할지 고려해야 함
				Actions action = new Actions();
				List li = new ArrayList();
				int order = 0;
				if(children.getLength() > 0) {
					for(int j = 0; j < children.getLength(); j++) {
						if(!children.item(j).getNodeName().equals("#text")) {
							if(order == 0) {
								action.setFile(children.item(j).getTextContent());
							}
							else if(order == 1) {
								action.setLine(children.item(j).getTextContent());
							}
							else if(order == 2) {
								action.setRule(children.item(j).getTextContent());
							}
							else if(order == 3) {
								action.setSeverity(children.item(j).getTextContent());
							}
							else if(order == 4) {
								action.setCode(children.item(j).getTextContent());
							}
							else if(order == 5) {
								action.setPosition(children.item(j).getTextContent());
							}
							else {
								order = -1;
							}
							order += 1;
//							li.add(children.item(j).getTextContent());
//							action.setFile(children.item(j).getTextContent());
//							action.setLine(children.item(j).getTextContent());
//							action.setRule(children.item(j).getTextContent());
//							action.setSeverity(children.item(j).getTextContent());
//							action.setCode(children.item(j).getTextContent());
//							action.setPosition(children.item(j).getTextContent());
						}
					}
					report.add(action.actions());
				}
//				ArrayList<ArrayList<String>> tmp = report.add(li);
//				List<List> t = report.add(li);
				
//				for(int p = 0; p < li.size(); p++) {
//					System.out.println(li.get(p));
//				}
				//System.out.println(tmp.get(0));
//				for(int p = 0; p < t.size(); p++) {
//					List m = t.get(p);
//					for(int q = 0; q < m.size(); q++) {
//						System.out.println(m.get(q));
//					}
//					System.out.println("---------");
//				}
			}	
			
		}	
//		List<List> t = report.tmp();
//		for(int p = 0; p < t.size(); p++) {
//			System.out.println(t.get(p));
//		}
		return report;
	}
	
//	/* builder에다가 요소들 set */
//	public Report convert(){
//		// 아마 report가 하나 생성되서 각 액션들을 report에 저장해 return하게 될 
//		Report report = new Report();
//		Actions action = new Actions();  // 각 case별로 action 구성\
//		
//		return report;
//	}
	
//	public static void main(String args[]) throws IOException, ParserConfigurationException, TransformerException, SAXException{
//		new xmlParser().extractInfo("./out.xml");
//	}
}

