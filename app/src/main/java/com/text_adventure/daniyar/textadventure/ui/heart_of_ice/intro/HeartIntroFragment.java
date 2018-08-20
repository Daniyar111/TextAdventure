package com.text_adventure.daniyar.textadventure.ui.heart_of_ice.intro;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;

import com.text_adventure.daniyar.textadventure.R;
import com.text_adventure.daniyar.textadventure.data.entity.HeartIntroModel;
import com.text_adventure.daniyar.textadventure.data.manager.ResourceManager;
import com.text_adventure.daniyar.textadventure.data.manager.SpeedyLinearLayoutManager;
import com.text_adventure.daniyar.textadventure.ui.BaseFragment;
import com.text_adventure.daniyar.textadventure.ui.heart_of_ice.intro.adapter.HeartIntroAdapter;
import com.text_adventure.daniyar.textadventure.ui.heart_of_ice.intro.adapter.OnHeartIntroChoiceClick;
import com.text_adventure.daniyar.textadventure.ui.heart_of_ice.personage.HeartChoosePersonActivity;

import io.realm.Realm;

public class HeartIntroFragment extends BaseFragment implements HeartIntroContract.View, OnHeartIntroChoiceClick, View.OnClickListener {

    private HeartIntroPresenter mPresenter;
    private Context mContext;
    private RecyclerView mRecyclerViewIntro;
    private HeartIntroAdapter mAdapter;
    private Button mButtonSkip;

    @Override
    protected int getViewLayout() {
        return R.layout.fragment_heart_intro;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if(getActivity() != null){
            mContext = getActivity().getApplicationContext();
        }

        mPresenter = new HeartIntroPresenter(Realm.getDefaultInstance(), new ResourceManager(mContext));
        mPresenter.bind(this);
        mPresenter.readDataCSV();
        mPresenter.writeToRealm();
        initializeViews(view);
    }

    private void initializeViews(View view){
        mRecyclerViewIntro = view.findViewById(R.id.recyclerViewIntro);
        mAdapter = new HeartIntroAdapter(mContext, mPresenter.getIsOpenedStoryModels(), this);
        mRecyclerViewIntro.setLayoutManager(new SpeedyLinearLayoutManager(mContext, SpeedyLinearLayoutManager.VERTICAL, false));
        mRecyclerViewIntro.setAdapter(mAdapter);
        mButtonSkip = view.findViewById(R.id.buttonSkip);
        mButtonSkip.setOnClickListener(this);
        mPresenter.animateRecycler();
    }

    @Override
    public void onButtonClick(HeartIntroModel heartIntroModel) {

        mPresenter.blockButtons(heartIntroModel);
        mPresenter.handlerLooping(mPresenter.getStoryModels(heartIntroModel.getId()), false);
    }

    @Override
    public void refreshAdapter(boolean isNew) {
        if(isNew){
            animateView();
        }
        else{
            mRecyclerViewIntro.getAdapter().notifyDataSetChanged();
            mRecyclerViewIntro.smoothScrollToPosition(mPresenter.getOldOpenedStoryModels() + 2);
        }
    }

    @Override
    public void scrollToEnd() {
        mRecyclerViewIntro.getAdapter().notifyDataSetChanged();
        mRecyclerViewIntro.scrollToPosition(mPresenter.getIsOpenedStoryModels().size() - 1);
    }

    @Override
    public void animateView() {
        mRecyclerViewIntro.setLayoutAnimation(AnimationUtils.loadLayoutAnimation(mRecyclerViewIntro.getContext(), R.anim.layout_fall_down));
        mRecyclerViewIntro.getAdapter().notifyDataSetChanged();
        mRecyclerViewIntro.scheduleLayoutAnimation();
    }

    @Override
    public void notifyDataAdapter() {
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.closeRealm();
        mPresenter.unbind();
    }

    @Override
    public void onClick(View view) {
        startActivity(new Intent(getActivity(), HeartChoosePersonActivity.class));
    }
}
