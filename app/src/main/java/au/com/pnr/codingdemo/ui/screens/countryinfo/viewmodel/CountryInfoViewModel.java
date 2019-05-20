package au.com.pnr.codingdemo.ui.screens.countryinfo.viewmodel;

import android.app.Application;
import android.text.TextUtils;
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
import timber.log.Timber;

import java.util.ArrayList;
import java.util.Objects;

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
        if (countryInfoMutableLiveData == null) {
            countryInfoMutableLiveData = new MutableLiveData<>();
        }
        if (!NetworkUtil.isOnline(getApplication())) {
            clearData();
            return countryInfoMutableLiveData;
        }
        loadCountryInformation();
        return countryInfoMutableLiveData;
    }

    private void loadCountryInformation() {
        ApiService api = DemoApplication.getApiService();
        Call<CountryInfo> call = api.getCountryInfo();
        call.enqueue(new Callback<CountryInfo>() {
            @Override
            public void onResponse(@NonNull Call<CountryInfo> call, @NonNull Response<CountryInfo> response) {
                //additional safe check
                if (response.body() != null) {
                    Objects.requireNonNull(Objects.requireNonNull(response.body()).getRows()).removeIf(x -> (TextUtils.isEmpty(x.getTitle()) && TextUtils.isEmpty(x.getDescription()) && TextUtils.isEmpty(x.getImageHref())));
                    countryInfoMutableLiveData.setValue(response.body());
                } else {
                    clearData();
                }
            }

            @Override
            public void onFailure(@NonNull Call<CountryInfo> call, @NonNull Throwable t) {
                NetworkUtil.displayServerErrorMessages(getApplication());
                Timber.d("Network call failure");
                clearData();
            }
        });
    }

    /**
     * Clear data.
     */
    private void clearData() {
        if (countryInfoMutableLiveData != null) {
            //clearing the livedata to hold empty data
            Timber.d("Clearing data");
            countryInfoMutableLiveData.setValue(new CountryInfo("", new ArrayList<>()));
        }
    }

}