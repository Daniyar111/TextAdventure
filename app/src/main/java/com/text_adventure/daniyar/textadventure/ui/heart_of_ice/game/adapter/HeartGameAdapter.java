package com.text_adventure.daniyar.textadventure.ui.heart_of_ice.game.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.text_adventure.daniyar.textadventure.data.entity.HeartStoryModel;

import io.realm.RealmResults;

public class HeartGameAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    private Context mContext;
    private RealmResults<HeartStoryModel> mHeartStoryModels;
    private OnHeartGameChoiceClick mChoiceClick;

    public HeartGameAdapter(Context context, RealmResults<HeartStoryModel> heartStoryModels, OnHeartGameChoiceClick choiceClick){
        mContext = context;
        mHeartStoryModels = heartStoryModels;
        mChoiceClick = choiceClick;
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        holder.onBindView(mHeartStoryModels, position);
    }

    @Override
    public int getItemViewType(int position) {
        return mHeartStoryModels.get(position).getType();
    }

    @Override
    public int getItemCount() {
        return mHeartStoryModels.size();
    }
}
