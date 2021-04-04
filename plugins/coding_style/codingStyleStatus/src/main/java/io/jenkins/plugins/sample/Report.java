package io.jenkins.plugins.sample;
import java.util.ArrayList;
import java.util.List;

public class Report {
/* 각 요소마다 list로 저장 */
	List<List> element = new ArrayList();
	public List<List> add(List list) {
		element.add(list);
//		for(int i = 0; i < element.size(); i++) {
//			System.out.println(element.get(i));
//		}
		return element;
	}
	
	public List<List> tmp(){
		return element;
	}
}
