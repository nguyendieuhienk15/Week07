package com.example.bookclean.data.repository;

import com.example.bookclean.data.entity.BookEntity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import io.reactivex.Observable;
import io.realm.Realm;

public class BookCacheImpl implements BookCache {
    private static final long EXPIRATION_TIME = 60 * 10 * 1000;

    @Override
    public boolean isExpired() {
        Realm realm = Realm.getDefaultInstance();
        if (realm.where(BookEntity.class).count() != 0) {
            Date currentTime = new Date(System.currentTimeMillis());
            SimpleDateFormat ISO8601DATEFORMAT = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ", Locale.ENGLISH);
            Date lastUpdated = null;
            try {
                lastUpdated = ISO8601DATEFORMAT.parse(/*realm.where(BookEntity.class).findFirst().getLastUpdated()*/"2018-06-10T19:34:20+0000");
                boolean isExpired = currentTime.getTime() - lastUpdated.getTime() > EXPIRATION_TIME;
                if(isExpired){
                    realm.beginTransaction();
                    realm.delete(BookEntity.class);
                    realm.commitTransaction();
                }
                return isExpired;
            } catch (ParseException e) {
                e.printStackTrace();
            } finally {
                realm.close();
            }

        }
        return false;
    }

    @Override
    public boolean isCached() {
        List<BookEntity> bookEntities = getBookEntityList();
        return bookEntities != null && bookEntities.size() > 0;
    }

    @Override
    public Observable<List<BookEntity>> get() {
        List<BookEntity> bookEntities = getBookEntityList();
        return Observable.just(bookEntities);
    }

    @Override
    public void put(List<BookEntity> bookEntities) {
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        realm.copyToRealmOrUpdate(bookEntities);
        realm.commitTransaction();
        realm.close();
    }

    private List<BookEntity> getBookEntityList() {
        Realm realm = Realm.getDefaultInstance();
        List<BookEntity> bookEntities = realm.copyFromRealm(
                realm.where(BookEntity.class).findAll());
        realm.close();
        return bookEntities;
    }
}