package com.jio.githublist;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.jio.githublist.utils.LoaderDecorator;

public abstract class BaseActivity extends AppCompatActivity {
    public LoaderDecorator loader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loader = new LoaderDecorator(this);

    }

    public void showProgress(boolean visible) {
        if (visible) {
            loader.start();
        } else {
            loader.stop();
        }
    }

    public static enum LayoutManagerType {
        GRID_LAYOUT_MANAGER,
        LINEAR_LAYOUT_MANAGER
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        loader = null;
    }
}
