package com.text_adventure.daniyar.textadventure.ui.heart_of_ice.beginning;

import com.text_adventure.daniyar.textadventure.R;
import com.text_adventure.daniyar.textadventure.data.entity.HeartIntroModel;
import com.text_adventure.daniyar.textadventure.data.manager.ResourceManager;

import java.util.ArrayList;
import java.util.Arrays;

import io.realm.Realm;

public class HeartBeginningPresenter implements HeartBeginningContract.Presenter {

    private HeartBeginningContract.View mView;
    private ResourceManager mResourceManager;
    private Realm mRealm;
    private ArrayList<String> rulesList = new ArrayList<>();

    public HeartBeginningPresenter(Realm realm, ResourceManager resourceManager){
        mRealm = realm;
        mResourceManager = resourceManager;
    }

    @Override
    public void getBeginningStory() {
        rulesList.addAll(Arrays.asList(mResourceManager.getResources().getStringArray(R.array.array_beginning)));
        if(isViewAttached()){
            mView.setRulesAdapter(rulesList);
        }
    }

    @Override
    public void setEnabledContinue() {
        if(mRealm.where(HeartIntroModel.class).findAll().size() == 0 && isViewAttached()){
            mView.setDisabled();
        }
    }

    @Override
    public void closeRealm() {
        mRealm.close();
    }

    @Override
    public void bind(HeartBeginningContract.View view) {
        mView = view;
    }

    @Override
    public void unbind() {
        mView = null;
    }

    private boolean isViewAttached(){
        return mView != null;
    }
}
