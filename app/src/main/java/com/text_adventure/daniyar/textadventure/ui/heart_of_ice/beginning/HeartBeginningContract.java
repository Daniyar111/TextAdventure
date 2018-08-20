package com.text_adventure.daniyar.textadventure.ui.heart_of_ice.beginning;

import com.text_adventure.daniyar.textadventure.ui.LifeCycle;

import java.util.ArrayList;

public interface HeartBeginningContract {

    interface View{

        void setRulesAdapter(ArrayList<String> rulesList);

        void setDisabled();
    }

    interface Presenter extends LifeCycle<View>{

        void getBeginningStory();

        void setEnabledContinue();

        void closeRealm();
    }
}
