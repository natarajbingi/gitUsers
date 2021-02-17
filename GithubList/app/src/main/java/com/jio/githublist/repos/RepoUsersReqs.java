package com.jio.githublist.repos;

import com.jio.githublist.network.RestFullServices;
import com.jio.githublist.utils.Constants;
import com.jio.githublist.viewmodels.GitUsersCallBacks;

public class RepoUsersReqs {
    private String mQuery;

    public static void requestGitUsers(String q, GitUsersCallBacks callBacks, int page, boolean first) {
        /*if (Constants.NETWORK_TIMEOUT) {
            RestFullServices.getSearchUser(q, page, callBacks);
        }*/
    }
    public void requestGitUsersNextPage(){
       // searchRecipesApi(mQuery, mPageNumber + 1);
    }
}
