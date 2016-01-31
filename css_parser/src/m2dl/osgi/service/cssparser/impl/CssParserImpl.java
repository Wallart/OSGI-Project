package m2dl.osgi.service.cssparser.impl;

import java.util.ArrayList;

import m2dl.osgi.service.csscolorize.CssColorize;
import m2dl.osgi.service.cssparser.CssParser;

public class CssParserImpl implements CssParser{

	private ArrayList<String> keywords;
	private CssColorize colorizer;
	
	public CssParserImpl() {
		keywords = new ArrayList<String>();
		
		keywords.add("background-color");
        keywords.add("font-family");
        keywords.add("font-size");
        keywords.add("text-align");
        keywords.add("background");
        keywords.add("body");
        keywords.add("color");	
	}
	
	@Override
	public String replace(String data) {
		for(String word : keywords) {
            data = data.replaceAll("(?!~)" + word +"(?<!~)", ":keyword{~" + word + "~}");
        }
		
    	return colorizer.colorize(data);
	}

	@Override
	public void setColorize(CssColorize colorizer) {
		this.colorizer = colorizer;
	}

}
