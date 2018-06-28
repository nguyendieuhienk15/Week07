package com.example.bookclean.data.repository;


import com.example.bookclean.data.entity.BookEntity;
import com.example.bookclean.data.network.ServiceGenerator;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;

public class BookCloudDataStore implements BookDataStore {
    private final BookCache bookCache;
    BookCloudDataStore(BookCache bookCache){
        this.bookCache = bookCache;
    }

    @Override
    public Observable<List<BookEntity>> books() {
        return ServiceGenerator.getBookService().getBooks().doOnNext(new Consumer<List<BookEntity>>() {
            @Override
            public void accept(final List<BookEntity> bookEntities) throws Exception {
                bookCache.put(bookEntities);
            }
        });
    }
}
