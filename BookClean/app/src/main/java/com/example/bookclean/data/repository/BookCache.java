package com.example.bookclean.data.repository;

import com.example.bookclean.data.entity.BookEntity;

import java.util.List;

import io.reactivex.Observable;

public interface BookCache {
    boolean isExpired();
    boolean isCached();
    Observable<List<BookEntity>> get();
    void put(List<BookEntity> bookEntities);
}
