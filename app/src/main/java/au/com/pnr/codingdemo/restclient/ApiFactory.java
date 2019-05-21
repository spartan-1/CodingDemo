package au.com.pnr.codingdemo.restclient;

import au.com.pnr.codingdemo.restclient.interfaces.ApiService;
import au.com.pnr.codingdemo.restclient.util.Constants;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import javax.inject.Singleton;
import java.util.concurrent.TimeUnit;

/**
 * The type Api factory.
 */
@Module
public class ApiFactory {


    private static OkHttpClient okHttpClient = new OkHttpClient().newBuilder()
            .connectTimeout(Constants.API_CONNECT_TIMEOUT_IN_SECONDS, TimeUnit.SECONDS)
            .readTimeout(Constants.API_READ_TIMEOUT_IN_SECONDS, TimeUnit.SECONDS)
            .writeTimeout(Constants.API_WRITE_TIMEOUT_IN_SECONDS, TimeUnit.SECONDS)
            .build();


    /**
     * Create api service.
     *
     * @return the api service
     */
    @Provides
    @Singleton
    static ApiService create() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .build();
        return retrofit.create(ApiService.class);
    }

}
