package com.text_adventure.daniyar.textadventure.ui.heart_of_ice.game.adapter.viewholder;

import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.text_adventure.daniyar.textadventure.R;
import com.text_adventure.daniyar.textadventure.data.entity.HeartStoryModel;
import com.text_adventure.daniyar.textadventure.data.manager.ResourceManager;
import com.text_adventure.daniyar.textadventure.ui.heart_of_ice.game.adapter.BaseViewHolder;
import com.text_adventure.daniyar.textadventure.ui.heart_of_ice.game.adapter.OnHeartGameChoiceClick;

import java.util.HashSet;
import java.util.Set;

import io.realm.RealmResults;

public class AliveButtonTypeViewHolder extends BaseViewHolder {

    private FrameLayout mButtonChoice;
    private ImageView mImageView;
    private TextView mTextViewDescription;
    private OnHeartGameChoiceClick mChoiceClick;
    private ResourceManager mResourceManager;
    private Set<Integer> mChosenSet = new HashSet<>();

    public AliveButtonTypeViewHolder(View itemView, OnHeartGameChoiceClick choiceClick, ResourceManager resourceManager) {
        super(itemView);

        mButtonChoice = itemView.findViewById(R.id.buttonChoice);
        mImageView = itemView.findViewById(R.id.imageView);
        mTextViewDescription = itemView.findViewById(R.id.textViewDescription);
        mChoiceClick = choiceClick;
        mResourceManager = resourceManager;
    }

    @Override
    protected void onBindView(final RealmResults<HeartStoryModel> heartStoryModels, final int position) {
        mTextViewDescription.setText(heartStoryModels.get(position).getDescription());

        mButtonChoice.setOnFocusChangeListener(null);

        mImageView.setImageResource(mChosenSet.contains(position) ? R.drawable.button_nohover : R.drawable.button_hover);

        if(heartStoryModels.get(position).getIsChosen()){
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

        mButtonChoice.setEnabled(heartStoryModels.get(position).getIsShowed());

        mButtonChoice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mChoiceClick.onButtonClick(heartStoryModels.get(position));
            }
        });
    }
}
