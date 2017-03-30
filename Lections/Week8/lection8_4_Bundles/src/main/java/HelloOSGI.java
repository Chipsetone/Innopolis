import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

/**
 * @author Семакин Виктор
 */
public class HelloOSGI implements BundleActivator{
    @Override
    public void start(BundleContext context) throws Exception {
        System.out.println("hello world");
    }

    @Override
    public void stop(BundleContext context) throws Exception {
        System.out.println("bye bye");
    }
}
