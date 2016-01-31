package m2dl.osgi.editor;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.util.tracker.ServiceTrackerCustomizer;

import m2dl.osgi.service.cssparser.CssParser;

public class CssParserTrackerCustomizer implements ServiceTrackerCustomizer<CssParser, CssParser> {

	private final BundleContext context;

	public CssParserTrackerCustomizer(BundleContext _context) {
		context = _context;
	}

	@Override
	public CssParser addingService(ServiceReference<CssParser> serviceReference) {
		final CssParser service = (CssParser) context.getService(serviceReference);

		System.out.println("A new \"CssParser\" appeared with the extention type = "+serviceReference.getProperty("my.metadata.type"));

		//service.replace();
		return service;
	}

	@Override
	public void modifiedService(ServiceReference<CssParser> reference, CssParser service) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removedService(ServiceReference<CssParser> reference, CssParser service) {
		// TODO Auto-generated method stub
		
	}
}
