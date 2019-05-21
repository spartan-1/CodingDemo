package au.com.pnr.codingdemo.restclient.webservices;

import android.text.TextUtils;
import androidx.lifecycle.MutableLiveData;
import au.com.pnr.codingdemo.R;
import au.com.pnr.codingdemo.application.DemoApplication;
import au.com.pnr.codingdemo.model.CountryInfo;
import au.com.pnr.codingdemo.restclient.interfaces.ApiService;
import au.com.pnr.codingdemo.util.NetworkUtil;
import au.com.pnr.codingdemo.util.UIUtility;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Objects;


/**
 * The type Country info web service.
 */
public class CountryInfoWebService {

    /**
     * The Demo application.
     */
    @Inject
    DemoApplication demoApplication;

    /**
     * The Ui utility.
     */
    @Inject
    UIUtility uiUtility;

    /**
     * The Network utility.
     */
    @Inject
    NetworkUtil networkUtility;

    /**
     * The Api service.
     */
    @Inject
    ApiService apiService;

    /**
     * Instantiates a new Country info web service.
     */
    public CountryInfoWebService() {
        DemoApplication.getDemoAppComponent().inject(this);
    }

    /**
     * Load country information mutable live data.
     *
     * @param countryInfoMutableLiveData the country info mutable live data
     * @return the mutable live data
     */
    public MutableLiveData<CountryInfo> loadCountryInformation(final MutableLiveData<CountryInfo> countryInfoMutableLiveData) {
        if (!networkUtility.isOnline()) {
            uiUtility.showToast(demoApplication.getApplicationContext().getString(R.string.error_data_loading));
            clearData(countryInfoMutableLiveData);
            return countryInfoMutableLiveData;
        }
        apiService.getCountryInfo()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<CountryInfo>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(CountryInfo countryInfo) {
                        //Removing entries which don't have heading, description and image
                        Objects.requireNonNull(Objects.requireNonNull(countryInfo).getRows()).removeIf(x -> (TextUtils.isEmpty(x.getTitle()) && TextUtils.isEmpty(x.getDescription()) && TextUtils.isEmpty(x.getImageHref())));
                        countryInfoMutableLiveData.setValue(countryInfo);
                    }

                    @Override
                    public void onError(Throwable e) {
                        uiUtility.showToast(demoApplication.getApplicationContext().getString(R.string.error_server_response));
                        clearData(countryInfoMutableLiveData);
                    }

                    @Override
                    public void onComplete() {
                    }
                });
        return countryInfoMutableLiveData;
    }

    private void clearData(MutableLiveData<CountryInfo> countryInfoMutableLiveData) {
        if (countryInfoMutableLiveData != null) {
            //clearing the livedata to hold empty data
            Timber.d("Clearing data");
            countryInfoMutableLiveData.setValue(new CountryInfo("", new ArrayList<>()));
        }
    }
}
