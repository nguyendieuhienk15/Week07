package com.example.bookclean.data.repository;

import com.example.bookclean.data.entity.BookEntity;

import java.util.List;

import io.reactivex.Observable;

public interface BookDataStore {
    Observable<List<BookEntity>> books();
}
