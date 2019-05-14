package au.com.pnr.codingdemo.ui.screens.countryinfo.activities;

import android.os.Bundle;
import androidx.annotation.Nullable;
import au.com.pnr.codingdemo.R;
import au.com.pnr.codingdemo.base.BaseActivity;
import au.com.pnr.codingdemo.ui.screens.countryinfo.fragments.CountryInfoListFragment;
import timber.log.Timber;

/**
 * The type Country info activity.
 */
public class CountryInfoActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Timber.d(" onCreate ");
        setContentView(R.layout.activity_country_info);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Timber.d(" onResume ");
        displayCountryInfoFeed();
    }

    private void displayCountryInfoFeed() {
        getSupportFragmentManager().beginTransaction().replace(R.id.content_layout, CountryInfoListFragment.newInstance()).commit();
    }
}
