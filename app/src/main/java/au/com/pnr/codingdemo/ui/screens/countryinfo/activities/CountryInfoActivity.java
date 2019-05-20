package au.com.pnr.codingdemo.ui.screens.countryinfo.activities;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import au.com.pnr.codingdemo.R;
import au.com.pnr.codingdemo.base.BaseActivity;
import au.com.pnr.codingdemo.ui.screens.countryinfo.fragments.CountryInfoListFragment;
import au.com.pnr.codingdemo.util.AppConstants;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * The type Country info activity.
 */
public class CountryInfoActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country_info);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        displayCountryInfoFeed(savedInstanceState);
    }

    private void displayCountryInfoFeed(Bundle savedInstanceState) {
        if (findViewById(R.id.content_layout) != null) {
            CountryInfoListFragment countryInfoListFragment;
            if (savedInstanceState == null) {
                countryInfoListFragment = new CountryInfoListFragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.content_layout, countryInfoListFragment, AppConstants.COUNTRYINFO_FRAGMENT_TAG_STRING).commit();
            }
        }
    }
}
