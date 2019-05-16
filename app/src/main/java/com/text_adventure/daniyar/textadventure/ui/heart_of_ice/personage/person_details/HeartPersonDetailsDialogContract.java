package com.text_adventure.daniyar.textadventure.ui.heart_of_ice.personage.person_details;

import com.text_adventure.daniyar.textadventure.data.entity.HeartPersonModel;
import com.text_adventure.daniyar.textadventure.ui.LifeCycle;

public interface HeartPersonDetailsDialogContract {

    interface View{

        void showDetails(HeartPersonModel personModel);
    }

    interface Presenter extends LifeCycle<View> {

        void showPersonInfo(String person);

        void closeRealm();
    }
}
