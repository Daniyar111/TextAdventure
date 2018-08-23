package com.text_adventure.daniyar.textadventure.ui.heart_of_ice.game.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.text_adventure.daniyar.textadventure.data.entity.HeartStoryModel;

import io.realm.RealmResults;

public abstract class BaseViewHolder extends RecyclerView.ViewHolder{

    public BaseViewHolder(View itemView) {
        super(itemView);
    }

    protected abstract void onBindView(RealmResults<HeartStoryModel> heartStoryModels, int position);
}
