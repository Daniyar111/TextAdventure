package com.text_adventure.daniyar.textadventure.ui.heart_of_ice.game;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.animation.AnimationUtils;

import com.text_adventure.daniyar.textadventure.R;
import com.text_adventure.daniyar.textadventure.data.entity.HeartStoryModel;
import com.text_adventure.daniyar.textadventure.data.manager.ResourceManager;
import com.text_adventure.daniyar.textadventure.data.manager.SpeedyLinearLayoutManager;
import com.text_adventure.daniyar.textadventure.ui.BaseFragment;
import com.text_adventure.daniyar.textadventure.ui.heart_of_ice.game.adapter.HeartGameAdapter;
import com.text_adventure.daniyar.textadventure.ui.heart_of_ice.game.adapter.OnHeartGameChoiceClick;

import io.realm.Realm;

public class HeartGameFragment extends BaseFragment implements HeartGameContract.View, OnHeartGameChoiceClick{

    private HeartGamePresenter mPresenter;
    private Context mContext;
    private RecyclerView mRecyclerViewHeart;
    private HeartGameAdapter mAdapter;

    @Override
    protected int getViewLayout() {
        return R.layout.fragment_heart_game;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if(getActivity() != null){
            mContext = getActivity().getApplicationContext();
        }

        mPresenter = new HeartGamePresenter(Realm.getDefaultInstance(), new ResourceManager(mContext));
        mPresenter.bind(this);
        mPresenter.readDataCSV();
        mPresenter.writeToRealm();
        initializeViews(view);
    }

    private void initializeViews(View view){
        mRecyclerViewHeart = view.findViewById(R.id.recyclerViewHeart);
        mAdapter = new HeartGameAdapter(mContext, mPresenter.getIsOpenedStoryModels(), this);
        mRecyclerViewHeart.setLayoutManager(new SpeedyLinearLayoutManager(mContext, SpeedyLinearLayoutManager.VERTICAL, false));
        mRecyclerViewHeart.setAdapter(mAdapter);
        mPresenter.animateRecycler();
    }

    @Override
    public void refreshAdapter(boolean isNew) {
        if(isNew){
            animateView();
        }
        else{
            mRecyclerViewHeart.getAdapter().notifyDataSetChanged();
            mRecyclerViewHeart.smoothScrollToPosition(mPresenter.getOldOpenedStoryModels() + 1);
        }
    }

    @Override
    public void notifyDataAdapter() {
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void scrollToEnd() {
        mRecyclerViewHeart.getAdapter().notifyDataSetChanged();
        mRecyclerViewHeart.scrollToPosition(mPresenter.getIsOpenedStoryModels().size() - 1);
    }

    @Override
    public void animateView() {
        mRecyclerViewHeart.setLayoutAnimation(AnimationUtils.loadLayoutAnimation(mRecyclerViewHeart.getContext(), R.anim.layout_slide_up));
        mRecyclerViewHeart.getAdapter().notifyDataSetChanged();
        mRecyclerViewHeart.scheduleLayoutAnimation();
    }

    @Override
    public void onButtonClick(HeartStoryModel heartStoryModel) {
        mPresenter.blockButtons(heartStoryModel);
        mPresenter.handlerLooping(mPresenter.getStoryModels(heartStoryModel.getId()), false);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.closeRealm();
        mPresenter.unbind();
    }
}
