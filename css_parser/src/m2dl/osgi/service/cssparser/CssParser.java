package m2dl.osgi.service.cssparser;

import m2dl.osgi.service.csscolorize.CssColorize;

public interface CssParser {
	public String replace(String data);
	public void setColorize(CssColorize colorizer);

}
