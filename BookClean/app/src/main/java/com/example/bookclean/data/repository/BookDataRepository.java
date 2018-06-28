package com.example.bookclean.data.repository;

import com.example.bookclean.data.entity.BookEntity;
import com.example.bookclean.data.entity.BookMapper;
import com.example.bookclean.domain.model.Book;
import com.example.bookclean.domain.repository.BookRepository;


import java.util.List;

import io.reactivex.Observable;
import io.reactivex.functions.Function;

public class BookDataRepository implements BookRepository {
    private final BookDataStoreFactory bookDataStoreFactory;
    private final BookMapper bookMapper;
    public BookDataRepository(BookDataStoreFactory bookDataStoreFactory, BookMapper bookMapper){
        this.bookDataStoreFactory = bookDataStoreFactory;
        this.bookMapper = bookMapper;
    }
    @Override
    public Observable<List<Book>> books() {
        return bookDataStoreFactory.create().books().map(new Function<List<BookEntity>, List<Book>>() {
            @Override
            public List<Book> apply(List<BookEntity> bookEntities) throws Exception {
                return bookMapper.transformList(bookEntities);
            }
        });
    }
}