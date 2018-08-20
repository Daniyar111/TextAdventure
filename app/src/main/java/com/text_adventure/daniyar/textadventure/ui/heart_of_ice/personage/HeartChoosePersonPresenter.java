package com.text_adventure.daniyar.textadventure.ui.heart_of_ice.personage;

public class HeartChoosePersonPresenter implements HeartChoosePersonContract.Presenter {

    private HeartChoosePersonContract.View mView;

    public HeartChoosePersonPresenter(){

    }

    @Override
    public void bind(HeartChoosePersonContract.View view) {
        mView = view;
    }

    @Override
    public void unbind() {
        mView = null;
    }

    private boolean isViewAttached(){
        return mView != null;
    }
}
