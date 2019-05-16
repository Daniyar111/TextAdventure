package com.text_adventure.daniyar.textadventure.ui.heart_of_ice.game.adapter.viewholder;

import android.view.View;
import android.widget.Button;

import com.text_adventure.daniyar.textadventure.R;
import com.text_adventure.daniyar.textadventure.data.entity.HeartStoryModel;
import com.text_adventure.daniyar.textadventure.ui.heart_of_ice.game.adapter.BaseViewHolder;

import io.realm.RealmResults;

public class GetHealthTypeViewHolder extends BaseViewHolder{

    private Button mButtonGetHealth;

    public GetHealthTypeViewHolder(View itemView) {
        super(itemView);

        mButtonGetHealth = itemView.findViewById(R.id.buttonGetHealth);
    }

    @Override
    protected void onBindView(RealmResults<HeartStoryModel> heartStoryModels, int position) {

        mButtonGetHealth.setText(heartStoryModels.get(position).getDescription());
    }
}
