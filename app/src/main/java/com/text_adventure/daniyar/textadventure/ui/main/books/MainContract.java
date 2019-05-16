package com.text_adventure.daniyar.textadventure.ui.main.books;

import android.os.Bundle;
import android.widget.AdapterView;

import com.text_adventure.daniyar.textadventure.data.entity.BookModel;
import com.text_adventure.daniyar.textadventure.ui.LifeCycle;

import java.util.ArrayList;

public interface MainContract {

    interface View{

        void showDetails(Bundle bundle);
    }

    interface Presenter extends LifeCycle<View>{

        ArrayList<BookModel> getBooks();

        void onListViewItemClicked(AdapterView<?> adapterView, int i);
    }
}
