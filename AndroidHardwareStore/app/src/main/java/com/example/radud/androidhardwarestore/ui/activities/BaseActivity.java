package com.example.radud.androidhardwarestore.ui.activities;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;

import com.example.radud.androidhardwarestore.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by radud on 06/01/2016.
 */
public class BaseActivity extends ActionBarActivity {

    @Bind(R.id.toolbar)
    Toolbar mToolbar;

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        ButterKnife.bind(this);
        setUpToolbar();
    }

    private void setUpToolbar() {
        setSupportActionBar(mToolbar);
    }

}
