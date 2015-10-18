package dailyhunt.info.dhapp.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class NetUtils {

    // utility to check if the network connection is available or not
    public static boolean isOnline(Context c) {
        NetworkInfo netInfo = null;
        try {
            ConnectivityManager cm =
                    (ConnectivityManager) c.getSystemService(Context.CONNECTIVITY_SERVICE);
            netInfo = cm.getActiveNetworkInfo();
        } catch (SecurityException e) {
            e.printStackTrace();
        }
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }
}