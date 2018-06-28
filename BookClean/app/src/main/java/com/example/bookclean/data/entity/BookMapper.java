package com.example.bookclean.data.entity;

import com.example.bookclean.domain.model.Book;

import java.util.ArrayList;
import java.util.List;

public class BookMapper {

    public BookMapper(){}

    public Book transform(BookEntity bookEntity){
        Book book = null;
        if(bookEntity != null){
            book = new Book(bookEntity.getId(), bookEntity.getTitle(), bookEntity.getDescription(), bookEntity.getAuthor(), bookEntity.getImageUrl());
        }
        return book;
    }

    public List<Book> transformList(List<BookEntity> bookEntities){
        List<Book> books = new ArrayList<>();
        for(BookEntity bookEntity:bookEntities){
            if(bookEntity != null){
                books.add(transform(bookEntity));
            }
        }
        return books;
    }
}
