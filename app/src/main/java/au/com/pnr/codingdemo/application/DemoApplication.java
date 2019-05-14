package au.com.pnr.codingdemo.application;

import android.app.Application;
import au.com.pnr.codingdemo.BuildConfig;
import au.com.pnr.codingdemo.restclient.ApiFactory;
import au.com.pnr.codingdemo.restclient.interfaces.ApiService;
import timber.log.Timber;

/**
 * The type Demo application.
 */
public class DemoApplication extends Application {

    private static ApiService apiService;

    @Override
    public void onCreate() {
        super.onCreate();
        // Logs are enabled only in debug mode for demo
        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }
    }

    /**
     * Gets api service.
     *
     * @return the api service
     */
    public static ApiService getApiService() {
        if (apiService == null) {
            apiService = ApiFactory.create();
        }
        return apiService;
    }
}
