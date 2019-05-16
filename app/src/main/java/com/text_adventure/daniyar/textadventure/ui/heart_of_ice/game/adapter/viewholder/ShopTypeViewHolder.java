package com.text_adventure.daniyar.textadventure.ui.heart_of_ice.game.adapter.viewholder;

import android.view.View;
import android.widget.Button;

import com.text_adventure.daniyar.textadventure.R;
import com.text_adventure.daniyar.textadventure.data.entity.HeartStoryModel;
import com.text_adventure.daniyar.textadventure.ui.heart_of_ice.game.adapter.BaseViewHolder;

import io.realm.RealmResults;

public class ShopTypeViewHolder extends BaseViewHolder {

    private Button mButtonSell, mButtonBuy;

    public ShopTypeViewHolder(View itemView) {
        super(itemView);

        mButtonBuy = itemView.findViewById(R.id.buttonBuy);
        mButtonSell = itemView.findViewById(R.id.buttonSell);
    }

    @Override
    protected void onBindView(RealmResults<HeartStoryModel> heartStoryModels, int position) {

        mButtonBuy.setText(heartStoryModels.get(position).getDescription());
        mButtonSell.setText(heartStoryModels.get(position).getDescription());
    }
}
