package com.text_adventure.daniyar.textadventure.ui.museum.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.text_adventure.daniyar.textadventure.data.entity.MuseumStoryModel;

import io.realm.RealmResults;

public abstract class BaseViewHolder extends RecyclerView.ViewHolder {

    public BaseViewHolder(View itemView) {
        super(itemView);
    }

    protected abstract void onBindView(RealmResults<MuseumStoryModel> museumStoryModels, int position);
}
