package com.text_adventure.daniyar.textadventure.ui.heart_of_ice.intro.adapter.viewholder;

import android.view.View;
import android.widget.TextView;

import com.text_adventure.daniyar.textadventure.R;
import com.text_adventure.daniyar.textadventure.data.entity.HeartIntroModel;
import com.text_adventure.daniyar.textadventure.ui.heart_of_ice.intro.adapter.BaseViewHolder;

public class HeaderTypeViewHolder extends BaseViewHolder {

    private TextView mTextViewHeader;

    public HeaderTypeViewHolder(View itemView) {
        super(itemView);
        mTextViewHeader = itemView.findViewById(R.id.textViewHeader);
    }

    @Override
    protected void onBindView(HeartIntroModel heartIntroModel) {
        mTextViewHeader.setText(heartIntroModel.getDescription());
    }

}
