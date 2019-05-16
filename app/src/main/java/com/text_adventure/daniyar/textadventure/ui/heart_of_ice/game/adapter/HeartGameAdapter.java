package com.text_adventure.daniyar.textadventure.ui.heart_of_ice.game.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.text_adventure.daniyar.textadventure.R;
import com.text_adventure.daniyar.textadventure.data.entity.HeartStoryModel;
import com.text_adventure.daniyar.textadventure.data.manager.ResourceManager;
import com.text_adventure.daniyar.textadventure.ui.heart_of_ice.game.adapter.viewholder.ButtonTypeViewHolder;
import com.text_adventure.daniyar.textadventure.ui.heart_of_ice.game.adapter.viewholder.GetHealthTypeViewHolder;
import com.text_adventure.daniyar.textadventure.ui.heart_of_ice.game.adapter.viewholder.KeyTypeViewHolder;
import com.text_adventure.daniyar.textadventure.ui.heart_of_ice.game.adapter.viewholder.LostHealthTypeViewHolder;
import com.text_adventure.daniyar.textadventure.ui.heart_of_ice.game.adapter.viewholder.SellTypeViewHolder;
import com.text_adventure.daniyar.textadventure.ui.heart_of_ice.game.adapter.viewholder.ShopTypeViewHolder;
import com.text_adventure.daniyar.textadventure.ui.heart_of_ice.game.adapter.viewholder.TextGetTypeViewHolder;
import com.text_adventure.daniyar.textadventure.ui.heart_of_ice.game.adapter.viewholder.TextLostTypeViewHolder;
import com.text_adventure.daniyar.textadventure.ui.heart_of_ice.game.adapter.viewholder.TextTypeViewHolder;

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

        LayoutInflater inflater = LayoutInflater.from(mContext = parent.getContext());
        View view;
        switch (viewType){
            case 0:
                view = inflater.inflate(R.layout.item_heart_game_text, parent, false);
                return new TextTypeViewHolder(view);
            case 1:
                view = inflater.inflate(R.layout.item_heart_game_button, parent, false);
                return new ButtonTypeViewHolder(view, mChoiceClick, new ResourceManager(mContext));
            case 2:
                view = inflater.inflate(R.layout.item_heart_game_key, parent, false);
                return new KeyTypeViewHolder(view);
            case 3:
                view = inflater.inflate(R.layout.item_heart_game_lost_hp, parent, false);
                return new LostHealthTypeViewHolder(view);
            case 4:
                view = inflater.inflate(R.layout.item_heart_game_dead, parent, false);
                return new ButtonTypeViewHolder(view, mChoiceClick, new ResourceManager(mContext));
            case 5:
                view = inflater.inflate(R.layout.item_heart_game_text, parent, false);
                return new TextTypeViewHolder(view);
            case 6:
                view = inflater.inflate(R.layout.item_heart_game_text_lost, parent, false);
                return new TextLostTypeViewHolder(view);
            case 7:
                view = inflater.inflate(R.layout.item_heart_game_key, parent, false);
                return new KeyTypeViewHolder(view);
            case 8:
                view = inflater.inflate(R.layout.item_heart_game_button, parent, false);
                return new ButtonTypeViewHolder(view, mChoiceClick, new ResourceManager(mContext));
            case 9:
                view = inflater.inflate(R.layout.item_heart_game_get_hp, parent, false);
                return new GetHealthTypeViewHolder(view);
            case 10:
                view = inflater.inflate(R.layout.item_heart_game_text_lost, parent, false);
                return new TextLostTypeViewHolder(view);
            case 11:
                view = inflater.inflate(R.layout.item_heart_game_text_get, parent, false);
                return new TextGetTypeViewHolder(view);
            case 12:
                view = inflater.inflate(R.layout.item_heart_game_shop, parent, false);
                return new ShopTypeViewHolder(view);
            case 13:
                view = inflater.inflate(R.layout.item_heart_game_sell, parent, false);
                return new SellTypeViewHolder(view);
            default:
                view = inflater.inflate(R.layout.item_heart_game_text, parent, false);
                return new TextTypeViewHolder(view);
        }
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
