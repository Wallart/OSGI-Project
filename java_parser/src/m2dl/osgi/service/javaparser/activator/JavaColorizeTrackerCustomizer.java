package m2dl.osgi.service.javaparser.activator;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.util.tracker.ServiceTrackerCustomizer;

import m2dl.osgi.service.javacolorize.JavaColorize;

public class JavaColorizeTrackerCustomizer implements ServiceTrackerCustomizer<JavaColorize, JavaColorize> {

	private final BundleContext context;

	public JavaColorizeTrackerCustomizer(BundleContext _context) {
		context = _context;
	}

	@Override
	public JavaColorize addingService(ServiceReference<JavaColorize> serviceReference) {
		final JavaColorize service = (JavaColorize) context.getService(serviceReference);

		System.out.println("A new \"JavaColorize\" appeared with the extention type = "+serviceReference.getProperty("my.metadata.type"));

		//service.replace();
		return service;
	}

	@Override
	public void modifiedService(ServiceReference<JavaColorize> reference, JavaColorize service) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removedService(ServiceReference<JavaColorize> reference, JavaColorize service) {
		// TODO Auto-generated method stub
		
	}
}