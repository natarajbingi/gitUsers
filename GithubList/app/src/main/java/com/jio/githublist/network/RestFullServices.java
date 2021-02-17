package com.jio.githublist.network;

import android.content.Context;
import android.util.Log;

import com.jio.githublist.models.GitUsersResponse;
import com.jio.githublist.utils.Constants;
import com.jio.githublist.viewmodels.GitUsersCallBacks;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RestFullServices {
    public static APIService getClient(Context context) {
        OkHttpClient clientWith60sTimeout = new OkHttpClient()
                .newBuilder()
                .cache(cache(context))
                .addInterceptor(httpLoggingInterceptor())
                .readTimeout(60, TimeUnit.SECONDS)
                .connectTimeout(60, TimeUnit.SECONDS)
                .addInterceptor(offlineInterceptor(context))
                .addNetworkInterceptor(networkInterceptor())
                .build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(clientWith60sTimeout)
                .build();
        return retrofit.create(APIService.class);
    }

    private static Cache cache(Context context) {
        return new Cache(new File(context.getCacheDir(), "retroCache"), Constants.cacheSize);
    }

    private static Interceptor offlineInterceptor(Context context) {
        return new Interceptor() {
            @Override
            public okhttp3.Response intercept(Chain chain) throws IOException {
                Log.d("TAG", "offline interceptor: called.");
                Request request = chain.request();

                if (!Constants.isConnection(context)) {
                    CacheControl cacheControl = new CacheControl.Builder()
                            .maxStale(7, TimeUnit.DAYS)
                            .build();

                    request = request.newBuilder()
                            .removeHeader(Constants.HEADER_PRAGMA)
                            .removeHeader(Constants.HEADER_CACHE_CONTROL)
                            .cacheControl(cacheControl)
                            .build();
                }

                return chain.proceed(request);
            }
        };
    }

    private static Interceptor networkInterceptor() {
        return new Interceptor() {
            @Override
            public okhttp3.Response intercept(Chain chain) throws IOException {
                Log.d("TAG", "network interceptor: called.");

                okhttp3.Response response = chain.proceed(chain.request());

                CacheControl cacheControl = new CacheControl.Builder()
                        .maxAge(5, TimeUnit.SECONDS)
                        .build();

                return response.newBuilder()
                        .removeHeader(Constants.HEADER_PRAGMA)
                        .removeHeader(Constants.HEADER_CACHE_CONTROL)
                        .header(Constants.HEADER_CACHE_CONTROL, cacheControl.toString())
                        .build();
            }
        };
    }

    private static HttpLoggingInterceptor httpLoggingInterceptor() {
        HttpLoggingInterceptor httpLoggingInterceptor =
                new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
                    @Override
                    public void log(String message) {
                        Log.d("TAG", "log: http log: " + message);
                    }
                });
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return httpLoggingInterceptor;
    }


    public static void getSearchUser(String q, Integer page, GitUsersCallBacks callBacks, Context context) {
        getClient(context).searchGitUsers(q, Constants.PerPage, page).enqueue(new Callback<GitUsersResponse>() {
            @Override
            public void onResponse(Call<GitUsersResponse> call, Response<GitUsersResponse> response) {
                Constants.logPrint(call.request().toString(), q + ", page" + page, response.body());

                if (response.isSuccessful()) {
                    callBacks.requestGitUsersSuccess(response.body());
                } else {
                    callBacks.onError("Something went wrong, Please check network connection" + response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<GitUsersResponse> call, Throwable t) {
                Constants.logPrint(call.request().toString(), q + ", page" + page, t.getMessage());
                callBacks.onError("Something went wrong, Please check network connection");
            }
        });
    }
}