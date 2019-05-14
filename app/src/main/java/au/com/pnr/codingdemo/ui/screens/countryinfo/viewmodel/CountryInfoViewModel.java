package au.com.pnr.codingdemo.ui.screens.countryinfo.viewmodel;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import au.com.pnr.codingdemo.application.DemoApplication;
import au.com.pnr.codingdemo.model.CountryInfo;
import au.com.pnr.codingdemo.restclient.interfaces.ApiService;
import au.com.pnr.codingdemo.util.NetworkUtil;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * The type Country info view model.
 */
public class CountryInfoViewModel extends AndroidViewModel {
    private MutableLiveData<CountryInfo> countryInfoMutableLiveData;

    /**
     * Instantiates a new Country info view model.
     *
     * @param application the application
     */
    public CountryInfoViewModel(@NonNull Application application) {
        super(application);
    }

    /**
     * Gets country info.
     *
     * @return the country info
     */
    public LiveData<CountryInfo> getCountryInfo() {
        if (!NetworkUtil.isOnline(getApplication())) {

        }
        if (countryInfoMutableLiveData == null) {
            countryInfoMutableLiveData = new MutableLiveData<>();
            loadCountryInformation();
        }
        return countryInfoMutableLiveData;
    }

    private void loadCountryInformation() {
        ApiService api = DemoApplication.getApiService();
        Call<CountryInfo> call = api.getCountryInfo();
        call.enqueue(new Callback<CountryInfo>() {
            @Override
            public void onResponse(@NonNull Call<CountryInfo> call, @NonNull Response<CountryInfo> response) {
                countryInfoMutableLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(@NonNull Call<CountryInfo> call, @NonNull Throwable t) {
                countryInfoMutableLiveData.setValue(null);
            }
        });
    }

    /**
     * Clear data.
     */
    public void clearData() {
        countryInfoMutableLiveData = null;
    }

}