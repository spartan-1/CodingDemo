package au.com.pnr.codingdemo.di;

import au.com.pnr.codingdemo.application.DemoApplicationModule;
import au.com.pnr.codingdemo.restclient.ApiFactory;
import au.com.pnr.codingdemo.restclient.webservices.CountryInfoWebService;
import au.com.pnr.codingdemo.util.NetworkUtil;
import au.com.pnr.codingdemo.util.UIUtility;
import dagger.Component;

import javax.inject.Singleton;

/**
 * The interface Demo app component.
 */
@Singleton
@Component(modules = {ApiFactory.class, DemoApplicationModule.class, UIUtility.class, NetworkUtil.class})
public interface DemoAppComponent {

    /**
     * Inject.
     *
     * @param countryInfoWebService the country info web service
     */
    void inject(CountryInfoWebService countryInfoWebService);

    /**
     * Inject.
     *
     * @param networkUtil the network util
     */
    void inject(NetworkUtil networkUtil);

    /**
     * Inject.
     *
     * @param uiUtility the ui utility
     */
    void inject(UIUtility uiUtility);


    /**
     * Inject.
     *
     * @param apiFactory the api factory
     */
    void inject(ApiFactory apiFactory);
}
