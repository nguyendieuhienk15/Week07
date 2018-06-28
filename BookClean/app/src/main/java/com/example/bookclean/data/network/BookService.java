package com.example.bookclean.data.network;

import com.example.bookclean.data.entity.BookEntity;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface BookService {
    @GET("bins/17tfku")
    Observable<List<BookEntity>> getBooks();
}
