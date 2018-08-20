package com.text_adventure.daniyar.textadventure.ui.heart_of_ice.personage.person_list;

import android.support.annotation.NonNull;
import android.util.Log;

import com.text_adventure.daniyar.textadventure.R;
import com.text_adventure.daniyar.textadventure.data.entity.HeartIntroModel;
import com.text_adventure.daniyar.textadventure.data.entity.HeartPersonEquipmentModel;
import com.text_adventure.daniyar.textadventure.data.entity.HeartPersonModel;
import com.text_adventure.daniyar.textadventure.data.entity.HeartPersonSkillModel;
import com.text_adventure.daniyar.textadventure.data.manager.ResourceManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmResults;

public class HeartPersonListPresenter implements HeartPersonListContract.Presenter {

    private HeartPersonListContract.View mView;
    private Realm mRealm;
    private ResourceManager mResourceManager;
    private ArrayList<HeartPersonModel> mHeartPersonModels = new ArrayList<>();
    private List<HeartPersonSkillModel> mHeartPersonSkillModels = new ArrayList<>();
    private ArrayList<HeartPersonModel> mRealmHeartPersonModels = new ArrayList<>();

    public HeartPersonListPresenter(Realm realm, ResourceManager resourceManager){
        mRealm = realm;
        mResourceManager = resourceManager;
    }

    @Override
    public void readDataCSV() {
        if(mRealm.where(HeartPersonModel.class).findAll().size() == 0){

            InputStream inputStreamSkills = mResourceManager.getResources().openRawResource(R.raw.data_heart_person_skills);
            BufferedReader bufferedReaderSkills = new BufferedReader(new InputStreamReader(inputStreamSkills, Charset.forName("UTF-8")));

            String lineSkills = "";
            try{
                bufferedReaderSkills.readLine();
                while ((lineSkills = bufferedReaderSkills.readLine()) != null){
                    Log.d("DATAPERSON", "Line: " + lineSkills);
                    String[] tokens = lineSkills.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
                    HeartPersonSkillModel heartPersonSkillModel = new HeartPersonSkillModel();
                    HeartPersonEquipmentModel heartPersonEquipmentModel = new HeartPersonEquipmentModel();
                    RealmList<HeartPersonEquipmentModel> heartPersonEquipmentModels = new RealmList<>();
                    heartPersonSkillModel.setName(tokens[0]);
                    heartPersonSkillModel.setDescription(tokens[1].replace("\\\"",""));
                    heartPersonEquipmentModel.setName(tokens[2]);
                    heartPersonEquipmentModels.add(heartPersonEquipmentModel);
                    heartPersonSkillModel.setEquipments(heartPersonEquipmentModels);
                    mHeartPersonSkillModels.add(heartPersonSkillModel);

                }
                Log.d("DATAPERSON", "Just created: " + mHeartPersonSkillModels);
            } catch (IOException e){
                Log.wtf("DATAPERSON", "Error reading data file on line" + lineSkills, e);
                e.printStackTrace();
            }



            InputStream inputStream = mResourceManager.getResources().openRawResource(R.raw.data_heart_person);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, Charset.forName("UTF-8")));

            String line = "";
            try{
                bufferedReader.readLine();
                while ((line = bufferedReader.readLine()) != null){
                    Log.d("DAN_DATA", "Line: " + line);
                    String[] tokens = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
                    HeartPersonModel heartPersonModel = new HeartPersonModel();
                    heartPersonModel.setName(tokens[0]);
                    heartPersonModel.setDescription(tokens[1].replace("\\\"",""));
                    skillsArray(heartPersonModel, tokens);
                    heartPersonModel.setMoney(Integer.parseInt(tokens[3]));
                    heartPersonModel.setHealth(Integer.parseInt(tokens[4]));
                    heartPersonModel.setImageId(imagesArray()[Integer.parseInt(tokens[5])]);
                    heartPersonModel.setStatus(0);
                    mHeartPersonModels.add(heartPersonModel);
                }
//                Log.d("DANCSVDATA", "Just created: " + mHeartPersonModels);
            } catch (IOException e) {
                Log.wtf("DAN_DATA", "Error reading data file on line" + line, e);
                e.printStackTrace();
            }
        }
    }

    @Override
    public void writeToRealm() {
        if(mRealm.where(HeartPersonModel.class).findAll().size() == 0){
            final RealmList<HeartPersonEquipmentModel> equipmentModels = new RealmList<>();
            final RealmList<HeartPersonSkillModel> skillModels = new RealmList<>();
            mRealm.executeTransactionAsync(new Realm.Transaction() {
                @Override
                public void execute(@NonNull Realm realm) {
                    HeartPersonModel dataModel;
                    HeartPersonSkillModel heartPersonSkillModel;
                    HeartPersonEquipmentModel heartPersonEquipmentModel;
                    for (int i = 0; i < mHeartPersonModels.size(); i++) {
                        for (int j = 0; j < mHeartPersonModels.get(i).getSkills().size(); j++) {
                            for (int k = 0; k < mHeartPersonModels.get(i).getSkills().get(j).getEquipments().size(); k++) {
                                heartPersonEquipmentModel = realm.createObject(HeartPersonEquipmentModel.class);
                                heartPersonEquipmentModel.setName(mHeartPersonModels.get(i).getSkills().get(j).getEquipments().get(k).getName());
                                equipmentModels.add(heartPersonEquipmentModel);
                            }
                            heartPersonSkillModel = realm.createObject(HeartPersonSkillModel.class);
                            heartPersonSkillModel.setName(mHeartPersonModels.get(i).getSkills().get(j).getName());
                            heartPersonSkillModel.setDescription(mHeartPersonModels.get(i).getSkills().get(j).getDescription());
                            heartPersonSkillModel.setEquipments(equipmentModels);
                            equipmentModels.clear();
                            skillModels.add(heartPersonSkillModel);
                        }
                        dataModel = realm.createObject(HeartPersonModel.class);
                        dataModel.setName(mHeartPersonModels.get(i).getName());
                        dataModel.setDescription(mHeartPersonModels.get(i).getDescription());
                        dataModel.setSkills(skillModels);
                        skillModels.clear();
                        dataModel.setMoney(mHeartPersonModels.get(i).getMoney());
                        dataModel.setHealth(mHeartPersonModels.get(i).getHealth());
                        dataModel.setImageId(mHeartPersonModels.get(i).getImageId());
                        dataModel.setStatus(mHeartPersonModels.get(i).getStatus());
                        mRealmHeartPersonModels.add(dataModel);
                    }
                }
            }, new Realm.Transaction.OnSuccess() {
                @Override
                public void onSuccess() {
                    mHeartPersonModels.clear();
                    if(isViewAttached()){
                        mView.refreshAdapter();
                    }
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
    public void refreshList() {
        if(mRealm.where(HeartPersonModel.class).findAll().size() != 0 && isViewAttached()){
            mView.refreshAdapter();
        }
    }

    private int[] imagesArray(){

        return new int[]{R.drawable.researcher, R.drawable.spy, R.drawable.killer, R.drawable.seller, R.drawable.seer, R.drawable.mutant, R.drawable.scientist};
    }

    private void skillsArray(HeartPersonModel heartPersonModel, String[] tokens){

        String[] skills = tokens[2].split("\\|");
        RealmList<HeartPersonSkillModel> skillModels = new RealmList<>();
        for (int i = 0; i < skills.length; i++) {
            for (int j = 0; j < mHeartPersonSkillModels.size(); j++) {
                if(skills[i].equals(mHeartPersonSkillModels.get(j).getName())){
                    skillModels.add(mHeartPersonSkillModels.get(j));
                    heartPersonModel.setSkills(skillModels);
                }
            }
        }
    }

    @Override
    public RealmResults<HeartPersonModel> getHeartPersonModels() {
        Log.d("heartdani", "getHeartPersonModels: " + mRealm.where(HeartPersonModel.class).findAll().size());
        return mRealm.where(HeartPersonModel.class)
                .findAll();
    }

    @Override
    public void closeRealm() {
        mRealm.close();
    }

    @Override
    public void bind(HeartPersonListContract.View view) {
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
