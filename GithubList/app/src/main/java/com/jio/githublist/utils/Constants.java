package com.jio.githublist.utils;

import android.content.Context;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.gson.Gson;
import com.jio.githublist.BuildConfig;
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

    public static void logPrint(String call, Object req, Object res) {
        if (BuildConfig.BUILD_TYPE.equals("debug")) {
            Gson g = new Gson();
            Log.d("Request-", call + "");
            Log.d("LogReq-", g.toJson(req));
            Log.d("LogRes-", g.toJson(res));
        }
    }
}
