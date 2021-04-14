package io.jenkins.plugins.sample;
import java.util.ArrayList;
import java.util.List;

public class Actions {
	String file;
	String line;
	String rule;
	String severity;
	String code;
	String position;
	List list = new ArrayList();

	public List actions(){
		list.add(this.file);
		list.add(this.line);
		list.add(this.rule);
		list.add(this.severity);
		list.add(this.code);
		list.add(this.position);
		return list;
	}
	public void setFile(String file) {
		this.file = file;
	}
	public void setLine(String line) {
		this.line = line;
	}
	public void setRule(String rule) {
		this.rule = rule;
	}
	public void setSeverity(String severity) {
		this.severity = severity;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public void setPosition(String position) {
		this.position = position;
	}
}
