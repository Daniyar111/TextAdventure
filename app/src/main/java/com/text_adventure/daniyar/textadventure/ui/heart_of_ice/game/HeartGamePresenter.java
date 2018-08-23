package com.text_adventure.daniyar.textadventure.ui.heart_of_ice.game;

import com.text_adventure.daniyar.textadventure.data.manager.ResourceManager;

import io.realm.Realm;

public class HeartGamePresenter implements HeartGameContract.Presenter{

    private HeartGameContract.View mView;
    private Realm mRealm;
    private ResourceManager mResourceManager;

    public HeartGamePresenter(Realm realm, ResourceManager resourceManager){
        mRealm = realm;
        mResourceManager = resourceManager;
    }

    @Override
    public void closeRealm() {
        mRealm.close();
    }

    @Override
    public void bind(HeartGameContract.View view) {
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
