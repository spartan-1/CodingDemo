package au.com.pnr.codingdemo.util.customviews;

import android.content.Context;
import android.util.AttributeSet;
import androidx.recyclerview.widget.RecyclerView;
import au.com.pnr.codingdemo.util.AppConstants;

/**
 * The type Custom recycler view.
 */
public class CustomRecyclerView extends RecyclerView {

    /**
     * The Context.
     */
    Context context;

    /**
     * Instantiates a new Custom recycler view.
     *
     * @param context the context
     */
    public CustomRecyclerView(Context context) {
        super(context);
        this.context = context;
    }

    /**
     * Instantiates a new Custom recycler view.
     *
     * @param context the context
     * @param attrs   the attrs
     */
    public CustomRecyclerView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * Instantiates a new Custom recycler view.
     *
     * @param context  the context
     * @param attrs    the attrs
     * @param defStyle the def style
     */
    public CustomRecyclerView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public boolean fling(int velocityX, int velocityY) {
        velocityY *= AppConstants.RECYCLER_VIEW_SPEED_MULTIPLIER;
        return super.fling(velocityX, velocityY);
    }

}