package com.example.bookclean.data.network;

public class ServiceGenerator {
    private static BookService bookService;
    public static BookService getBookService(){
        if(bookService == null){
            bookService = RetrofitHelper.getRetrofit().create(BookService.class);
        }
        return bookService;
    }
}
