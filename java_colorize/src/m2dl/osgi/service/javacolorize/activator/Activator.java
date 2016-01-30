package m2dl.osgi.service.javacolorize.activator;

import java.util.Dictionary;
import java.util.Hashtable;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

import m2dl.osgi.service.javacolorize.JavaColorize;
import m2dl.osgi.service.javacolorize.impl.JavaColorizeImpl;

public class Activator implements BundleActivator {

	public void start(BundleContext context) throws Exception {
		
		final JavaColorize javaParser = new JavaColorizeImpl();
		final Dictionary<String, String> properties = new Hashtable<String, String>();
		properties.put("name", "JavaColorize");
		
		context.registerService(JavaColorize.class.getName(), javaParser, properties);
		System.out.println("Starting Java Colorize service...");
	}
	
	public void stop(BundleContext context) throws Exception {
		System.out.println("Stopping Java Colorize service...");
	}

}
