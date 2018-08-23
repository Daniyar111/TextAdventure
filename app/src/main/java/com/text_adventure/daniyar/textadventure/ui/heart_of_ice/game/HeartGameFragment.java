package com.text_adventure.daniyar.textadventure.ui.heart_of_ice.game;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.text_adventure.daniyar.textadventure.R;
import com.text_adventure.daniyar.textadventure.ui.BaseFragment;

public class HeartGameFragment extends BaseFragment implements HeartGameContract.View{

    private HeartGamePresenter mPresenter;
    private Context mContext;
    private RecyclerView mRecyclerViewHeart;

    @Override
    protected int getViewLayout() {
        return R.layout.fragment_heart_game;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if(getActivity() != null){
            mContext = getActivity().getApplicationContext();
        }


    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.closeRealm();
        mPresenter.unbind();
    }
}
