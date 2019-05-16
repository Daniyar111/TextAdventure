package com.text_adventure.daniyar.textadventure.ui.museum.adapter.viewholder;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.text_adventure.daniyar.textadventure.R;
import com.text_adventure.daniyar.textadventure.data.entity.MuseumStoryModel;
import com.text_adventure.daniyar.textadventure.data.manager.ResourceManager;
import com.text_adventure.daniyar.textadventure.ui.museum.adapter.BaseViewHolder;
import com.text_adventure.daniyar.textadventure.ui.museum.adapter.OnMuseumChoiceClick;

import java.util.HashSet;
import java.util.Set;

import io.realm.RealmResults;

public class ChoiceTypeViewHolder extends BaseViewHolder{

    private FrameLayout mButtonChoice;
    private ImageView mImageView;
    private TextView mTextViewDescription;
    private OnMuseumChoiceClick mChoiceClick;
    private ResourceManager mResourceManager;
    private Set<Integer> mChosenSet = new HashSet<>();

    public ChoiceTypeViewHolder(View itemView, OnMuseumChoiceClick choiceClick, ResourceManager resourceManager) {
        super(itemView);
        mButtonChoice = itemView.findViewById(R.id.buttonChoice);
        mImageView = itemView.findViewById(R.id.imageView);
        mTextViewDescription = itemView.findViewById(R.id.textViewDescription);
        mChoiceClick = choiceClick;
        mResourceManager = resourceManager;
    }

    @Override
    protected void onBindView(final RealmResults<MuseumStoryModel> museumStoryModels, final int position) {
        mTextViewDescription.setText(museumStoryModels.get(position).getDescription());

        mButtonChoice.setOnFocusChangeListener(null);

        mImageView.setImageResource(mChosenSet.contains(position) ? R.drawable.button_nohover : R.drawable.button_hover);

        if(museumStoryModels.get(position).getIsChosen()){
            mImageView.setImageResource(R.drawable.button_nohover);
        }
        mButtonChoice.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                    mChosenSet.add(position);
                }
                else{
                    mChosenSet.remove(position);
                }
            }
        });

        mButtonChoice.setEnabled(museumStoryModels.get(position).getIsShowed());

        mButtonChoice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mChoiceClick.onButtonClick(museumStoryModels.get(position));
            }
        });

    }
}
