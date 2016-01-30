package m2dl.osgi.service.javaparser.activator;

import java.util.Dictionary;
import java.util.Hashtable;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.util.tracker.ServiceTracker;
import org.osgi.util.tracker.ServiceTrackerCustomizer;

import m2dl.osgi.service.javacolorize.JavaColorize;
import m2dl.osgi.service.javaparser.JavaParser;
import m2dl.osgi.service.javaparser.impl.JavaParserImpl;

public class Activator implements BundleActivator {

	public void start(BundleContext context) throws Exception {

		final ServiceTrackerCustomizer<JavaColorize, JavaColorize> trackerCustomizer = new JavaColorizeTrackerCustomizer(context);
		final ServiceTracker<JavaColorize, JavaColorize> mainService = new ServiceTracker<JavaColorize, JavaColorize>(context, JavaColorize.class.getName(), trackerCustomizer);
		mainService.open();
		
		ServiceReference<?>[] references = context.getServiceReferences(JavaColorize.class.getName(), "(name=JavaColorize)");
		JavaColorize colorizer = (JavaColorize) context.getService(references[0]);
		

		final JavaParser javaParser = new JavaParserImpl();
		javaParser.setColorize(colorizer);
		final Dictionary<String, String> properties = new Hashtable<String, String>();
		properties.put("name", "JavaParser");
		
		context.registerService(JavaParser.class.getName(), javaParser, properties);
		System.out.println("Starting Java Parsing service...");
	}
	
	public void stop(BundleContext context) throws Exception {
		System.out.println("Stopping Java Parsing service...");
	}

}
