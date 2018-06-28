package com.example.bookclean.presentation.view;

import com.example.bookclean.presentation.model.BookModel;
import com.example.bookclean.presentation.view.LoadDataView;

import java.util.List;

public interface BookListView extends LoadDataView {
    void renderBookList(List<BookModel> bookModels);
}