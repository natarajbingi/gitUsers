package com.jio.githublist;

import android.content.Context;
import android.os.Bundle;
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
import com.jio.githublist.utils.RecycleItemClicked;
import com.jio.githublist.viewmodels.GItListViewModel;
import com.jio.githublist.viewmodels.GitUsersUsagebacks;

import java.util.List;

public class GitHubListActivity extends BaseActivity implements RecycleItemClicked, GitUsersUsagebacks {

    private Context context;
    GItListViewModel viewModel;
    ActivityGithubListBinding binding;
    List<GitUsers> mDataset;
    private GitUserListAdapter mAdapter;
    int pageCount = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        viewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(GItListViewModel.class);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_github_list);
        binding.setGitModel(viewModel);
        viewModel.setUsagebacks(this);

        viewModel.requestGitUsers("nataraj", pageCount);
        viewModel.getGitUsers().observe(this, new Observer<GitUsersResponse>() {
            @Override
            public void onChanged(GitUsersResponse usersResponse) {
                mDataset = usersResponse.items;
                setmRecyclerView();
            }
        });

        binding.searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String query = binding.searchText.getText().toString();
                viewModel.requestGitUsers(query, pageCount = 1);
            }
        });
    }

    private void setmRecyclerView() {
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
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
    }

    @Override
    public void requestGitUsersSuccess(GitUsersResponse usersResponse) {
        Toast.makeText(context, usersResponse.total_count + "", Toast.LENGTH_LONG).show();
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
