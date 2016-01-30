package m2dl.osgi.service.javaparser.impl;

import java.util.ArrayList;

import m2dl.osgi.service.javacolorize.JavaColorize;
import m2dl.osgi.service.javaparser.JavaParser;

public class JavaParserImpl implements JavaParser {
	
	private ArrayList<String> keywords;
	private JavaColorize colorizer;
	
	public JavaParserImpl() {
		
		keywords = new ArrayList<String>();
		keywords.add("package");
		
		keywords.add("public");
		keywords.add("private");
		keywords.add("protected");
		
		keywords.add("class");
		keywords.add("interface");
		keywords.add("implements");
		keywords.add("extends");
		
		keywords.add("int");
		keywords.add("float");
		keywords.add("double");
		keywords.add("boolean");
	}
	
	@Override
	public String replace(String data) {
		for(String word : keywords) {
		    data = data.replaceAll("(?!~)" + word +"(?<!~)", ":keyword{~" + word + "~}");
		}
		
		return colorizer.colorize(data);
	}

	public void setColorize(JavaColorize colorizer) {
		this.colorizer = colorizer;
	}
}
