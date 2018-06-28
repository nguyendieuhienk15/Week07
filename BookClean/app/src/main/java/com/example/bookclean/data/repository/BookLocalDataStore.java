package com.example.bookclean.data.repository;

import com.example.bookclean.data.entity.BookEntity;

import java.util.List;

import io.reactivex.Observable;

public class BookLocalDataStore implements BookDataStore {
    private final BookCache bookCache;
    BookLocalDataStore(BookCache bookCache){
        this.bookCache = bookCache;
    }

    //Return a list of books from DB
    @Override
    public Observable<List<BookEntity>> books() {
        return bookCache.get();
    }
}
