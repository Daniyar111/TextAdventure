package com.text_adventure.daniyar.textadventure.ui.heart_of_ice.intro.adapter.viewholder;

import android.view.View;
import android.widget.Button;

import com.text_adventure.daniyar.textadventure.R;
import com.text_adventure.daniyar.textadventure.data.entity.HeartIntroModel;
import com.text_adventure.daniyar.textadventure.ui.heart_of_ice.intro.adapter.BaseViewHolder;
import com.text_adventure.daniyar.textadventure.ui.heart_of_ice.intro.adapter.OnHeartIntroChoiceClick;

public class ButtonTypeViewHolder extends BaseViewHolder {

    private Button mButtonNext;
    private OnHeartIntroChoiceClick mChoiceClick;

    public ButtonTypeViewHolder(View itemView, OnHeartIntroChoiceClick choiceClick) {
        super(itemView);
        mButtonNext = itemView.findViewById(R.id.buttonNext);
        mChoiceClick = choiceClick;
    }

    @Override
    protected void onBindView(final HeartIntroModel heartIntroModel) {
        mButtonNext.setText(heartIntroModel.getDescription());
        mButtonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mChoiceClick.onButtonClick(heartIntroModel);
            }
        });
        mButtonNext.setEnabled(heartIntroModel.getIsShowed());
    }
}
