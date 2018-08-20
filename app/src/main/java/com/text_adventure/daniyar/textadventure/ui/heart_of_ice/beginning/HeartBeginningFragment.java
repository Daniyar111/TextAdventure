package com.text_adventure.daniyar.textadventure.ui.heart_of_ice.beginning;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.text_adventure.daniyar.textadventure.R;
import com.text_adventure.daniyar.textadventure.data.manager.ResourceManager;
import com.text_adventure.daniyar.textadventure.ui.BaseFragment;
import com.text_adventure.daniyar.textadventure.ui.heart_of_ice.main.HeartMainCallBack;

import java.util.ArrayList;

import io.realm.Realm;

public class HeartBeginningFragment extends BaseFragment implements HeartBeginningContract.View, View.OnClickListener {

    private Context mContext;
    private ListView mListViewBeginning;
    private HeartBeginningPresenter mPresenter;
    private HeartBeginningAdapter mAdapter;
    private Button mButtonNewGame, mButtonContinue;
    private HeartMainCallBack mCallBack;

    @Override
    protected int getViewLayout() {
        return R.layout.fragment_heart_beginning;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if(getActivity() != null){
            mContext = getActivity().getApplicationContext();
        }

        mPresenter = new HeartBeginningPresenter(Realm.getDefaultInstance(), new ResourceManager(mContext));
        mPresenter.bind(this);

        initializeViews(view);
        mPresenter.setEnabledContinue();

        mPresenter.getBeginningStory();
    }

    private void initializeViews(View view){
        mListViewBeginning = view.findViewById(R.id.listViewBeginning);
        mButtonContinue = view.findViewById(R.id.buttonContinue);
        mButtonNewGame = view.findViewById(R.id.buttonNewGame);
        mButtonNewGame.setOnClickListener(this);
        mButtonContinue.setOnClickListener(this);
    }

    @Override
    public void setRulesAdapter(ArrayList<String> rulesList) {
        mAdapter = new HeartBeginningAdapter(mContext, rulesList);
        mListViewBeginning.setAdapter(mAdapter);
    }

    @Override
    public void setDisabled() {
        mButtonContinue.setEnabled(false);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.buttonNewGame:
                mCallBack.onNewGameClicked();
                break;
            case R.id.buttonContinue:
                mCallBack.onContinueClicked();
                break;
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mCallBack = (HeartMainCallBack) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement HeartMainCallBack");
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.closeRealm();
        mPresenter.unbind();
    }
}
