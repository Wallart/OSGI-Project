package m2dl.osgi.service.javaparser.activator;

import java.util.Dictionary;
import java.util.Hashtable;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

import m2dl.osgi.service.javaparser.JavaParser;
import m2dl.osgi.service.javaparser.impl.JavaParserImpl;

public class Activator implements BundleActivator {

	public void start(BundleContext context) throws Exception {

		final JavaParser javaParser = new JavaParserImpl();
		final Dictionary<String, String> properties = new Hashtable<String, String>();
		properties.put("name", "JavaParser");
		
		context.registerService(JavaParser.class.getName(), javaParser, properties);
		System.out.println("Starting Java Parsing service...");
	}
	
	public void stop(BundleContext context) throws Exception {
		System.out.println("Stopping Java Parsing service...");
	}

}
