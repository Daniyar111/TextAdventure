package com.text_adventure.daniyar.textadventure.ui.museum.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.text_adventure.daniyar.textadventure.R;
import com.text_adventure.daniyar.textadventure.data.entity.MuseumStoryModel;
import com.text_adventure.daniyar.textadventure.data.manager.ResourceManager;
import com.text_adventure.daniyar.textadventure.ui.museum.adapter.viewholder.ChoiceTypeViewHolder;
import com.text_adventure.daniyar.textadventure.ui.museum.adapter.viewholder.TextAuthorTypeViewHolder;
import com.text_adventure.daniyar.textadventure.ui.museum.adapter.viewholder.TextPersonTypeViewHolder;
import com.text_adventure.daniyar.textadventure.ui.museum.adapter.viewholder.TextUserTypeViewHolder;

import io.realm.RealmResults;

public class MuseumAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    private Context mContext;
    private RealmResults<MuseumStoryModel> mMuseumStoryModels;
    private OnMuseumChoiceClick mChoiceClick;

    public MuseumAdapter(Context context, RealmResults<MuseumStoryModel> museumStoryModels, OnMuseumChoiceClick choiceClick){
        mContext = context;
        mMuseumStoryModels = museumStoryModels;
        mChoiceClick = choiceClick;
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(mContext = parent.getContext());
        View view;
        switch (viewType){
            case 0:
                view = inflater.inflate(R.layout.item_museum_person, parent, false);
                return new TextPersonTypeViewHolder(view);
            case 1:
                view = inflater.inflate(R.layout.item_museum_author, parent, false);
                return new TextAuthorTypeViewHolder(view);
            case 2:
                view = inflater.inflate(R.layout.item_museum_user, parent, false);
                return new TextUserTypeViewHolder(view);
            case 3:
                view = inflater.inflate(R.layout.item_museum_choice, parent, false);
                return new ChoiceTypeViewHolder(view, mChoiceClick, new ResourceManager(mContext));
            default:
                view = inflater.inflate(R.layout.item_museum_choice, parent, false);
                return new ChoiceTypeViewHolder(view, mChoiceClick, new ResourceManager(mContext));
        }
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        holder.onBindView(mMuseumStoryModels, position);
    }

    @Override
    public int getItemViewType(int position) {
        return mMuseumStoryModels.get(position).getType();
    }

    @Override
    public int getItemCount() {
        return mMuseumStoryModels.size();
    }
}
