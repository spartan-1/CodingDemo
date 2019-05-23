package au.com.pnr.codingdemo.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import au.com.pnr.codingdemo.application.DemoApplication;
import dagger.Module;
import dagger.Provides;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * The type Network util.
 */
@Module
public class NetworkUtil {

    @Provides
    @Singleton
    NetworkUtil providesNetworkUtility() {
        return new NetworkUtil();
    }

    @Inject
    DemoApplication demoApplication;

    public NetworkUtil() {
    }

    public boolean isOnline() {
        DemoApplication.getDemoAppComponent().inject(this);
        ConnectivityManager conMgr = (ConnectivityManager) demoApplication.getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        assert conMgr != null;
        NetworkInfo netInfo = conMgr.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnected() && netInfo.isAvailable();
    }
}
