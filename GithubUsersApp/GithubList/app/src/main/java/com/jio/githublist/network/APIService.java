package com.jio.githublist.network;

import com.jio.githublist.models.GitUsersResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APIService {

    // SEARCH
    @GET("search/users")
    Call<GitUsersResponse> searchGitUsers(
            @Query("q") String query,
            @Query("per_page") Integer per_page,
            @Query("page") Integer page
    );

}
