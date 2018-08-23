package com.text_adventure.daniyar.textadventure.ui.heart_of_ice.personage.person_list;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.text_adventure.daniyar.textadventure.R;
import com.text_adventure.daniyar.textadventure.data.manager.ResourceManager;
import com.text_adventure.daniyar.textadventure.ui.BaseFragment;
import com.text_adventure.daniyar.textadventure.ui.heart_of_ice.personage.person_details.HeartPersonDetailsDialogFragment;

import io.realm.Realm;

public class HeartPersonListFragment extends BaseFragment implements HeartPersonListContract.View, AdapterView.OnItemClickListener {

    private HeartPersonListPresenter mPresenter;
    private Context mContext;
    private ListView mListViewPerson;
    private HeartPersonListAdapter mAdapter;
    private Button mButtonCreate;
    private HeartPersonDetailsDialogFragment mDetailsDialogFragment;

    @Override
    protected int getViewLayout() {
        return R.layout.fragment_person_list;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if(getActivity() != null){
            mContext = getActivity().getApplicationContext();
        }

        mPresenter = new HeartPersonListPresenter(Realm.getDefaultInstance(), new ResourceManager(mContext));
        mPresenter.bind(this);
        initializeViews(view);
        mPresenter.readDataCSV();
        mPresenter.writeToRealm();
    }

    private void initializeViews(View view){
        mListViewPerson = view.findViewById(R.id.listViewPerson);
        mButtonCreate = view.findViewById(R.id.buttonCreate);
        mPresenter.refreshList();
    }

    @Override
    public void refreshAdapter() {

        mAdapter = new HeartPersonListAdapter(mContext, mPresenter.getHeartPersonModels());
        mListViewPerson.setAdapter(mAdapter);
        mListViewPerson.setLayoutAnimation(AnimationUtils.loadLayoutAnimation(mListViewPerson.getContext(), R.anim.layout_slide_up));
        mListViewPerson.scheduleLayoutAnimation();
        mListViewPerson.setOnItemClickListener(this);
    }

    @Override
    public void showDetails(Bundle bundle) {

        mDetailsDialogFragment = new HeartPersonDetailsDialogFragment();
        if(getActivity() != null){
            mDetailsDialogFragment.setArguments(bundle);
            mDetailsDialogFragment.show(getActivity().getSupportFragmentManager(), "person");
        }
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

        mPresenter.onListViewItemClicked(adapterView, i);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.closeRealm();
        mPresenter.unbind();
    }
}
