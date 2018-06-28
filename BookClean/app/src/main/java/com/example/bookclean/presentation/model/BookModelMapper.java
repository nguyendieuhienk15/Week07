package com.example.bookclean.presentation.model;

import com.example.bookclean.domain.model.Book;

import java.util.ArrayList;
import java.util.List;

public class BookModelMapper {
    public BookModelMapper() {
    }

    public BookModel transform(Book book) {
        BookModel bookModel = null;
        if (book != null) {
            bookModel = new BookModel(book.getId(), book.getTitle(), book.getDescription(), book.getAuthor(), book.getImageUrl());
        }
        return bookModel;
    }

    public List<BookModel> transformList(List<Book> books) {
        List<BookModel> bookModels = new ArrayList<>();
        for (Book book : books) {
            if (book != null) {
                bookModels.add(transform(book));
            }
        }
        return bookModels;
    }
}

