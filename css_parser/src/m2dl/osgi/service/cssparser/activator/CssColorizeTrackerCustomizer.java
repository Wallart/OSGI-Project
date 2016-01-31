package m2dl.osgi.service.cssparser.activator;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.util.tracker.ServiceTrackerCustomizer;

import m2dl.osgi.service.csscolorize.CssColorize;

public class CssColorizeTrackerCustomizer implements ServiceTrackerCustomizer<CssColorize, CssColorize> {

	private final BundleContext context;

	public CssColorizeTrackerCustomizer(BundleContext _context) {
		context = _context;
	}

	@Override
	public CssColorize addingService(ServiceReference<CssColorize> serviceReference) {
		final CssColorize service = (CssColorize) context.getService(serviceReference);

		System.out.println("A new \"CssColorize\" appeared with the extention type = "+serviceReference.getProperty("my.metadata.type"));

		//service.replace();
		return service;
	}

	@Override
	public void modifiedService(ServiceReference<CssColorize> reference, CssColorize service) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removedService(ServiceReference<CssColorize> reference, CssColorize service) {
		// TODO Auto-generated method stub
		
	}
}