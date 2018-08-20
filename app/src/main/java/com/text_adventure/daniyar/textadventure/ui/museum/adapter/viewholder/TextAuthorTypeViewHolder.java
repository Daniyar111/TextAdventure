package com.text_adventure.daniyar.textadventure.ui.museum.adapter.viewholder;

import android.view.View;
import android.widget.TextView;

import com.text_adventure.daniyar.textadventure.R;
import com.text_adventure.daniyar.textadventure.data.entity.MuseumStoryModel;
import com.text_adventure.daniyar.textadventure.ui.museum.adapter.BaseViewHolder;

import io.realm.RealmResults;

public class TextAuthorTypeViewHolder extends BaseViewHolder {

    private TextView mTextViewDescription;

    public TextAuthorTypeViewHolder(View itemView) {
        super(itemView);
        mTextViewDescription = itemView.findViewById(R.id.textViewDescription);
    }

    @Override
    protected void onBindView(RealmResults<MuseumStoryModel> museumStoryModels, int position) {
        mTextViewDescription.setText(museumStoryModels.get(position).getDescription());
    }
}
