package com.jio.githublist.models;

import java.util.List;

public class GitUsersResponse {
    public int total_count;
    public boolean incomplete_results;
    public List<GitUsers> items;
}
