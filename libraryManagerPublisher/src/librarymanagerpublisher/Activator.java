package librarymanagerpublisher;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

public class Activator implements BundleActivator {
    private ServiceRegistration serviceRegistration;

    @Override
    public void start(BundleContext context) throws Exception {
        System.out.println("Library Management service up and running");
        LibraryManagerInterface libraryManager = new LibraryManagerImpl();
        serviceRegistration = context.registerService(LibraryManagerInterface.class.getName(), libraryManager, null);
    }

    @Override
    public void stop(BundleContext context) throws Exception {
        System.out.println("Library Management service shut down");
        serviceRegistration.unregister();
    }
}
