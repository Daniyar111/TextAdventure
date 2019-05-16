package com.text_adventure.daniyar.textadventure.ui.heart_of_ice.game.adapter.viewholder;

import android.view.View;
import android.widget.Button;

import com.text_adventure.daniyar.textadventure.R;
import com.text_adventure.daniyar.textadventure.data.entity.HeartStoryModel;
import com.text_adventure.daniyar.textadventure.ui.heart_of_ice.game.adapter.BaseViewHolder;

import io.realm.RealmResults;

public class SellTypeViewHolder extends BaseViewHolder {

    private Button mButtonSell;

    public SellTypeViewHolder(View itemView) {
        super(itemView);

        mButtonSell = itemView.findViewById(R.id.buttonSell);
    }

    @Override
    protected void onBindView(RealmResults<HeartStoryModel> heartStoryModels, int position) {

        mButtonSell.setText(heartStoryModels.get(position).getDescription());
    }
}
