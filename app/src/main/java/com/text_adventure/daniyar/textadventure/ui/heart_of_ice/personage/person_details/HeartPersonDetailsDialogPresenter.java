package com.text_adventure.daniyar.textadventure.ui.heart_of_ice.personage.person_details;

import android.util.Log;

import com.text_adventure.daniyar.textadventure.data.entity.HeartPersonModel;

import io.realm.Realm;
import io.realm.RealmResults;

public class HeartPersonDetailsDialogPresenter implements HeartPersonDetailsDialogContract.Presenter {

    private HeartPersonDetailsDialogContract.View mView;
    private Realm mRealm;
    private RealmResults<HeartPersonModel> mPersonModels;

    public HeartPersonDetailsDialogPresenter(Realm realm){
        mRealm = realm;
    }

    @Override
    public void showPersonInfo(String person) {
        mPersonModels = mRealm.where(HeartPersonModel.class).findAll();
        for (int i = 0; i < mPersonModels.size(); i++) {
            if(person.equals(mPersonModels.get(i).getName()) && isViewAttached()){
                mView.showDetails(mPersonModels.get(i));
            }
        }
    }

    @Override
    public void closeRealm() {
        mRealm.close();
    }

    @Override
    public void bind(HeartPersonDetailsDialogContract.View view) {
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
