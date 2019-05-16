package com.text_adventure.daniyar.textadventure.ui.heart_of_ice.game;

import android.support.annotation.NonNull;
import android.util.Log;

import com.text_adventure.daniyar.textadventure.R;
import com.text_adventure.daniyar.textadventure.data.entity.HeartStoryModel;
import com.text_adventure.daniyar.textadventure.data.manager.ResourceManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmResults;

public class HeartGamePresenter implements HeartGameContract.Presenter{

    private HeartGameContract.View mView;
    private Realm mRealm;
    private ResourceManager mResourceManager;
    private ArrayList<HeartStoryModel> mHeartStoryModels = new ArrayList<>();
    private ArrayList<HeartStoryModel> mRealmHeartStoryModels = new ArrayList<>();
    private int mOldOpenedStoryModels;
    private boolean mIsNew;

    public HeartGamePresenter(Realm realm, ResourceManager resourceManager){
        mRealm = realm;
        mResourceManager = resourceManager;
        mOldOpenedStoryModels = 0;
        mIsNew = false;
    }

    @Override
    public void readDataCSV() {
        if(mRealm.where(HeartStoryModel.class).findAll().size() == 0){
            InputStream inputStream = mResourceManager.getResources().openRawResource(R.raw.data_heart);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, Charset.forName("UTF-8")));

            String line = "";

            try{
                bufferedReader.readLine();

                while ((line = bufferedReader.readLine()) != null){
                    Log.d("DAN_DATA", "Line: " + line);
                    String[] tokens = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
                    HeartStoryModel heartStoryModel = new HeartStoryModel();
                    heartStoryModel.setId(Integer.parseInt(tokens[0]));
                    heartStoryModel.setParentId(Integer.parseInt(tokens[1]));
                    heartStoryModel.setDescription(tokens[2].replace("\\\"",""));
                    setTypeInt(heartStoryModel, tokens);
                    heartStoryModel.setIsOpened(0);
                    heartStoryModel.setIsShowed(true);
                    heartStoryModel.setChosen(false);
                    mHeartStoryModels.add(heartStoryModel);

                    Log.d("DAN_DATA", "Just created: " + heartStoryModel);
                }
            } catch (IOException e) {
                Log.wtf("DAN_DATA", "Error reading data file on line" + line, e);

                e.printStackTrace();
            }
        }
    }

    private void setTypeInt(HeartStoryModel heartStoryModel, String[] tokens){
        if(tokens[3].equals("text")){
            heartStoryModel.setType(0);
            return;
        }
        if(tokens[3].equals("button")){
            heartStoryModel.setType(1);
            return;
        }
        if(tokens[3].equals("key")){
            heartStoryModel.setType(2);
            return;
        }
        if(tokens[3].equals("lost_hp")){
            heartStoryModel.setType(3);
            return;
        }
        if(tokens[3].equals("dead")){
            heartStoryModel.setType(4);
            return;
        }
        if(tokens[3].equals("alive_text")){
            heartStoryModel.setType(5);
            return;
        }
        if(tokens[3].equals("alive_text_lost")){
            heartStoryModel.setType(6);
            return;
        }
        if(tokens[3].equals("alive_key")){
            heartStoryModel.setType(7);
            return;
        }
        if(tokens[3].equals("alive_button")){
            heartStoryModel.setType(8);
            return;
        }
        if(tokens[3].equals("get_hp")){
            heartStoryModel.setType(9);
            return;
        }
        if(tokens[3].equals("text_lost")){
            heartStoryModel.setType(10);
            return;
        }
        if(tokens[3].equals("text_get")){
            heartStoryModel.setType(11);
            return;
        }
        if(tokens[3].equals("shop")){
            heartStoryModel.setType(12);
            return;
        }
        if(tokens[3].equals("sell")){
            heartStoryModel.setType(13);
        }
    }

    @Override
    public void writeToRealm() {
        if(mRealm.where(HeartStoryModel.class).findAll().size() == 0){
            mRealm.executeTransactionAsync(new Realm.Transaction() {
                @Override
                public void execute(@NonNull Realm realm) {
                    for (int i = 0; i < mHeartStoryModels.size(); i++) {
                        HeartStoryModel dataModel = realm.createObject(HeartStoryModel.class);
                        dataModel.setId(mHeartStoryModels.get(i).getId());
                        dataModel.setParentId(mHeartStoryModels.get(i).getParentId());
                        dataModel.setDescription(mHeartStoryModels.get(i).getDescription());
                        dataModel.setType(mHeartStoryModels.get(i).getType());
                        dataModel.setIsOpened(mHeartStoryModels.get(i).getIsOpened());
                        dataModel.setIsShowed(mHeartStoryModels.get(i).getIsShowed());
                        dataModel.setChosen(mHeartStoryModels.get(i).getIsChosen());
                        mRealmHeartStoryModels.add(dataModel);
                    }
                }
            }, new Realm.Transaction.OnSuccess() {
                @Override
                public void onSuccess() {
                    mHeartStoryModels.clear();
                    handlerLooping(getStoryModels(1), mIsNew = true);
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
    public void handlerLooping(RealmResults<HeartStoryModel> heartStoryModels, boolean isNew) {
        for (int i = 0; i < heartStoryModels.size(); i++) {

            if (!mRealm.isClosed()) {
                mRealm.beginTransaction();
                heartStoryModels.get(i).setIsOpened(1);
                mRealm.commitTransaction();
            }
            Log.d("DANCH", "run: running");
        }
        if (isViewAttached()) {
            mView.refreshAdapter(isNew);
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
    public void startAgain() {
        mRealm.beginTransaction();
        mRealm.delete(HeartStoryModel.class);
        mRealm.commitTransaction();
        readDataCSV();
        writeToRealm();
        if(isViewAttached()){
            mView.notifyDataAdapter();
        }
    }

    @Override
    public void blockButtons(HeartStoryModel heartStoryModel) {
        mOldOpenedStoryModels = getIsOpenedStoryModels().size();
        RealmResults<HeartStoryModel> buttons = mRealm.where(HeartStoryModel.class)
                .equalTo("parentId", heartStoryModel.getParentId())
                .and()
                .equalTo("type", 1)
                .findAll();
        mRealm.beginTransaction();
        heartStoryModel.setChosen(true);
        mRealm.commitTransaction();
        for (int j = 0; j < buttons.size(); j++) {
            mRealm.beginTransaction();
            buttons.get(j).setIsShowed(false);
            mRealm.commitTransaction();
        }
        if(isViewAttached()){
            mView.notifyDataAdapter();
        }
    }

    @Override
    public RealmResults<HeartStoryModel> getIsOpenedStoryModels() {
        return mRealm.where(HeartStoryModel.class)
                .equalTo("isOpened", 1)
                .findAll();
    }

    @Override
    public RealmResults<HeartStoryModel> getStoryModels(int id) {
        return mRealm.where(HeartStoryModel.class)
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
