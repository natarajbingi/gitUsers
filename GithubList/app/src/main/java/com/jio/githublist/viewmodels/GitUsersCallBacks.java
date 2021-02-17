package com.jio.githublist.viewmodels;

import com.jio.githublist.models.GitUsersResponse;

public interface GitUsersCallBacks {

    void requestGitUsersSuccess(GitUsersResponse usersResponse);
    void onError(String message);

}
