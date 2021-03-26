package com.jio.githublist;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Toast;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.jio.githublist.adapters.GitUserListAdapter;
import com.jio.githublist.databinding.ActivityGithubListBinding;
import com.jio.githublist.models.GitUsers;
import com.jio.githublist.models.GitUsersResponse;
import com.jio.githublist.utils.PagnationScrollListener;
import com.jio.githublist.utils.RecycleItemClicked;
import com.jio.githublist.viewmodels.GItListViewModel;
import com.jio.githublist.viewmodels.GitUsersUsagebacks;

import java.util.ArrayList;
import java.util.List;
 

public class GitHubListActivity extends BaseActivity implements RecycleItemClicked, GitUsersUsagebacks {

    private Context context;
    private GItListViewModel viewModel;
    private ActivityGithubListBinding binding;
    private List<GitUsers> mDataset = new ArrayList<>();
    private GitUserListAdapter mAdapter;
    private final int pageStart = 1;
    private int pageCurrent = pageStart;
    private final int totalPages = 5;
    private boolean isLoading = false, isLastPage = false;
    private String query = "";
    private LinearLayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        viewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(GItListViewModel.class);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_github_list);
        binding.setGitModel(viewModel);
        viewModel.setUsagebacks(this);
        mLayoutManager = new LinearLayoutManager(this);

        // viewModel.requestGitUsers("nataraj", pageCount);
        viewModel.getGitUsers().observe(this, new Observer<GitUsersResponse>() {
            @Override
            public void onChanged(GitUsersResponse usersResponse) {
                if (pageCurrent == 1) {
                    mDataset = (usersResponse.items);
                } else {
                    mDataset.addAll(usersResponse.items);
                }
                setmRecyclerView();
            }
        });

        binding.searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                query = binding.searchText.getText().toString();

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        viewModel.requestGitUsers(query, pageCurrent = pageStart);
                    }
                }, 1000);
            }
        });

        binding.usersList.addOnScrollListener(new PagnationScrollListener(mLayoutManager) {
            @Override
            protected void LoadMoreItems() {
                isLoading = true;
                pageCurrent += 1;

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        viewModel.requestGitUsers(query, pageCurrent);
                    }
                }, 1000);
            }

            @Override
            public int getTotalPageCount() {
                return totalPages;
            }

            @Override
            public boolean isLoading() {
                return isLoading;
            }

            @Override
            public boolean isLastPage() {
                return isLastPage;
            }
        });
    }

    private void setmRecyclerView() {
        int scrollPosition = 0;
        // If a layout manager has already been set, get current scroll position.
        if (binding.usersList.getLayoutManager() != null) {
            scrollPosition = ((LinearLayoutManager) binding.usersList.getLayoutManager())
                    .findFirstCompletelyVisibleItemPosition();
        }
        binding.usersList.setLayoutManager(mLayoutManager);
        binding.usersList.scrollToPosition(scrollPosition);

        if (mAdapter == null) {
            mAdapter = new GitUserListAdapter(context, mDataset);
            mAdapter.setClickListener(this);
            binding.usersList.setAdapter(mAdapter);
        } else {
            mAdapter.updateListNew(mDataset);
        }
    }

    @Override
    public void oncItemClicked(View view, int position) {
        Toast.makeText(context, "Selected " + mDataset.get(position).getLogin(), Toast.LENGTH_LONG).show();
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(mDataset.get(position).getHtml_url()));
        startActivity(browserIntent);
    }

    @Override
    public void requestGitUsersSuccess(GitUsersResponse usersResponse) {
        isLoading = false;
        if (pageCurrent >= totalPages) {
            isLastPage = true;
        }
       // Toast.makeText(context, usersResponse.total_count + "", Toast.LENGTH_LONG).show();
    }

    @Override
    public void showBar() {
        loader.start();
    }

    @Override
    public void hideBar() {
        loader.stop();
    }

    @Override
    public void onError(String message) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }
}
