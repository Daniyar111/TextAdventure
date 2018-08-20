package com.text_adventure.daniyar.textadventure.ui.heart_of_ice.intro.adapter.viewholder;

import android.view.View;
import android.widget.TextView;

import com.text_adventure.daniyar.textadventure.R;
import com.text_adventure.daniyar.textadventure.data.entity.HeartIntroModel;
import com.text_adventure.daniyar.textadventure.ui.heart_of_ice.intro.adapter.BaseViewHolder;

public class BodyTypeViewHolder extends BaseViewHolder {

    private TextView mTextViewRule;

    public BodyTypeViewHolder(View itemView) {
        super(itemView);
        mTextViewRule = itemView.findViewById(R.id.textViewRule);
    }

    @Override
    protected void onBindView(HeartIntroModel heartIntroModel) {
        mTextViewRule.setText(heartIntroModel.getDescription());
    }

}
