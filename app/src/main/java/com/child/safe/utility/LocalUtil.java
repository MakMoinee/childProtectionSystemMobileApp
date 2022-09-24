package com.child.safe.utility;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class LocalUtil {
    public static boolean isNetworkConnect(Context mContext){
        ConnectivityManager cm = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = cm.getActiveNetworkInfo();
        return cm.getActiveNetworkInfo() != null && info.isConnected();
    };
}
