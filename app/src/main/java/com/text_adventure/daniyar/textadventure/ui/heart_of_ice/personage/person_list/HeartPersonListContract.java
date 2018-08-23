package com.text_adventure.daniyar.textadventure.ui.heart_of_ice.personage.person_list;

import android.os.Bundle;
import android.widget.AdapterView;

import com.text_adventure.daniyar.textadventure.data.entity.HeartPersonModel;
import com.text_adventure.daniyar.textadventure.ui.LifeCycle;

import io.realm.RealmResults;

public interface HeartPersonListContract {

    interface View{

        void refreshAdapter();

        void showDetails(Bundle bundle);
    }

    interface Presenter extends LifeCycle<View>{

        void readDataCSV();

        void writeToRealm();

        void refreshList();

        void onListViewItemClicked(AdapterView<?> adapterView, int position);

        RealmResults<HeartPersonModel> getHeartPersonModels();

        void closeRealm();
    }
}
