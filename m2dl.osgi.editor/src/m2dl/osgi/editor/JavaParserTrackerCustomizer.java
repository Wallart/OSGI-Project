package m2dl.osgi.editor;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.util.tracker.ServiceTrackerCustomizer;

import m2dl.osgi.service.javaparser.JavaParser;

public class JavaParserTrackerCustomizer implements ServiceTrackerCustomizer<JavaParser, JavaParser> {

	private final BundleContext context;

	public JavaParserTrackerCustomizer(BundleContext _context) {
		context = _context;
	}

	@Override
	public JavaParser addingService(ServiceReference<JavaParser> serviceReference) {
		final JavaParser service = (JavaParser) context.getService(serviceReference);

		System.out.println("A new \"JavaParser\" appeared with the extention type = "+serviceReference.getProperty("my.metadata.type"));

		//service.replace();
		return service;
	}

	@Override
	public void modifiedService(ServiceReference<JavaParser> reference, JavaParser service) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removedService(ServiceReference<JavaParser> reference, JavaParser service) {
		// TODO Auto-generated method stub
		
	}
}
