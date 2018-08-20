package com.text_adventure.daniyar.textadventure.ui.heart_of_ice.main;

import android.os.Bundle;
import android.widget.Toast;

import com.text_adventure.daniyar.textadventure.R;
import com.text_adventure.daniyar.textadventure.ui.BaseActivity;
import com.text_adventure.daniyar.textadventure.ui.heart_of_ice.beginning.HeartBeginningFragment;
import com.text_adventure.daniyar.textadventure.ui.heart_of_ice.intro.HeartIntroFragment;

import io.realm.Realm;

public class HeartMainActivity extends BaseActivity implements HeartMainContract.View, HeartMainCallBack {

    private HeartMainPresenter mPresenter;

    @Override
    protected int getViewLayout() {
        return R.layout.activity_heart_main;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getViewLayout());

        mPresenter = new HeartMainPresenter(Realm.getDefaultInstance());
        mPresenter.bind(this);

        switchFragment(new HeartBeginningFragment());
    }

    @Override
    public void onNewGameClicked() {
        mPresenter.deleteDataRealm();
        switchFragment(new HeartIntroFragment());
    }

    @Override
    public void onContinueClicked() {
        switchFragment(new HeartIntroFragment());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.closeRealm();
        mPresenter.unbind();
    }
}
