package com.example.bookclean.domain.usecase;

import com.example.bookclean.domain.model.Book;
import com.example.bookclean.domain.repository.BookRepository;

import java.util.List;

import io.reactivex.Observable;

public class GetBookList extends UseCase<List<Book>,Void> {
    private final BookRepository bookRepository;

    public GetBookList(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    Observable<List<Book>> buildUseCaseObservable(Void unused) {
        return bookRepository.books();
    }
}