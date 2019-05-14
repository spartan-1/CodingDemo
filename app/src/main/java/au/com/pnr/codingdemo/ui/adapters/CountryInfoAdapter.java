package au.com.pnr.codingdemo.ui.adapters;


import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;
import au.com.pnr.codingdemo.BR;
import au.com.pnr.codingdemo.R;
import au.com.pnr.codingdemo.databinding.ListItemCountryInfoBinding;
import au.com.pnr.codingdemo.model.InformationData;
import au.com.pnr.codingdemo.ui.viewholders.InformationViewHolder;
import timber.log.Timber;

import java.util.List;

/**
 * The type Country info adapter.
 */
public class CountryInfoAdapter extends RecyclerView.Adapter<InformationViewHolder> {

    private List<InformationData> informationDataList;

    /**
     * Instantiates a new Country info adapter.
     *
     * @param informationDataList the information data list
     */
    public CountryInfoAdapter(List<InformationData> informationDataList) {
        this.informationDataList = informationDataList;
    }

    @NonNull
    @Override
    public InformationViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        Timber.d(" onCreateViewHolder ");
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        ListItemCountryInfoBinding binding = DataBindingUtil.inflate(layoutInflater, R.layout.list_item_country_info, viewGroup, false);
        return new InformationViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull InformationViewHolder informationViewHolder, int i) {
        Timber.d(" onBindViewHolder ");
        informationViewHolder.getBinding().setVariable(BR.countryInfo, informationDataList.get(i));
        ListItemCountryInfoBinding viewBinding = (ListItemCountryInfoBinding) informationViewHolder.getBinding();
        viewBinding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return informationDataList.size();
    }
}
