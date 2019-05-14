package au.com.pnr.codingdemo.ui.screens.countryinfo.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import au.com.pnr.codingdemo.R;
import au.com.pnr.codingdemo.base.BaseFragment;
import au.com.pnr.codingdemo.model.CountryInfo;
import au.com.pnr.codingdemo.ui.adapters.CountryInfoAdapter;
import au.com.pnr.codingdemo.ui.screens.countryinfo.viewmodel.CountryInfoViewModel;
import butterknife.BindView;
import timber.log.Timber;

import java.util.Objects;

/**
 * The type Country info list fragment.
 */
public class CountryInfoListFragment extends BaseFragment {

    /**
     * The Recycler view.
     */
    @BindView(R.id.rv_country_info)
    RecyclerView recyclerView;

    /**
     * The M refresh feed.
     */
    @BindView(R.id.swipe_layout_information)
    SwipeRefreshLayout mRefreshFeed;

    private CountryInfoViewModel countryInfoViewModel;

    private CountryInfoAdapter countryInfoAdapter;
    private Observer<CountryInfo> infoObserver = countryInfo -> {
        mRefreshFeed.setRefreshing(false);
        if (countryInfo != null) {
            countryInfoAdapter = new CountryInfoAdapter(Objects.requireNonNull(countryInfoViewModel.getCountryInfo().getValue()).getRows());
            recyclerView.setHasFixedSize(true);
            LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setAdapter(countryInfoAdapter);
        } else {
            Toast.makeText(getContext(), "error", Toast.LENGTH_SHORT).show();
        }
    };

    /**
     * New instance country info list fragment.
     *
     * @return the country info list fragment
     */
    public static CountryInfoListFragment newInstance() {
        return new CountryInfoListFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_country_info_list, container, false);
        Timber.d(" onCreateView ");
        countryInfoViewModel = ViewModelProviders.of(this).get(CountryInfoViewModel.class);
        recyclerView = view.findViewById(R.id.rv_country_info);
        mRefreshFeed = view.findViewById(R.id.swipe_layout_information);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        Timber.d(" onResume ");
        countryInfoViewModel.getCountryInfo().observe(this, infoObserver);
        mRefreshFeed.setOnRefreshListener(() -> {
            countryInfoViewModel.clearData();
            countryInfoViewModel.getCountryInfo().observe(CountryInfoListFragment.this, infoObserver);
        });
    }

    @Override
    public void onDestroyView() {
        Timber.d(" onDestroyView ");
        super.onDestroyView();
    }
}