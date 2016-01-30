package m2dl.osgi.service.javacolorize.impl;

import m2dl.osgi.service.javacolorize.JavaColorize;

public class JavaColorizeImpl implements JavaColorize {

	@Override
	public String colorize(String data) {
		String deb = ":keyword\\{~";
		String fin = "~\\}";

		data = data.replaceAll(deb, "<font color=\"blue\">");
		data = data.replaceAll(fin,"</font>");
		return data;
	}

}
