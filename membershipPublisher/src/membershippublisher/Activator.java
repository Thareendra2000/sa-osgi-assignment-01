package membershippublisher;

import membershippublisher.membershipImpl;
import membershippublisher.membershipInterface;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;


public class Activator implements BundleActivator {

    private ServiceRegistration membershipServiceRegistration;

    public void start(BundleContext context) throws Exception {
        System.out.println("Membership service up and running");

        // Register Membership service
        membershipInterface membershipInterface = new membershipImpl();
        membershipServiceRegistration = context.registerService(membershipInterface.class.getName(), membershipInterface, null);
    }

    public void stop(BundleContext context) throws Exception {
        System.out.println("Membership service shut down");
        membershipServiceRegistration.unregister();
    }
}
