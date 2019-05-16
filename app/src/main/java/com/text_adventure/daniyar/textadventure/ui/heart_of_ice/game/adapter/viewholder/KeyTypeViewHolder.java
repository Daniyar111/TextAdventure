package com.text_adventure.daniyar.textadventure.ui.heart_of_ice.game.adapter.viewholder;

import android.view.View;
import android.widget.Button;

import com.text_adventure.daniyar.textadventure.R;
import com.text_adventure.daniyar.textadventure.data.entity.HeartStoryModel;
import com.text_adventure.daniyar.textadventure.ui.heart_of_ice.game.adapter.BaseViewHolder;

import io.realm.RealmResults;

public class KeyTypeViewHolder extends BaseViewHolder{

    private Button mButtonKey;

    public KeyTypeViewHolder(View itemView) {
        super(itemView);

        mButtonKey = itemView.findViewById(R.id.buttonKey);
    }

    @Override
    protected void onBindView(RealmResults<HeartStoryModel> heartStoryModels, int position) {

        mButtonKey.setText(heartStoryModels.get(position).getDescription());
    }
}
