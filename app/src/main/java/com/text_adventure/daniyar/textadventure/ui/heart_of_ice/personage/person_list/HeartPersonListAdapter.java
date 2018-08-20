package com.text_adventure.daniyar.textadventure.ui.heart_of_ice.personage.person_list;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.text_adventure.daniyar.textadventure.R;
import com.text_adventure.daniyar.textadventure.data.entity.HeartPersonModel;

import java.util.ArrayList;

import io.realm.RealmResults;

public class HeartPersonListAdapter extends BaseAdapter {

    private Context mContext;
    private RealmResults<HeartPersonModel> mHeartPersonModels;

    public HeartPersonListAdapter(Context context, RealmResults<HeartPersonModel> heartPersonModels){
        mContext = context;
        mHeartPersonModels = heartPersonModels;
    }

    @Override
    public int getCount() {
        return mHeartPersonModels.size();
    }

    @Override
    public Object getItem(int i) {
        return mHeartPersonModels.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        ViewHolder viewHolder;
        if(view == null){
            view = LayoutInflater.from(mContext).inflate(R.layout.item_heart_person, viewGroup, false);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        }
        else{
            viewHolder = (ViewHolder) view.getTag();
        }

        HeartPersonModel personModel = (HeartPersonModel) getItem(i);
        viewHolder.mTextViewName.setText(personModel.getName());
        viewHolder.mImageViewPerson.setImageResource(personModel.getImageId());
        viewHolder.mTextViewDescription.setText(personModel.getDescription());
        StringBuilder skills = new StringBuilder();
        StringBuilder equipments = new StringBuilder();
        for (int j = 0; j < personModel.getSkills().size(); j++) {
            for (int k = 0; k < personModel.getSkills().get(j).getEquipments().size(); k++) {
                equipments.append(personModel.getSkills().get(j).getEquipments().get(k).getName());
            }
            skills.append(personModel.getSkills().get(j).getName());
        }
        viewHolder.mTextViewSkills.setText(skills);

        viewHolder.mTextViewEquipments.setText(equipments);
        viewHolder.mTextViewMoney.setText(String.valueOf(personModel.getMoney()));
        viewHolder.mTextViewHealth.setText(String.valueOf(personModel.getHealth()));
        viewHolder.mTextViewStatus.setText(String.valueOf(personModel.getStatus()));

        return view;
    }

    class ViewHolder{

        private TextView mTextViewName, mTextViewDescription, mTextViewSkills, mTextViewEquipments, mTextViewMoney, mTextViewHealth, mTextViewStatus;
        private ImageView mImageViewPerson;

        ViewHolder(View view){
            mTextViewName = view.findViewById(R.id.textViewName);
            mImageViewPerson = view.findViewById(R.id.imagePerson);
            mTextViewDescription = view.findViewById(R.id.textViewDescription);
            mTextViewSkills = view.findViewById(R.id.textViewSkills);
            mTextViewEquipments = view.findViewById(R.id.textViewEquipments);
            mTextViewMoney = view.findViewById(R.id.textViewMoney);
            mTextViewHealth = view.findViewById(R.id.textViewHealth);
            mTextViewStatus = view.findViewById(R.id.textViewStatus);
        }
    }
}
