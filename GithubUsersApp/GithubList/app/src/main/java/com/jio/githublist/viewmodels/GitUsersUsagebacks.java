package com.jio.githublist.viewmodels;

import com.jio.githublist.models.GitUsersResponse;

public interface GitUsersUsagebacks {

    void requestGitUsersSuccess(GitUsersResponse usersResponse);
    void showBar();
    void hideBar();
    void onError(String message);
}
