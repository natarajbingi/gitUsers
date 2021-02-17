package com.jio.githublist.network;

import com.jio.githublist.models.GitUsersResponse;
import com.jio.githublist.utils.Constants;
import com.jio.githublist.viewmodels.GitUsersCallBacks;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RestFullServices {

    public static APIService getClient() {
        OkHttpClient clientWith60sTimeout = new OkHttpClient()
                .newBuilder()
                .readTimeout(60, TimeUnit.SECONDS)
                .connectTimeout(60, TimeUnit.SECONDS)
                .build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(clientWith60sTimeout)
                .build();
        return retrofit.create(APIService.class);
    }

    public static void getSearchUser(String q, Integer page, GitUsersCallBacks callBacks) {
        getClient().searchGitUsers(q, Constants.PerPage, page).enqueue(new Callback<GitUsersResponse>() {
            @Override
            public void onResponse(Call<GitUsersResponse> call, Response<GitUsersResponse> response) {
                Constants.logPrint(call.request().toString(), q + ", page" + page, response.body());

                if (response.isSuccessful()) {
                    callBacks.requestGitUsersSuccess(response.body());
                } else {
                    callBacks.onError(response.errorBody() + ", Something went wrong, Server Error");
                }
            }

            @Override
            public void onFailure(Call<GitUsersResponse> call, Throwable t) {
                Constants.logPrint(call.request().toString(), q + ", page" + page, t.getMessage());
                callBacks.onError(t.getMessage() + ", Something went wrong, Server Error");
            }
        });
    }
}