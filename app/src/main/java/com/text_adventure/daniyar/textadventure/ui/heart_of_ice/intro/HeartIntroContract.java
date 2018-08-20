package com.text_adventure.daniyar.textadventure.ui.heart_of_ice.intro;

import com.text_adventure.daniyar.textadventure.data.entity.HeartIntroModel;
import com.text_adventure.daniyar.textadventure.ui.LifeCycle;

import io.realm.RealmResults;

public interface HeartIntroContract {

    interface View{

        void refreshAdapter(boolean isNew);

        void scrollToEnd();

        void animateView();

        void notifyDataAdapter();
    }

    interface Presenter extends LifeCycle<View>{

        void readDataCSV();

        void writeToRealm();

        void animateRecycler();

        void blockButtons(HeartIntroModel heartIntroModel);

        void handlerLooping(RealmResults<HeartIntroModel> introModels, boolean isNew);

        RealmResults<HeartIntroModel> getIsOpenedStoryModels();

        RealmResults<HeartIntroModel> getStoryModels(int id);

        int getOldOpenedStoryModels();

        void closeRealm();
    }
}
