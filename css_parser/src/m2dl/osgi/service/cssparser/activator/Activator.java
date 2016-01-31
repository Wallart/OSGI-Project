package m2dl.osgi.service.cssparser.activator;

import java.util.Dictionary;
import java.util.Hashtable;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.util.tracker.ServiceTracker;
import org.osgi.util.tracker.ServiceTrackerCustomizer;

import m2dl.osgi.service.csscolorize.CssColorize;
import m2dl.osgi.service.cssparser.CssParser;
import m2dl.osgi.service.cssparser.impl.CssParserImpl;


public class Activator implements BundleActivator {

	public void start(BundleContext context) throws Exception {
		final ServiceTrackerCustomizer<CssColorize, CssColorize> trackerCustomizer = new CssColorizeTrackerCustomizer(context);
		final ServiceTracker<CssColorize, CssColorize> mainService = new ServiceTracker<CssColorize, CssColorize>(context, CssColorize.class.getName(), trackerCustomizer);
		mainService.open();
		
		ServiceReference<?>[] references = context.getServiceReferences(CssColorize.class.getName(), "(name=CssColorize)");
		CssColorize colorizer = (CssColorize) context.getService(references[0]);
		

		final CssParser cssParser = new CssParserImpl();
		cssParser.setColorize(colorizer);
		final Dictionary<String, String> properties = new Hashtable<String, String>();
		properties.put("name", "CssParser");
		
		context.registerService(CssParser.class.getName(), cssParser, properties);
		System.out.println("Starting CSS Parsing service...");
	}
	
	public void stop(BundleContext context) throws Exception {
		System.out.println("Stopping CSS Parsing service...");
	}

}
