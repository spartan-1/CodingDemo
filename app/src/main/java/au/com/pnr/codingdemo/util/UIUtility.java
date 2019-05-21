package au.com.pnr.codingdemo.util;

import android.widget.Toast;
import au.com.pnr.codingdemo.application.DemoApplication;
import dagger.Module;
import dagger.Provides;

import javax.inject.Inject;
import javax.inject.Singleton;

@Module
public class UIUtility {

    @Provides
    @Singleton
    UIUtility providesUiUtility() {
        return new UIUtility();
    }

    @Inject
    DemoApplication demoApplication;

    public UIUtility() {
    }

    public void showToast(String message) {
        DemoApplication.getDemoAppComponent().inject(this);
        Toast.makeText(demoApplication.getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }
}
