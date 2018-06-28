package com.example.bookclean.presentation.view.activity;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.bookclean.R;
import com.example.bookclean.data.entity.BookMapper;
import com.example.bookclean.data.repository.BookCache;
import com.example.bookclean.data.repository.BookCacheImpl;
import com.example.bookclean.data.repository.BookDataRepository;
import com.example.bookclean.data.repository.BookDataStoreFactory;
import com.example.bookclean.domain.usecase.GetBookList;
import com.example.bookclean.presentation.model.BookModel;
import com.example.bookclean.presentation.model.BookModelMapper;
import com.example.bookclean.presentation.presenter.BookListPresenter;
import com.example.bookclean.presentation.view.BookListView;
import com.example.bookclean.presentation.view.adapter.BookAdapter;

import java.util.List;

import butterknife.BindView;

public class BookListActivity extends BaseActivity implements BookListView {
    @BindView(R.id.recycler)
    RecyclerView recycler;
    @BindView(R.id.progress)
    ProgressBar progress;
    @BindView(R.id.activity_book_list)
    RelativeLayout activityBookList;
    @BindView(R.id.error)
    TextView error;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    private BookAdapter bookAdapter;
    private BookListPresenter bookListPresenter;
    private GetBookList getBookList;
    private BookModelMapper bookModelMapper;
    private BookDataRepository bookDataRepository;
    private BookMapper bookMapper;
    private BookDataStoreFactory bookDataStoreFactory;
    private BookCache bookCache;

    public BookListActivity(){

    }
    @Override
    public void renderBookList(List<BookModel> bookModels) {
        bookAdapter.setBooks(bookModels);
    }

    @Override
    public void showLoading() {
        recycler.setVisibility(View.GONE);
        progress.setVisibility(View.VISIBLE);
        error.setVisibility(View.GONE);
    }

    @Override
    public void hideLoading() {
        recycler.setVisibility(View.VISIBLE);
        progress.setVisibility(View.GONE);
        error.setVisibility(View.GONE);
    }

    @Override
    public void showError(String errorMessage) {
        recycler.setVisibility(View.GONE);
        progress.setVisibility(View.GONE);
        error.setVisibility(View.VISIBLE);
        error.setText(errorMessage);
    }


    @Override
    public Context context() {
        return this;
    }

    @Override
    public int getContentLayout() {
        return R.layout.activity_book_list;
    }

    @Override
    public void initComponents() {
        // use a linear layout manager since the cards are vertically scrollable
        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recycler.setLayoutManager(layoutManager);

        bookAdapter = new BookAdapter();
        recycler.setAdapter(bookAdapter);
        bookModelMapper = new BookModelMapper();
        bookMapper = new BookMapper();
        bookCache = new BookCacheImpl();
        bookDataStoreFactory = new BookDataStoreFactory(bookCache);
        bookDataRepository = new BookDataRepository(bookDataStoreFactory,bookMapper);
        getBookList = new GetBookList(bookDataRepository);
        bookListPresenter = new BookListPresenter(getBookList,bookModelMapper);
        bookListPresenter.setBookListView(this);
        bookListPresenter.initialize();
    }

    @Override
    protected void onResume() {
        super.onResume();
        bookListPresenter.resume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        bookListPresenter.pause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        bookListPresenter.destroy();
    }
}
