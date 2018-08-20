package com.text_adventure.daniyar.textadventure.ui.museum;

import com.text_adventure.daniyar.textadventure.data.entity.MuseumStoryModel;
import com.text_adventure.daniyar.textadventure.ui.LifeCycle;

import io.realm.RealmResults;

public interface MuseumMainContract {

    interface View{

        void refreshAdapter(boolean isNew);

        void notifyDataAdapter();

        void scrollToEnd();

        void animateView();
    }

    interface Presenter extends LifeCycle<View>{

        void readDataCSV();

        void writeToRealm();

        void animateRecycler();

        void startAgain();

        void blockButtons(MuseumStoryModel museumStoryModel);

        void handlerLooping(RealmResults<MuseumStoryModel> museumStoryModels, boolean isNew);

        RealmResults<MuseumStoryModel> getIsOpenedStoryModels();

        RealmResults<MuseumStoryModel> getStoryModels(int id);

        int getOldOpenedStoryModels();

        void closeRealm();
    }
}
