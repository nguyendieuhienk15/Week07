package com.example.bookclean.data.repository;


public class BookDataStoreFactory {
    private final BookCache bookCache;
    public BookDataStoreFactory(BookCache bookCache){
        this.bookCache = bookCache;
    }

    public BookDataStore create(){
        if(!bookCache.isExpired() && bookCache.isCached()){
            return new BookLocalDataStore(bookCache);
        }else{
            return new BookCloudDataStore(bookCache);
        }
    }
}
