package com.example.ebooks.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ebooks.BookDetailsScreen;
import com.example.ebooks.R;
import com.example.ebooks.model.Book;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.ViewHolder> {

    Book [] bookList;
    Context context;

    public BookAdapter(Book[] bookList, Context context) {
        this.bookList = bookList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.book_list_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Book bookDataList = bookList[position];
        holder.bookName.setText(bookDataList.getBookName());
        holder.bookAuth.setText(bookDataList.getAuthorName());
        holder.bookImage.setImageResource(bookDataList.getBookImage());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String bId = bookList[position].getBookId();
                String bTitle = bookList[position].getBookName();
                Bundle bundle = new Bundle();
                bundle.putString("BookId", bId);
                bundle.putString("BookTitle", bTitle);
                Intent intent = new Intent(context, BookDetailsScreen.class);
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return bookList.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView bookImage;
        TextView bookName;
        TextView bookAuth;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            bookImage = itemView.findViewById(R.id.bookImage);
            bookName = itemView.findViewById(R.id.bookName);
            bookAuth = itemView.findViewById(R.id.bookAuthor);
        }
    }
}
