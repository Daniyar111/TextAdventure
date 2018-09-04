package com.text_adventure.daniyar.textadventure.ui.main.books;

import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.text_adventure.daniyar.textadventure.R;
import com.text_adventure.daniyar.textadventure.data.manager.ResourceManager;
import com.text_adventure.daniyar.textadventure.ui.BaseActivity;
import com.text_adventure.daniyar.textadventure.ui.main.start_game.MainStartGameDialogFragment;

public class MainActivity extends BaseActivity implements MainContract.View, AdapterView.OnItemClickListener {

    private MainPresenter mPresenter;
    private MainAdapter mAdapter;
    private ListView mListViewBooks;
    private MainStartGameDialogFragment mDialogFragment;

    @Override
    protected int getViewLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getViewLayout());

        mPresenter = new MainPresenter(new ResourceManager(this));
        mPresenter.bind(this);

        initializeViews();

    }

    private void initializeViews(){
        mListViewBooks = findViewById(R.id.listViewBooks);
        mAdapter = new MainAdapter(this, mPresenter.getBooks());
        mListViewBooks.setAdapter(mAdapter);
        mListViewBooks.setLayoutAnimation(AnimationUtils.loadLayoutAnimation(mListViewBooks.getContext(), R.anim.layout_slide_up));
        mListViewBooks.scheduleLayoutAnimation();
        mListViewBooks.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

        switch (i){
            case 0:
                mPresenter.onListViewItemClicked(adapterView, i);
                break;
            default:
                Toast.makeText(this, getString(R.string.release), Toast.LENGTH_LONG).show();
                break;
        }
    }

    @Override
    public void showDetails(Bundle bundle) {

        mDialogFragment = new MainStartGameDialogFragment();
        mDialogFragment.setArguments(bundle);
        mDialogFragment.show(getSupportFragmentManager(), "book");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.unbind();
    }

}
