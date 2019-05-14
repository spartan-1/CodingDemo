package au.com.pnr.codingdemo.restclient;

import au.com.pnr.codingdemo.restclient.interfaces.ApiService;
import au.com.pnr.codingdemo.restclient.util.Constants;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * The type Api factory.
 */
public class ApiFactory {

    /**
     * Create api service.
     *
     * @return the api service
     */
    public static ApiService create() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        return retrofit.create(ApiService.class);
    }

}
