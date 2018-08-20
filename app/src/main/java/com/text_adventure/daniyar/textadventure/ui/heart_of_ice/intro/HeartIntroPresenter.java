package com.text_adventure.daniyar.textadventure.ui.heart_of_ice.intro;

import android.os.Handler;
import android.support.annotation.NonNull;
import android.util.Log;

import com.text_adventure.daniyar.textadventure.R;
import com.text_adventure.daniyar.textadventure.data.entity.HeartIntroModel;
import com.text_adventure.daniyar.textadventure.data.entity.MuseumStoryModel;
import com.text_adventure.daniyar.textadventure.data.manager.ResourceManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmResults;

public class HeartIntroPresenter implements HeartIntroContract.Presenter{

    private HeartIntroContract.View mView;
    private Realm mRealm;
    private ResourceManager mResourceManager;
    private ArrayList<HeartIntroModel> mHeartIntroModels = new ArrayList<>();
    private ArrayList<HeartIntroModel> mRealmHeartIntroModels = new ArrayList<>();
    private int mOldOpenedStoryModels;
    private boolean mIsNew;

    public HeartIntroPresenter(Realm realm, ResourceManager resourceManager){
        mRealm = realm;
        mResourceManager = resourceManager;
        mOldOpenedStoryModels = 0;
        mIsNew = false;
    }

    @Override
    public void readDataCSV() {
        if(mRealm.where(HeartIntroModel.class).findAll().size() == 0){
            InputStream inputStream = mResourceManager.getResources().openRawResource(R.raw.data_heart_intro);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, Charset.forName("UTF-8")));

            String line = "";
            try{
                bufferedReader.readLine();
                while ((line = bufferedReader.readLine()) != null){
                    Log.d("DAN_DATA", "Line: " + line);
                    String[] tokens = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
                    HeartIntroModel heartIntroModel = new HeartIntroModel();
                    heartIntroModel.setId(Integer.parseInt(tokens[0]));
                    heartIntroModel.setParentId(Integer.parseInt(tokens[1]));
                    heartIntroModel.setDescription(tokens[2].replace("\\\"",""));
                    setTypeInt(heartIntroModel, tokens);
                    heartIntroModel.setIsOpened(Integer.parseInt(tokens[4]));
                    heartIntroModel.setShowed(true);
                    mHeartIntroModels.add(heartIntroModel);

                    Log.d("DAN_DATA", "Just created: " + heartIntroModel);
                }
            } catch (IOException e) {
                Log.wtf("DAN_DATA", "Error reading data file on line" + line, e);

                e.printStackTrace();
            }
        }
    }

    private void setTypeInt(HeartIntroModel heartIntroModel, String[] tokens){
        if(tokens[3].equals("header")){
            heartIntroModel.setType(0);
            return;
        }
        if(tokens[3].equals("body")){
            heartIntroModel.setType(1);
            return;
        }
        if(tokens[3].equals("button")){
            heartIntroModel.setType(2);
        }
    }

    @Override
    public void writeToRealm() {
        if(mRealm.where(HeartIntroModel.class).findAll().size() == 0){
            mRealm.executeTransactionAsync(new Realm.Transaction() {
                @Override
                public void execute(@NonNull Realm realm) {
                    for (int i = 0; i < mHeartIntroModels.size(); i++) {
                        HeartIntroModel dataModel = realm.createObject(HeartIntroModel.class);
                        dataModel.setId(mHeartIntroModels.get(i).getId());
                        dataModel.setParentId(mHeartIntroModels.get(i).getParentId());
                        dataModel.setDescription(mHeartIntroModels.get(i).getDescription());
                        dataModel.setType(mHeartIntroModels.get(i).getType());
                        dataModel.setIsOpened(mHeartIntroModels.get(i).getIsOpened());
                        dataModel.setShowed(mHeartIntroModels.get(i).getIsShowed());
                        mRealmHeartIntroModels.add(dataModel);
                    }
                }
            }, new Realm.Transaction.OnSuccess() {
                @Override
                public void onSuccess() {
                    mHeartIntroModels.clear();
                    handlerLooping(getStoryModels(0), mIsNew = true);
                }
            }, new Realm.Transaction.OnError() {
                @Override
                public void onError(@NonNull Throwable error) {
                    Log.d("REALM_DAN", error.getMessage());
                }
            });
        }
    }

    @Override
    public void animateRecycler() {
        if(!mIsNew){
            if(isViewAttached())
                mView.scrollToEnd();
        }
        else{
            if(isViewAttached())
                mView.animateView();
        }
    }

    @Override
    public void blockButtons(HeartIntroModel heartIntroModel) {
        mOldOpenedStoryModels = getIsOpenedStoryModels().size();
        RealmResults<HeartIntroModel> buttons = mRealm.where(HeartIntroModel.class)
                .equalTo("parentId", heartIntroModel.getParentId())
                .and()
                .equalTo("type", 2)
                .findAll();
        for (int j = 0; j < buttons.size(); j++) {
            Log.d("CLICKEN", "onButtonClick: " + buttons.get(j).toString());
            mRealm.beginTransaction();
            buttons.get(j).setShowed(false);
            mRealm.commitTransaction();
        }
        if(isViewAttached()){
            mView.notifyDataAdapter();
        }
    }

    @Override
    public void handlerLooping(final RealmResults<HeartIntroModel> introModels, boolean isNew) {
        for (int i = 0; i < introModels.size(); i++) {
            if(!mRealm.isClosed()){
                mRealm.beginTransaction();
                introModels.get(i).setIsOpened(1);
                mRealm.commitTransaction();
            }
        }
        if(isViewAttached()){
            mView.refreshAdapter(isNew);
        }
    }

    @Override
    public RealmResults<HeartIntroModel> getIsOpenedStoryModels() {
        return mRealm.where(HeartIntroModel.class)
                .equalTo("isOpened", 1)
                .findAll();
    }

    @Override
    public RealmResults<HeartIntroModel> getStoryModels(int id) {
        return mRealm.where(HeartIntroModel.class)
                .equalTo("parentId", id)
                .findAll();
    }

    @Override
    public int getOldOpenedStoryModels() {
        return mOldOpenedStoryModels;
    }

    @Override
    public void closeRealm() {
        mRealm.close();
    }


    @Override
    public void bind(HeartIntroContract.View view) {
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
