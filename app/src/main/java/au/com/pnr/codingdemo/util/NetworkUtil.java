package au.com.pnr.codingdemo.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;
import au.com.pnr.codingdemo.R;

/**
 * The type Network util.
 */
public class NetworkUtil {

    /**
     * Is online boolean.
     *
     * @param context the context
     * @return the boolean
     */
    public static boolean isOnline(Context context) {
        ConnectivityManager conMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        assert conMgr != null;
        NetworkInfo netInfo = conMgr.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnected() && netInfo.isAvailable();
    }

    public static void displayServerErrorMessages(Context context) {
        Toast.makeText(context,R.string.error_server_response,Toast.LENGTH_SHORT).show();
    }
}
