package m2dl.osgi.service.javaparser;

import m2dl.osgi.service.javacolorize.JavaColorize;

public interface JavaParser {
	public String replace(String data);
	public void setColorize(JavaColorize colorizer);
}
