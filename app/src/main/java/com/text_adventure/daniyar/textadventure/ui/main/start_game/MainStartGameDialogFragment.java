package com.text_adventure.daniyar.textadventure.ui.main.start_game;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.text_adventure.daniyar.textadventure.R;
import com.text_adventure.daniyar.textadventure.data.entity.BookModel;
import com.text_adventure.daniyar.textadventure.data.entity.HeartPersonModel;
import com.text_adventure.daniyar.textadventure.ui.BaseDialogFragment;
import com.text_adventure.daniyar.textadventure.ui.heart_of_ice.personage.person_details.HeartPersonDetailsDialogContract;
import com.text_adventure.daniyar.textadventure.ui.heart_of_ice.personage.person_details.HeartPersonDetailsDialogPresenter;
import com.text_adventure.daniyar.textadventure.ui.museum.MuseumMainActivity;

import io.realm.Realm;

public class MainStartGameDialogFragment extends BaseDialogFragment implements MainStartGameDialogContract.View, View.OnClickListener {

    private Context mContext;
    private MainStartGameDialogPresenter mPresenter;
    private TextView mTextViewStart;
    private Button mButtonStart, mButtonContinue;
    private String mBook = "";

    @Override
    protected int getViewLayout() {
        return R.layout.dialog_fragment_start_game;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if(getActivity() != null){
            mContext = getActivity().getApplicationContext();
        }

        mPresenter = new MainStartGameDialogPresenter(Realm.getDefaultInstance());
        mPresenter.bind(this);

        removeDialogToolbarAndSetAnimation();
        initializeViews(view);
        getBundleArg();
        mButtonStart.setOnClickListener(this);
        mButtonContinue.setOnClickListener(this);
    }

    private void initializeViews(View view){

        mTextViewStart = view.findViewById(R.id.textViewStart);
        mButtonStart = view.findViewById(R.id.buttonStart);
        mButtonContinue = view.findViewById(R.id.buttonContinue);

    }

    private void getBundleArg(){
        Bundle bundle = this.getArguments();
        if(bundle != null){
            mBook = bundle.getString("book");
        }
        mPresenter.showPersonInfo(mBook);
    }

    @Override
    public void showSaved() {

        mTextViewStart.setText(getString(R.string.yes_save));
    }

    @Override
    public void showNew() {

        mTextViewStart.setText(getString(R.string.no_save));
        mButtonContinue.setEnabled(false);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.buttonStart:
                mPresenter.deleteDataRealm();
                startActivity(new Intent(getActivity(), MuseumMainActivity.class));
                this.dismiss();
                break;
            case R.id.buttonContinue:
                startActivity(new Intent(getActivity(), MuseumMainActivity.class));
                this.dismiss();
                break;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.unbind();
        mPresenter.closeRealm();
    }
}
