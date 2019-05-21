package au.com.pnr.codingdemo.ui.screens.countryinfo.viewmodel;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import au.com.pnr.codingdemo.model.CountryInfo;
import au.com.pnr.codingdemo.restclient.webservices.CountryInfoWebService;

/**
 * The type Country info view model.
 */
public class CountryInfoViewModel extends AndroidViewModel {

    private MutableLiveData<CountryInfo> countryInfoMutableLiveData;

    private CountryInfoWebService countryInfoWebService;

    /**
     * Instantiates a new Country info view model.
     *
     * @param application the application
     */
    public CountryInfoViewModel(@NonNull Application application) {
        super(application);
        countryInfoWebService = new CountryInfoWebService();
        countryInfoMutableLiveData = new MutableLiveData<>();
    }

    /**
     * Gets country info.
     *
     * @return the country info
     */
    public LiveData<CountryInfo> getCountryInfo() {
        return countryInfoWebService.loadCountryInformation(countryInfoMutableLiveData);
    }

}