package au.com.pnr.codingdemo.ui.screens.countryinfo.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;
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
import au.com.pnr.codingdemo.util.NetworkUtil;
import butterknife.BindView;
import butterknife.ButterKnife;
import timber.log.Timber;

import java.util.ArrayList;
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

    /**
     * The Error message.
     */
    @BindView(R.id.text_loading_error)
    AppCompatTextView errorMessage;

    /**
     * The Progress bar.
     */
    @BindView(R.id.data_loading_progress)
    ProgressBar progressBar;

    private CountryInfoViewModel countryInfoViewModel;

    private CountryInfoAdapter countryInfoAdapter;

    private Observer<CountryInfo> infoObserver = countryInfo -> {
        mRefreshFeed.setRefreshing(false);
        countryInfoAdapter.notifyDataSetChanged();
        if (countryInfo.getRows() != null && countryInfo.getRows().size() > 0) {
            countryInfoAdapter.updateData(countryInfo.getRows());
            showDataView();
        } else {
            handleDataError();
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

    private void initRecyclerView() {
        countryInfoAdapter = new CountryInfoAdapter(new ArrayList<>());
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(countryInfoAdapter);
    }

    private void handleDataError() {
        if (!NetworkUtil.isOnline(Objects.requireNonNull(getContext()))) {
            Toast.makeText(getContext(), R.string.error_data_loading, Toast.LENGTH_SHORT).show();
        }
        showErrorView();
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
        ButterKnife.bind(this, view);
        countryInfoViewModel = ViewModelProviders.of(this).get(CountryInfoViewModel.class);
        initRecyclerView();
        showProgressView();
        if (getActivity() != null) {
            countryInfoViewModel.getCountryInfo().observe(getActivity(), infoObserver);
        }
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        Timber.d(" onResume ");
        mRefreshFeed.setOnRefreshListener(() -> countryInfoViewModel.getCountryInfo());
    }

    @Override
    public void onDestroyView() {
        Timber.d(" onDestroyView ");
        super.onDestroyView();
    }

    private void showProgressView() {
        progressBar.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.GONE);
        errorMessage.setVisibility(View.GONE);
    }

    private void showErrorView() {
        progressBar.setVisibility(View.GONE);
        recyclerView.setVisibility(View.GONE);
        errorMessage.setVisibility(View.VISIBLE);
    }

    private void showDataView() {
        progressBar.setVisibility(View.GONE);
        recyclerView.setVisibility(View.VISIBLE);
        errorMessage.setVisibility(View.GONE);
    }
}