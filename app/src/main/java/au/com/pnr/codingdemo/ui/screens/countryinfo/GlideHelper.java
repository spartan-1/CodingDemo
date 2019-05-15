package au.com.pnr.codingdemo.ui.screens.countryinfo;

import android.widget.ImageView;
import androidx.databinding.BindingAdapter;
import au.com.pnr.codingdemo.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

/**
 * The type Glide helper.
 */
public class GlideHelper {

    /**
     * Load image.
     *
     * @param view the view
     * @param url  the url
     */
    @BindingAdapter("bind:imageUrl")
    public static void loadImage(ImageView view, String url) {
        Glide.with(view.getContext())
                .load(url)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.drawable.ic_launcher_background)
                .into(view);
    }
}