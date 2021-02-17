package com.jio.githublist.viewmodels;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.jio.githublist.models.GitUsersResponse;
import com.jio.githublist.network.RestFullServices;
import com.jio.githublist.utils.Constants;

public class GItListViewModel extends AndroidViewModel implements GitUsersCallBacks {

    MutableLiveData<GitUsersResponse> mDataSet;
    GitUsersUsagebacks usagebacks;
    Context context;


    public GItListViewModel(@NonNull Application application) {
        super(application);
        mDataSet = new MutableLiveData<>();
        context = application.getApplicationContext();
    }

    public void setUsagebacks(GitUsersUsagebacks usagebacks) {
        this.usagebacks = usagebacks;
    }

    public void requestGitUsers(String q, int page) {
        usagebacks.showBar();
        if (Constants.isConnection(context)) {
            RestFullServices.getSearchUser(q, page, this);
        } else {
            usagebacks.onError("No Internet connection.");
        }
    }

    public LiveData<GitUsersResponse> getGitUsers() {
        return mDataSet;
    }

    @Override
    public void requestGitUsersSuccess(GitUsersResponse usersResponse) {
        mDataSet.postValue(usersResponse);
        usagebacks.hideBar();
        usagebacks.requestGitUsersSuccess(usersResponse);
    }

    @Override
    public void onError(String message) {
        usagebacks.hideBar();
        usagebacks.onError(message);
    }
}
