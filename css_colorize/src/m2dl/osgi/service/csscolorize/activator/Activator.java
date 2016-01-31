package m2dl.osgi.service.csscolorize.activator;

import java.util.Dictionary;
import java.util.Hashtable;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

import m2dl.osgi.service.csscolorize.CssColorize;
import m2dl.osgi.service.csscolorize.impl.CssColorizeImpl;

public class Activator implements BundleActivator {

	public void start(BundleContext context) throws Exception {

		final CssColorize colorizer = new CssColorizeImpl();
		final Dictionary<String, String> properties = new Hashtable<String, String>();
		properties.put("name", "CssColorize");
		
		context.registerService(CssColorize.class.getName(), colorizer, properties);
		System.out.println("Starting CSS Colorize service...");
	}
	
	public void stop(BundleContext context) throws Exception {
		System.out.println("Stopping CSS Colorize service...");
	}

}
