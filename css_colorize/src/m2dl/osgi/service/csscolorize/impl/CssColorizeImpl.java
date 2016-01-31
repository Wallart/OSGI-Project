package m2dl.osgi.service.csscolorize.impl;

import m2dl.osgi.service.csscolorize.CssColorize;

public class CssColorizeImpl implements CssColorize {
	public String colorize(String data) {
		String deb = ":keyword\\{~", fin = "~\\}";
		data = data.replaceAll(deb, "<font color=\"blue\">");
        data = data.replaceAll(fin,"</font>");
        return data;
	}
}
