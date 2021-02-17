package com.jio.githublist.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.jio.githublist.R;
import com.jio.githublist.models.GitUsers;
import com.jio.githublist.utils.Constants;
import com.jio.githublist.utils.RecycleItemClicked;

import java.util.List;

public class GitUserListAdapter extends RecyclerView.Adapter<GitUserListAdapter.ViewHolder> implements Filterable {
    private static final String TAG = "CustomCustomersAdapter";

    Context context;
    List<GitUsers> mDataset;
    RecycleItemClicked companyClicked;

    public GitUserListAdapter(Context context, List<GitUsers> dataSet) {
        this.mDataset = (dataSet);
        this.context = context;
    }

    public void updateListNew(List<GitUsers> mDataset) {
        this.mDataset = (mDataset);
        this.notifyDataSetChanged();
    }

    @Override
    public Filter getFilter() {
        return null;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final TextView textView, text_sub, text_date;
        private final ImageView logo_id;
        private final RelativeLayout parent_tile_bg;

        public ViewHolder(View v) {
            super(v);
            parent_tile_bg = (RelativeLayout) v.findViewById(R.id.parent_tile_bg);
            logo_id = (ImageView) v.findViewById(R.id.logo_id);
            textView = (TextView) v.findViewById(R.id.text_header);
            text_date = (TextView) v.findViewById(R.id.text_date);
            text_sub = (TextView) v.findViewById(R.id.text_sub);

            v.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (companyClicked != null) {
                companyClicked.oncItemClicked(view, getAdapterPosition());
            }
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.text_row_item, viewGroup, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        if (position % 2 == 1) {
            viewHolder.parent_tile_bg.setBackgroundColor(context.getResources().getColor(R.color.light_me));
        } else {
            viewHolder.parent_tile_bg.setBackgroundColor(context.getResources().getColor(R.color.white));
        }


        viewHolder.textView.setText(mDataset.get(position).getLogin());
        viewHolder.text_sub.setText("URL: " + mDataset.get(position).getUrl());
        viewHolder.text_date.setText("Unique ID: " + mDataset.get(position).getId());
        Constants.setGilde(context, mDataset.get(position).avatar_url, viewHolder.logo_id);

    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    // allows clicks events to be caught
    public void setClickListener(RecycleItemClicked companyClicked) {
        this.companyClicked = companyClicked;
    }

}
