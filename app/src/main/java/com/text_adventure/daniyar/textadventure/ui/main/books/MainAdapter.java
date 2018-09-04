package com.text_adventure.daniyar.textadventure.ui.main.books;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.text_adventure.daniyar.textadventure.R;
import com.text_adventure.daniyar.textadventure.data.entity.BookModel;

import java.util.ArrayList;

public class MainAdapter extends BaseAdapter {

    private Context mContext;
    private ArrayList<BookModel> mBookModels;

    public MainAdapter(Context context, ArrayList<BookModel> bookModels){
        mContext = context;
        mBookModels = bookModels;
    }

    @Override
    public int getCount() {
        return mBookModels.size();
    }

    @Override
    public Object getItem(int i) {
        return mBookModels.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        ViewHolder viewHolder;
        if(view == null){
            view = LayoutInflater.from(mContext).inflate(R.layout.item_main_list, viewGroup, false);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        }
        else{
            viewHolder = (ViewHolder) view.getTag();
        }

        BookModel bookModel = (BookModel) getItem(i);
        viewHolder.mImageBook.setImageResource(bookModel.getImageId());
        viewHolder.mTextViewBookName.setText(bookModel.getName());

        return view;
    }

    class ViewHolder{

        private TextView mTextViewBookName;
        private ImageView mImageBook;

        ViewHolder(View view){
            mTextViewBookName = view.findViewById(R.id.textViewBookName);
            mImageBook = view.findViewById(R.id.imageBook);
        }
    }
}
