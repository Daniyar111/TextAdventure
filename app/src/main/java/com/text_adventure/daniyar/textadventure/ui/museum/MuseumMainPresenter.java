package com.text_adventure.daniyar.textadventure.ui.museum;

import android.os.Handler;
import android.support.annotation.NonNull;
import android.util.Log;

import com.text_adventure.daniyar.textadventure.R;
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

public class MuseumMainPresenter implements MuseumMainContract.Presenter {

    private MuseumMainContract.View mView;
    private Realm mRealm;
    private ResourceManager mResourceManager;
    private ArrayList<MuseumStoryModel> mMuseumStoryModels = new ArrayList<>();
    private ArrayList<MuseumStoryModel> mRealmMuseumStoryModels = new ArrayList<>();
    private int mOldOpenedStoryModels;
    private boolean mIsNew;

    public MuseumMainPresenter(Realm realm, ResourceManager resourceManager){
        mRealm = realm;
        mResourceManager = resourceManager;
        mOldOpenedStoryModels = 0;
        mIsNew = false;
    }

    @Override
    public void readDataCSV() {
        if(mRealm.where(MuseumStoryModel.class).findAll().size() == 0){
            InputStream inputStream = mResourceManager.getResources().openRawResource(R.raw.data_mus);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, Charset.forName("UTF-8")));

            String line = "";

            try{
                bufferedReader.readLine();

                while ((line = bufferedReader.readLine()) != null){
                    Log.d("DAN_DATA", "Line: " + line);
                    String[] tokens = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
                    MuseumStoryModel museumStoryModel = new MuseumStoryModel();
                    museumStoryModel.setId(Integer.parseInt(tokens[0]));
                    museumStoryModel.setParentId(Integer.parseInt(tokens[1]));
                    museumStoryModel.setAuthor(tokens[2]);
                    museumStoryModel.setDescription(tokens[3].replace("\\\"",""));
                    setTypeInt(museumStoryModel, tokens);
                    museumStoryModel.setIsOpened(Integer.parseInt(tokens[5]));
                    museumStoryModel.setIsShowed(true);
                    museumStoryModel.setChosen(false);
                    mMuseumStoryModels.add(museumStoryModel);

                    Log.d("DAN_DATA", "Just created: " + museumStoryModel);
                }
            } catch (IOException e) {
                Log.wtf("DAN_DATA", "Error reading data file on line" + line, e);

                e.printStackTrace();
            }
        }
    }

    private void setTypeInt(MuseumStoryModel museumStoryModel, String[] tokens){
        if(tokens[4].equals("text_person")){
            museumStoryModel.setType(0);
            return;
        }
        if(tokens[4].equals("text_author")){
            museumStoryModel.setType(1);
            return;
        }
        if(tokens[4].equals("text_user")){
            museumStoryModel.setType(2);
            return;
        }
        if(tokens[4].equals("button")){
            museumStoryModel.setType(3);
        }
    }

    @Override
    public void writeToRealm() {
        if(mRealm.where(MuseumStoryModel.class).findAll().size() == 0){
            mRealm.executeTransactionAsync(new Realm.Transaction() {
                @Override
                public void execute(@NonNull Realm realm) {
                    for (int i = 0; i < mMuseumStoryModels.size(); i++) {
                        MuseumStoryModel dataModel = realm.createObject(MuseumStoryModel.class);
                        dataModel.setId(mMuseumStoryModels.get(i).getId());
                        dataModel.setParentId(mMuseumStoryModels.get(i).getParentId());
                        dataModel.setAuthor(mMuseumStoryModels.get(i).getAuthor());
                        dataModel.setDescription(mMuseumStoryModels.get(i).getDescription());
                        dataModel.setType(mMuseumStoryModels.get(i).getType());
                        dataModel.setIsOpened(mMuseumStoryModels.get(i).getIsOpened());
                        dataModel.setIsShowed(mMuseumStoryModels.get(i).getIsShowed());
                        dataModel.setChosen(mMuseumStoryModels.get(i).getIsChosen());
                        mRealmMuseumStoryModels.add(dataModel);
                    }
                }
            }, new Realm.Transaction.OnSuccess() {
                @Override
                public void onSuccess() {
                    mMuseumStoryModels.clear();
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
    public void startAgain() {
        mRealm.beginTransaction();
        mRealm.delete(MuseumStoryModel.class);
        mRealm.commitTransaction();
        readDataCSV();
        writeToRealm();
        if(isViewAttached()){
            mView.notifyDataAdapter();
        }
    }

    @Override
    public void blockButtons(MuseumStoryModel museumStoryModel) {
        mOldOpenedStoryModels = getIsOpenedStoryModels().size();
        RealmResults<MuseumStoryModel> buttons = mRealm.where(MuseumStoryModel.class)
                .equalTo("parentId", museumStoryModel.getParentId())
                .and()
                .equalTo("type", 3)
                .findAll();
        mRealm.beginTransaction();
        museumStoryModel.setChosen(true);
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
    public void handlerLooping(final RealmResults<MuseumStoryModel> museumStoryModels, boolean isNew){
        for (int i = 0; i < museumStoryModels.size(); i++) {

            if (!mRealm.isClosed()) {
                mRealm.beginTransaction();
                museumStoryModels.get(i).setIsOpened(1);
                mRealm.commitTransaction();
            }
            Log.d("DANCH", "run: running");
        }
        if (isViewAttached()) {
            mView.refreshAdapter(isNew);
        }
    }

    @Override
    public RealmResults<MuseumStoryModel> getIsOpenedStoryModels() {
        return mRealm.where(MuseumStoryModel.class)
                .equalTo("isOpened", 1)
                .findAll();
    }

    @Override
    public RealmResults<MuseumStoryModel> getStoryModels(int id){
        return mRealm.where(MuseumStoryModel.class)
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
    public void bind(MuseumMainContract.View view) {
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
