package com.text_adventure.daniyar.textadventure.ui.heart_of_ice.game;

import com.text_adventure.daniyar.textadventure.data.entity.HeartStoryModel;
import com.text_adventure.daniyar.textadventure.ui.LifeCycle;

import io.realm.RealmResults;

public interface HeartGameContract {

    interface View{

        void refreshAdapter(boolean isNew);

        void notifyDataAdapter();

        void scrollToEnd();

        void animateView();
    }

    interface Presenter extends LifeCycle<View>{

        void readDataCSV();

        void writeToRealm();

        void handlerLooping(RealmResults<HeartStoryModel> heartStoryModels, boolean isNew);

        void animateRecycler();

        void startAgain();

        void blockButtons(HeartStoryModel heartStoryModel);

        RealmResults<HeartStoryModel> getIsOpenedStoryModels();

        RealmResults<HeartStoryModel> getStoryModels(int id);

        int getOldOpenedStoryModels();

        void closeRealm();
    }
}
