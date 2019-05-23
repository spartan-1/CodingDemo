package au.com.pnr.codingdemo.application;

import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;

/**
 * The type Demo application module.
 */
@Module
public class DemoApplicationModule {

    /**
     * The Demo application.
     */
    private final DemoApplication demoApplication;

    /**
     * Instantiates a new Demo application module.
     *
     * @param demoApplication the demo application
     */
    DemoApplicationModule(DemoApplication demoApplication) {
        this.demoApplication = demoApplication;
    }

    /**
     * Provides demo application demo application.
     *
     * @return the demo application
     */
    @Provides
    @Singleton
    DemoApplication providesDemoApplication() {
        return demoApplication;
    }
}
