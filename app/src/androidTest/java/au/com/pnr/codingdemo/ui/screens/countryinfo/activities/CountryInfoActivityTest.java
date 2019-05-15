package au.com.pnr.codingdemo.ui.screens.countryinfo.activities;

import android.content.Intent;
import android.widget.TextView;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.rule.ActivityTestRule;
import au.com.pnr.codingdemo.R;
import au.com.pnr.codingdemo.RestServiceTestHelper;
import au.com.pnr.codingdemo.restclient.util.Constants;
import com.squareup.okhttp.mockwebserver.MockResponse;
import com.squareup.okhttp.mockwebserver.MockWebServer;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.*;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.core.AllOf.allOf;

/**
 * The type Country info activity test.
 */
@RunWith(AndroidJUnit4.class)
public class CountryInfoActivityTest {

    /**
     * The Country info activity activity test rule.
     */
    @Rule
    public ActivityTestRule<CountryInfoActivity> countryInfoActivityActivityTestRule = new ActivityTestRule<>(CountryInfoActivity.class, true, false);
    private MockWebServer mockWebServer;

    /**
     * Sets up.
     *
     * @throws Exception the exception
     */
    @Before
    public void setUp() throws Exception {
        mockWebServer = new MockWebServer();
        mockWebServer.start();
        Constants.BASE_URL = mockWebServer.url("/").toString();
    }

    /**
     * Test success response.
     *
     * @throws Exception the exception
     */
    @Test
    public void testSuccessResponse() throws Exception {
        String fileName = "success_response_test_data.json";
        mockWebServer.enqueue(new MockResponse()
                .setResponseCode(Constants.HTTP_OK)
                .setBody(RestServiceTestHelper.getStringFromFile(InstrumentationRegistry.getInstrumentation().getContext(), fileName)));
        Intent intent = new Intent();
        countryInfoActivityActivityTestRule.launchActivity(intent);
        onView(allOf(instanceOf(TextView.class),
                withParent(withResourceName("toolbar"))))
                .check(matches(withText("About Canada")));
    }

    /**
     * Test no data error message.
     *
     * @throws Exception the exception
     */
    @Test
    public void testNoDataErrorMessage() throws Exception {
        String fileName = "error_not_found_data.json";
        mockWebServer.enqueue(new MockResponse()
                .setResponseCode(Constants.HTTP_NOT_FOUND)
                .setBody(RestServiceTestHelper.getStringFromFile(InstrumentationRegistry.getInstrumentation().getContext(), fileName)));
        Intent intent = new Intent();
        countryInfoActivityActivityTestRule.launchActivity(intent);
        onView(withText(R.string.error_empty_recyclerview)).check(matches(isDisplayed()));
    }

    /**
     * Tear down.
     *
     * @throws Exception the exception
     */
    @After
    public void tearDown() throws Exception {
        mockWebServer.shutdown();
    }
}
