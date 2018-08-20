package com.text_adventure.daniyar.textadventure.ui;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.text_adventure.daniyar.textadventure.R;

public abstract class BaseActivity extends AppCompatActivity{

    protected abstract int getViewLayout();

    @Override
    public void setContentView(View view) {
        super.setContentView(view);
        setContentView(getViewLayout());
    }

    protected void switchFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragmentContainer, fragment)
                .commit();
    }
}
