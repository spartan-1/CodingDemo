package au.com.pnr.codingdemo.application;

import android.app.Application;
import au.com.pnr.codingdemo.BuildConfig;
import au.com.pnr.codingdemo.di.DaggerDemoAppComponent;
import au.com.pnr.codingdemo.di.DemoAppComponent;
import au.com.pnr.codingdemo.util.NetworkUtil;
import au.com.pnr.codingdemo.util.UIUtility;
import timber.log.Timber;

/**
 * The type Demo application.
 */
public class DemoApplication extends Application {

    private static DemoAppComponent demoAppComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        // Logs are enabled only in debug mode for demo
        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }
        demoAppComponent = DaggerDemoAppComponent.builder()
                .demoApplicationModule(new DemoApplicationModule(this))
                .networkUtil(new NetworkUtil())
                .uIUtility(new UIUtility())
                .build();
    }

    /**
     * Gets demo app component.
     *
     * @return the demo app component
     */
    public static DemoAppComponent getDemoAppComponent() {
        return demoAppComponent;
    }
}
