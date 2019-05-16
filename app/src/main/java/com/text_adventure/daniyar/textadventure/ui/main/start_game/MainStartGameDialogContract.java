package com.text_adventure.daniyar.textadventure.ui.main.start_game;

import com.text_adventure.daniyar.textadventure.data.entity.BookModel;
import com.text_adventure.daniyar.textadventure.data.entity.HeartPersonModel;
import com.text_adventure.daniyar.textadventure.ui.LifeCycle;

public interface MainStartGameDialogContract {

    interface View{

        void showSaved();

        void showNew();
    }

    interface Presenter extends LifeCycle<View> {

        void showPersonInfo(String person);

        void deleteDataRealm();

        void closeRealm();
    }
}
