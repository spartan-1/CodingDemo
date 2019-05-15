package au.com.pnr.codingdemo.ui.screens.countryinfo.activities;

import android.view.View;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;
import au.com.pnr.codingdemo.R;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertNotNull;

/**
 * The type Contry info activity views test.
 */
@RunWith(AndroidJUnit4.class)
public class ContryInfoActivityViewsTest {

    /**
     * The countryInfoActivityActivityTestRule.
     */
    @Rule
    public ActivityTestRule<CountryInfoActivity> countryInfoActivityActivityTestRule = new ActivityTestRule<>(CountryInfoActivity.class);
    private CountryInfoActivity countryInfoActivity = null;

    /**
     * Sets up.
     *
     * @throws Exception the exception
     */
    @Before
    public void setUp() throws Exception {
        countryInfoActivity = countryInfoActivityActivityTestRule.getActivity();
    }

    /**
     * Test launch.
     *
     * @throws Exception the exception
     */
    @Test
    public void testLaunch() throws Exception {
        View view = countryInfoActivity.findViewById(R.id.activity_country_info_constraint_layout);
        assertNotNull(view);
    }

    /**
     * Test tool bar.
     *
     * @throws Exception the exception
     */
    @Test
    public void testToolBar() throws Exception {
        View view = countryInfoActivity.findViewById(R.id.toolbar);
        assertNotNull(view);

    }

    /**
     * Test container.
     *
     * @throws Exception the exception
     */
    @Test
    public void testContainer() throws Exception {
        View view = countryInfoActivity.findViewById(R.id.content_layout);
        assertNotNull(view);

    }

    /**
     * Tear down.
     *
     * @throws Exception the exception
     */
    @After
    public void tearDown() throws Exception {
        countryInfoActivity = null;
    }
}
