package com.text_adventure.daniyar.textadventure.ui.museum;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.animation.AnimationUtils;

import com.text_adventure.daniyar.textadventure.R;
import com.text_adventure.daniyar.textadventure.data.entity.MuseumStoryModel;
import com.text_adventure.daniyar.textadventure.data.manager.ResourceManager;
import com.text_adventure.daniyar.textadventure.data.manager.SpeedyLinearLayoutManager;
import com.text_adventure.daniyar.textadventure.ui.BaseActivity;
import com.text_adventure.daniyar.textadventure.ui.museum.adapter.MuseumAdapter;
import com.text_adventure.daniyar.textadventure.ui.museum.adapter.OnMuseumChoiceClick;

import io.realm.Realm;

public class MuseumMainActivity extends BaseActivity implements MuseumMainContract.View, OnMuseumChoiceClick{

    private MuseumMainPresenter mPresenter;
    private RecyclerView mRecyclerView;
    private MuseumAdapter mAdapter;

    @Override
    protected int getViewLayout() {
        return R.layout.activity_museum_main;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getViewLayout());

        mPresenter = new MuseumMainPresenter(Realm.getDefaultInstance(), new ResourceManager(this));
        mPresenter.bind(this);
        mPresenter.readDataCSV();
        mPresenter.writeToRealm();
        initializeViews();
    }

    private void initializeViews(){
        mRecyclerView = findViewById(R.id.recyclerView);
        mAdapter = new MuseumAdapter(this, mPresenter.getIsOpenedStoryModels(), this);
        mRecyclerView.setLayoutManager(new SpeedyLinearLayoutManager(this, SpeedyLinearLayoutManager.VERTICAL, false));
        mRecyclerView.setAdapter(mAdapter);
        mPresenter.animateRecycler();
    }

    @Override
    public void refreshAdapter(boolean isNew) {
        if(isNew){
            animateView();
        }
        else{
            mRecyclerView.getAdapter().notifyDataSetChanged();
            mRecyclerView.smoothScrollToPosition(mPresenter.getOldOpenedStoryModels() + 5);
        }
    }

    @Override
    public void notifyDataAdapter() {
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void scrollToEnd() {
        mRecyclerView.getAdapter().notifyDataSetChanged();
        mRecyclerView.scrollToPosition(mPresenter.getIsOpenedStoryModels().size() - 1);
    }

    @Override
    public void animateView() {
        mRecyclerView.setLayoutAnimation(AnimationUtils.loadLayoutAnimation(mRecyclerView.getContext(), R.anim.layout_slide_up));
        mRecyclerView.getAdapter().notifyDataSetChanged();
        mRecyclerView.scheduleLayoutAnimation();
    }

    @Override
    public void onButtonClick(MuseumStoryModel museumStoryModel) {

        if(museumStoryModel.getDescription().equals("Начать сначала")){
            mPresenter.startAgain();
            return;
        }

        mPresenter.blockButtons(museumStoryModel);
        mPresenter.handlerLooping(mPresenter.getStoryModels(museumStoryModel.getId()), false);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.closeRealm();
        mPresenter.unbind();
    }
}
