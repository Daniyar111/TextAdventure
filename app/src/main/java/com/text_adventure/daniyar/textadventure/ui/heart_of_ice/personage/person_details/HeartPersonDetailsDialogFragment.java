package com.text_adventure.daniyar.textadventure.ui.heart_of_ice.personage.person_details;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.text_adventure.daniyar.textadventure.R;
import com.text_adventure.daniyar.textadventure.data.entity.HeartPersonModel;
import com.text_adventure.daniyar.textadventure.ui.BaseDialogFragment;
import com.text_adventure.daniyar.textadventure.ui.heart_of_ice.personage.HeartGameStartCallBack;

import io.realm.Realm;

public class HeartPersonDetailsDialogFragment extends BaseDialogFragment implements HeartPersonDetailsDialogContract.View, View.OnClickListener {

    private Context mContext;
    private HeartPersonDetailsDialogPresenter mPresenter;
    private ImageView mImagePerson;
    private TextView mTextViewName, mTextViewDescription, mTextViewSkillFirst, mTextViewSkillSecond, mTextViewSkillThird, mTextViewSkillForth;
    private String mPerson;
    private Button mButtonStart;
    private HeartGameStartCallBack mCallBack;

    @Override
    protected int getViewLayout() {
        return R.layout.dialog_fragment_heart_person_details;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if(getActivity() != null){
            mContext = getActivity().getApplicationContext();
        }

        mPresenter = new HeartPersonDetailsDialogPresenter(Realm.getDefaultInstance());
        mPresenter.bind(this);

        removeDialogToolbarAndSetAnimation();
        initializeViews(view);
        getBundleArg();
    }

    private void initializeViews(View view){

        mImagePerson = view.findViewById(R.id.imagePerson);
        mTextViewName = view.findViewById(R.id.textViewName);
        mTextViewDescription = view.findViewById(R.id.textViewDescription);
        mTextViewSkillFirst = view.findViewById(R.id.textViewSkillFirst);
        mTextViewSkillSecond = view.findViewById(R.id.textViewSkillSecond);
        mTextViewSkillThird = view.findViewById(R.id.textViewSkillThird);
        mTextViewSkillForth = view.findViewById(R.id.textViewSkillForth);
        mButtonStart = view.findViewById(R.id.buttonStart);
        mButtonStart.setOnClickListener(this);
    }

    private void getBundleArg(){
        Bundle bundle = this.getArguments();
        if(bundle != null){
            mPerson = bundle.getString("person");
        }
        mPresenter.showPersonInfo(mPerson);
    }

    @Override
    public void showDetails(HeartPersonModel personModel) {

        mImagePerson.setImageResource(personModel.getImageId());
        mTextViewName.setText(personModel.getName());
        mTextViewDescription.setText(personModel.getDescription());
        mTextViewSkillFirst.setText(String.format("%s: %s", personModel.getSkills().get(0).getName(), personModel.getSkills().get(0).getDescription()));
        mTextViewSkillSecond.setText(String.format("%s: %s", personModel.getSkills().get(1).getName(), personModel.getSkills().get(1).getDescription()));
        mTextViewSkillThird.setText(String.format("%s: %s", personModel.getSkills().get(2).getName(), personModel.getSkills().get(2).getDescription()));
        mTextViewSkillForth.setText(String.format("%s: %s", personModel.getSkills().get(3).getName(), personModel.getSkills().get(3).getDescription()));
    }

    @Override
    public void onClick(View view) {
        mCallBack.onStartClicked();
        dismiss();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mCallBack = (HeartGameStartCallBack) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement HeartGameStartCallBack");
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.unbind();
        mPresenter.closeRealm();
    }
}
