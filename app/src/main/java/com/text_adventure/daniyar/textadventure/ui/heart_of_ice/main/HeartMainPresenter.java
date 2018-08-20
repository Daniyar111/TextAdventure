package com.text_adventure.daniyar.textadventure.ui.heart_of_ice.main;

import android.util.Log;

import com.text_adventure.daniyar.textadventure.data.entity.HeartIntroModel;

import io.realm.Realm;

public class HeartMainPresenter implements HeartMainContract.Presenter {

    private HeartMainContract.View mView;
    private Realm mRealm;

    public HeartMainPresenter(Realm realm){
        mRealm = realm;
    }

    @Override
    public void deleteDataRealm() {
        mRealm.beginTransaction();
        mRealm.delete(HeartIntroModel.class);
        mRealm.commitTransaction();
    }

    @Override
    public void closeRealm() {
        mRealm.close();
    }

    @Override
    public void bind(HeartMainContract.View view) {
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
