package com.text_adventure.daniyar.textadventure.ui.heart_of_ice.personage;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.text_adventure.daniyar.textadventure.R;
import com.text_adventure.daniyar.textadventure.data.entity.HeartStoryModel;
import com.text_adventure.daniyar.textadventure.ui.BaseActivity;
import com.text_adventure.daniyar.textadventure.ui.heart_of_ice.game.HeartGameFragment;
import com.text_adventure.daniyar.textadventure.ui.heart_of_ice.game.adapter.OnHeartGameChoiceClick;
import com.text_adventure.daniyar.textadventure.ui.heart_of_ice.personage.person_list.HeartPersonListFragment;

public class HeartChoosePersonActivity extends BaseActivity implements HeartChoosePersonContract.View, HeartGameStartCallBack{

    private HeartChoosePersonPresenter mPresenter;

    @Override
    protected int getViewLayout() {
        return R.layout.activity_heart_choose_person;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getViewLayout());

        mPresenter = new HeartChoosePersonPresenter();
        mPresenter.bind(this);
        switchFragment(new HeartPersonListFragment());
    }



    @Override
    public void onStartClicked() {
        switchFragment(new HeartGameFragment());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.unbind();
    }
}
