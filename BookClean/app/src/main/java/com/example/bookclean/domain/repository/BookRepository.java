package com.example.bookclean.domain.repository;

import com.example.bookclean.domain.model.Book;

import java.util.List;

import io.reactivex.Observable;

public interface BookRepository {
    Observable<List<Book>> books();
}
