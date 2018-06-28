package com.example.bookclean.presentation.view.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bookclean.R;
import com.example.bookclean.presentation.model.BookModel;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.BookViewHolder>{
    List<BookModel> bookModels;

    @NonNull
    @Override
    public BookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_book, parent, false);
        return new BookViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BookViewHolder holder, int position) {
        holder.bind(bookModels.get(position));
    }

    @Override
    public int getItemCount() {
        return (bookModels != null)? bookModels.size() : 0;
    }

    public void setBooks(List<BookModel> books){
        if(books == null){
            throw  new IllegalArgumentException("List can't be null");
        }
        bookModels = books;
        notifyDataSetChanged();
    }

    public class BookViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.card_books)
        CardView bookCard;
        @BindView(R.id.text_books_title)
        TextView title;
        @BindView(R.id.text_books_description)
        TextView description;
        @BindView(R.id.text_books_author)
        TextView author;
        @BindView(R.id.image_background)
        ImageView image;


        public BookViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }

        void bind(BookModel book){
            Picasso.with(image.getContext())
                    .load(book.getImageUrl())
                    .into(image);
            title.setText(book.getTitle());
            description.setText(book.getDescription());
            author.setText(book.getAuthor());
        }
    }
}
