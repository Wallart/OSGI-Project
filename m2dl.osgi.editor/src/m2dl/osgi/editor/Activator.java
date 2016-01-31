package m2dl.osgi.editor;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.util.tracker.ServiceTracker;
import org.osgi.util.tracker.ServiceTrackerCustomizer;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import m2dl.osgi.service.javaparser.JavaParser;
import m2dl.osgi.service.cssparser.CssParser;

public class Activator implements BundleActivator {

	/**
	 * To know if the window is running.
	 */
	private static Boolean windowIsRunning = false;
	/**
	 * The logger.
	 */
	public static final Logger logger = LogManager.getLogger(Activator.class);

	@Override
	public void start(BundleContext bundleContext) throws Exception {
		
		final ServiceTrackerCustomizer<JavaParser, JavaParser> javaTrackerCustomizer = new JavaParserTrackerCustomizer(bundleContext);
		final ServiceTracker<JavaParser, JavaParser> javaService = new ServiceTracker<JavaParser, JavaParser>(bundleContext, JavaParser.class.getName(), javaTrackerCustomizer);
		javaService.open();
		
		ServiceReference<?>[] javaReferences = bundleContext.getServiceReferences(JavaParser.class.getName(), "(name=JavaParser)");
		JavaParser javaParser = (JavaParser) bundleContext.getService(javaReferences[0]);
		
		final ServiceTrackerCustomizer<CssParser, CssParser> cssTrackerCustomizer = new CssParserTrackerCustomizer(bundleContext);
		final ServiceTracker<CssParser, CssParser> cssService = new ServiceTracker<CssParser, CssParser>(bundleContext, CssParser.class.getName(), cssTrackerCustomizer);
		cssService.open();
		
		ServiceReference<?>[] cssReferences = bundleContext.getServiceReferences(CssParser.class.getName(), "(name=CssParser)");
		CssParser cssParser = (CssParser) bundleContext.getService(cssReferences[0]);
		
		/*
		 * Configuring the logger.
		 */
		BasicConfigurator.configure();
		/*
		 * Starting only one JavaFX window.
		 */

		if (!windowIsRunning) {
			new JFXPanel();
			Platform.runLater(() -> {
				try {
					/*
					 * Init the JavaFX nodes.
					 */
					final FXMLLoader loader = new FXMLLoader(
							CodeViewerController.class.getResource("main-window-exercice.fxml"));
					loader.load();

					final VBox root = (VBox) loader.getRoot();
					final Stage primaryStage = new Stage();
					final Scene scene = new Scene(root, 400, 400);

					final CodeViewerController controller = loader.getController();
					controller.setJavaParser(javaParser);
					controller.setCssParser(cssParser);

					controller.setPrimaryStage(primaryStage);

					/*
					 * Setup the window
					 */
					primaryStage.setTitle("The first OSGi modulable text editor");
					primaryStage.setScene(scene);
					/*
					 * Display the window.
					 */
					primaryStage.show();
					windowIsRunning = true;
					logger.info("The editor is running");

				} catch (final Exception e) {
					logger.debug("Error during loading the window");
					e.printStackTrace();
				}
			});
		}
	}

	@Override
	public void stop(BundleContext context) throws Exception {
		/*
		 * Stopping the editor.
		 */
		logger.info("The editor will be stopted.");
	}

}

/*
 * Get the menu bar from the window. File "main-window-exercice.java", line 15:
 * <OSGiMenuBar fx:id="MenuActions" VBox.vgrow="NEVER">
 */
// final OSGiMenuBar osgiMenuBar = (OSGiMenuBar)
// scene.lookup("#MenuActions");
// /*
// * Export the OSGiMenuBar service.
// */
// final Hashtable<String, String> dictionnary = new
// Hashtable<>();
// dictionnary.put("m2dl.osgi.editor.javafx-node",
// OSGiMenuBar.class.getName());
// bundleContext.registerService(IOSGiMenuBar.class.getName(),
// osgiMenuBar, dictionnary);
// logger.info(osgiMenuBar + " is now available");
// /*
// * TODO THE OTHER OSGi SERVICES BASED ON THE WINDOW HAVE
// TO
// * BE DECLARED HERE!
// */
//
