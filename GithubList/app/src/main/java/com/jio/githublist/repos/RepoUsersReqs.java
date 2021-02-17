package com.jio.githublist.repos;

import com.jio.githublist.network.RestFullServices;
import com.jio.githublist.utils.Constants;
import com.jio.githublist.viewmodels.GitUsersCallBacks;

public class RepoUsersReqs {
    private static String mQuery;
    private static int pageNumber;

    public static void requestGitUsers(String q, GitUsersCallBacks callBacks, boolean first) {
        if(pageNumber==0){
            pageNumber =1;
            mQuery = q;
        }
        RestFullServices.getSearchUser(q, pageNumber, callBacks);
    }

    public void requestGitUsersNextPage() {
        // searchRecipesApi(mQuery, mPageNumber + 1);
    }
}
