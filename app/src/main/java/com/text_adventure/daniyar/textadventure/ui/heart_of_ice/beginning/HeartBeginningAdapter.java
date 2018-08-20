package com.text_adventure.daniyar.textadventure.ui.heart_of_ice.beginning;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.text_adventure.daniyar.textadventure.R;

import java.util.ArrayList;

public class HeartBeginningAdapter extends BaseAdapter {

    private Context mContext;
    private ArrayList<String> mRulesList;

    public HeartBeginningAdapter(Context context, ArrayList<String> rulesList){
        mContext = context;
        mRulesList = rulesList;
    }

    @Override
    public int getCount() {
        return mRulesList.size();
    }

    @Override
    public Object getItem(int i) {
        return mRulesList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        ViewHolder viewHolder;
        if(view == null){
            view = LayoutInflater.from(mContext).inflate(R.layout.item_heart_rules, viewGroup, false);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        }
        else{
            viewHolder = (ViewHolder) view.getTag();
        }

        String rule = (String) getItem(i);
        viewHolder.mTextViewRule.setText(rule);

        return view;
    }

    class ViewHolder{

        private TextView mTextViewRule;

        ViewHolder(View view){
            mTextViewRule = view.findViewById(R.id.textViewRule);
        }
    }
}
