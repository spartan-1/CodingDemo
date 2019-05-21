package au.com.pnr.codingdemo.restclient.interfaces;

import au.com.pnr.codingdemo.model.CountryInfo;
import au.com.pnr.codingdemo.restclient.util.Constants;
import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * The interface Api service.
 */
public interface ApiService {
    /**
     * Gets country info.
     *
     * @return the country info
     */
    @GET(Constants.INFO_FEED)
    Observable<CountryInfo> getCountryInfo();
}
