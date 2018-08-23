package com.text_adventure.daniyar.textadventure.ui.heart_of_ice.game;

import com.text_adventure.daniyar.textadventure.ui.LifeCycle;

public interface HeartGameContract {

    interface View{

    }

    interface Presenter extends LifeCycle<View>{

        void closeRealm();
    }
}
