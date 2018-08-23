package com.text_adventure.daniyar.textadventure.ui.heart_of_ice.game.adapter.viewholder;

import android.view.View;
import android.widget.TextView;

import com.text_adventure.daniyar.textadventure.R;
import com.text_adventure.daniyar.textadventure.data.entity.HeartStoryModel;
import com.text_adventure.daniyar.textadventure.ui.heart_of_ice.game.adapter.BaseViewHolder;

import io.realm.RealmResults;

public class TextTypeViewHolder extends BaseViewHolder {

    private TextView mTextViewDescription;

    public TextTypeViewHolder(View itemView) {
        super(itemView);
        mTextViewDescription = itemView.findViewById(R.id.textViewDescription);
    }

    @Override
    protected void onBindView(RealmResults<HeartStoryModel> heartStoryModels, int position) {
        mTextViewDescription.setText(heartStoryModels.get(position).getDescription());
    }
}
