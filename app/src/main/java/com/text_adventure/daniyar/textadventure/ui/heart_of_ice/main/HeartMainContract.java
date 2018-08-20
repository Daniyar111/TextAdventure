package com.text_adventure.daniyar.textadventure.ui.heart_of_ice.main;

import com.text_adventure.daniyar.textadventure.ui.LifeCycle;

public interface HeartMainContract {

    interface View{

    }

    interface Presenter extends LifeCycle<View>{

        void deleteDataRealm();

        void closeRealm();
    }
}
