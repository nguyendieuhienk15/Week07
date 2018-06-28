package com.example.bookclean.presentation.presenter;

import com.example.bookclean.domain.model.Book;
import com.example.bookclean.domain.usecase.GetBookList;
import com.example.bookclean.presentation.model.BookModel;
import com.example.bookclean.presentation.model.BookModelMapper;
import com.example.bookclean.presentation.view.BookListView;

import java.util.List;

public class BookListPresenter implements Presenter {
    private BookListView bookListView;
    private final GetBookList getBookList;
    private final BookModelMapper bookModelMapper;

    public BookListPresenter(GetBookList getBookList, BookModelMapper bookModelMapper){
        this.getBookList = getBookList;
        this.bookModelMapper = bookModelMapper;
    }

    public void setBookListView(BookListView bookListView){
        this.bookListView = bookListView;
    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void destroy() {
        this.getBookList.dispose();
        this.bookListView = null;
    }


    public void initialize(){
        this.loadBookList();
    }

    private void loadBookList() {
        this.showViewLoading();
        this.getBookList();
    }

    private void showViewLoading() {
        this.bookListView.showLoading();
    }

    private void hideViewLoading() {
        this.bookListView.hideLoading();
    }

    private void showErrorMessage(String errorMessage) {
        this.bookListView.showError(errorMessage);
    }

    private void showBookListInView(List<Book> books) {
        final List<BookModel> bookModelList = this.bookModelMapper.transformList(books);
        this.bookListView.renderBookList(bookModelList);
    }

    private void getBookList() {
        this.getBookList.execute(new BookListPresenter.BooksObserver(), null);
    }

    private class BooksObserver extends io.reactivex.observers.DisposableObserver<List<Book>> {
        @Override
        public void onNext(List<Book> books) {
            BookListPresenter.this.showBookListInView(books);
        }

        @Override
        public void onError(Throwable e) {
            e.printStackTrace();
            BookListPresenter.this.hideViewLoading();
            BookListPresenter.this.showErrorMessage(e.getLocalizedMessage());
        }

        @Override
        public void onComplete() {
            BookListPresenter.this.hideViewLoading();
        }
    }
}
