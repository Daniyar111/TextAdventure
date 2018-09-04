package com.text_adventure.daniyar.textadventure.ui.main.books;

import android.os.Bundle;
import android.widget.AdapterView;

import com.text_adventure.daniyar.textadventure.R;
import com.text_adventure.daniyar.textadventure.data.entity.BookModel;
import com.text_adventure.daniyar.textadventure.data.manager.ResourceManager;

import java.util.ArrayList;

public class MainPresenter implements MainContract.Presenter {

    private MainContract.View mView;
    private ArrayList<BookModel> mBookModels;
    private ResourceManager mResourceManager;

    public MainPresenter(ResourceManager resourceManager){
        mResourceManager = resourceManager;
        mBookModels = new ArrayList<>();
    }

    @Override
    public ArrayList<BookModel> getBooks() {

        for (int i = 0; i < imagesArray().length; i++) {
            BookModel bookModel = new BookModel();
            bookModel.setImageId(imagesArray()[i]);
            bookModel.setName(mResourceManager.getResources().getStringArray(R.array.array_books)[i]);
            mBookModels.add(bookModel);
        }

        return mBookModels;
    }

    @Override
    public void onListViewItemClicked(AdapterView<?> adapterView, int i) {

        BookModel bookModel = (BookModel) adapterView.getItemAtPosition(i);
        Bundle bundle = new Bundle();
        bundle.putString("book", bookModel.getName());
        if(isViewAttached()){
            mView.showDetails(bundle);
        }
    }

    private int[] imagesArray(){

        return new int[]{R.drawable.museum, R.drawable.heart_of_ice, R.drawable.psychotest};
    }

    @Override
    public void bind(MainContract.View view) {
        mView = view;
    }

    @Override
    public void unbind() {
        mView = null;
    }

    private boolean isViewAttached(){
        return mView != null;
    }
}
