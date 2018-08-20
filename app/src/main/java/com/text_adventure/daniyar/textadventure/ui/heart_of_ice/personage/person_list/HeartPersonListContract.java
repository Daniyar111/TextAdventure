package com.text_adventure.daniyar.textadventure.ui.heart_of_ice.personage.person_list;

import com.text_adventure.daniyar.textadventure.data.entity.HeartPersonModel;
import com.text_adventure.daniyar.textadventure.ui.LifeCycle;

import io.realm.RealmResults;

public interface HeartPersonListContract {

    interface View{

        void refreshAdapter();
    }

    interface Presenter extends LifeCycle<View>{

        void readDataCSV();

        void writeToRealm();

        void refreshList();

        RealmResults<HeartPersonModel> getHeartPersonModels();

        void closeRealm();
    }
}
