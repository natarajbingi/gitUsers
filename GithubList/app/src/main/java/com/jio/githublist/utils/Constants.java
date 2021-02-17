package com.jio.githublist.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.gson.Gson;
import com.jio.githublist.R;

public class Constants {
    public static final String BASE_URL = "https://api.github.com";
    public static final int NETWORK_TIMEOUT = 3000;
    public static final int PerPage = 10;


    public static void setGilde(Context context, String logoImagePath, ImageView view) {
        Glide.with(context)
                .load(logoImagePath)

                .apply(new RequestOptions()
                        //  .centerCrop()
                        //  .circleCrop()
                        .error(R.drawable.profile_icon_menu)
                        .placeholder(R.drawable.profile_icon_menu))
                .into(view);
    }

    public static boolean isConnection(Context context) {
        boolean haveConnectedWifi = false;
        boolean haveConnectedMobile = false;

        ConnectivityManager cm = (ConnectivityManager) context.getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo[] netInfo = cm.getAllNetworkInfo();
        for (NetworkInfo ni : netInfo) {
            if (ni.getTypeName().equalsIgnoreCase("WIFI"))
                if (ni.isConnected())
                    haveConnectedWifi = true;
            if (ni.getTypeName().equalsIgnoreCase("MOBILE"))
                if (ni.isConnected())
                    haveConnectedMobile = true;
        }
        return haveConnectedWifi || haveConnectedMobile;
    }

    public static void logPrint(String call, Object req, Object res) {
        Gson g = new Gson();
        Log.d("Request-", call + "");
        Log.d("LogReq-", g.toJson(req));
        Log.d("LogRes-", g.toJson(res));
    }
}
