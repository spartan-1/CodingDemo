package au.com.pnr.codingdemo.ui.viewholders;

import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import au.com.pnr.codingdemo.databinding.ListItemCountryInfoBinding;

/**
 * The type Information view holder.
 */
public class InformationViewHolder extends RecyclerView.ViewHolder {

    private final ViewDataBinding binding;

    /**
     * Instantiates a new Information view holder.
     *
     * @param patientListItemBinding the patient list item binding
     */
    public InformationViewHolder(ListItemCountryInfoBinding patientListItemBinding) {
        super(patientListItemBinding.getRoot());
        binding = patientListItemBinding;
    }

    /**
     * Gets binding.
     *
     * @return the binding
     */
    public ViewDataBinding getBinding() {
        return binding;
    }
}