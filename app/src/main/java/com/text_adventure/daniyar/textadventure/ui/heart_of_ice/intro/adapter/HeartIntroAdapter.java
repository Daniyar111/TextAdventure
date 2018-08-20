package com.text_adventure.daniyar.textadventure.ui.heart_of_ice.intro.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.text_adventure.daniyar.textadventure.R;
import com.text_adventure.daniyar.textadventure.data.entity.HeartIntroModel;
import com.text_adventure.daniyar.textadventure.ui.heart_of_ice.intro.adapter.viewholder.BodyTypeViewHolder;
import com.text_adventure.daniyar.textadventure.ui.heart_of_ice.intro.adapter.viewholder.ButtonTypeViewHolder;
import com.text_adventure.daniyar.textadventure.ui.heart_of_ice.intro.adapter.viewholder.HeaderTypeViewHolder;

import io.realm.RealmResults;

public class HeartIntroAdapter extends RecyclerView.Adapter<BaseViewHolder>{

    private Context mContext;
    private RealmResults<HeartIntroModel> mIntroModels;
    private OnHeartIntroChoiceClick mChoiceClick;

    public HeartIntroAdapter(Context context, RealmResults<HeartIntroModel> introModels, OnHeartIntroChoiceClick choiceClick){
        mContext = context;
        mIntroModels = introModels;
        mChoiceClick = choiceClick;
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(mContext = parent.getContext());
        View view;

        switch (viewType){
            case 0:
                view = inflater.inflate(R.layout.item_heart_intro_header, parent, false);
                return new HeaderTypeViewHolder(view);
            case 1:
                view = inflater.inflate(R.layout.item_heart_intro_body, parent, false);
                return new BodyTypeViewHolder(view);
            case 2:
                view = inflater.inflate(R.layout.item_heart_intro_button, parent, false);
                return new ButtonTypeViewHolder(view, mChoiceClick);
            default:
                view = inflater.inflate(R.layout.item_heart_intro_button, parent, false);
                return new ButtonTypeViewHolder(view, mChoiceClick);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        holder.onBindView(mIntroModels.get(position));
    }

    @Override
    public int getItemViewType(int position) {
        return mIntroModels.get(position).getType();
    }

    @Override
    public int getItemCount() {
        return mIntroModels.size();
    }
}
