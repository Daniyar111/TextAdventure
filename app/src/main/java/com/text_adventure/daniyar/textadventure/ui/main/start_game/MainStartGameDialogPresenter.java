package com.text_adventure.daniyar.textadventure.ui.main.start_game;

import com.text_adventure.daniyar.textadventure.data.entity.BookModel;
import com.text_adventure.daniyar.textadventure.data.entity.HeartPersonModel;
import com.text_adventure.daniyar.textadventure.data.entity.MuseumStoryModel;
import com.text_adventure.daniyar.textadventure.ui.heart_of_ice.personage.person_details.HeartPersonDetailsDialogContract;

import io.realm.Realm;
import io.realm.RealmResults;

public class MainStartGameDialogPresenter implements MainStartGameDialogContract.Presenter {

    private MainStartGameDialogContract.View mView;
    private Realm mRealm;

    public MainStartGameDialogPresenter(Realm realm){
        mRealm = realm;
    }

    @Override
    public void showPersonInfo(String book) {
        if(book.equals("Ужасы музея") && mRealm.where(MuseumStoryModel.class).findAll().size() == 0 && isViewAttached()){
            mView.showNew();
            return;
        }
        if(book.equals("Ужасы музея") && mRealm.where(MuseumStoryModel.class).findAll().size() != 0 && isViewAttached()){
            mView.showSaved();
        }
    }

    @Override
    public void deleteDataRealm() {
        mRealm.beginTransaction();
        mRealm.delete(MuseumStoryModel.class);
        mRealm.commitTransaction();
    }

    @Override
    public void closeRealm() {
        mRealm.close();
    }

    @Override
    public void bind(MainStartGameDialogContract.View view) {
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
