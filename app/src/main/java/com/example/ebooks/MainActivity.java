package com.example.ebooks;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ebooks.adapter.BookAdapter;
import com.example.ebooks.model.Book;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Book[] bookData = new Book[]{
                new Book("greatGatsby","Great Gatsby", "F. Scott Fitzgerald",R.drawable.greatgatsby),
                new Book("sherlock","The Return of Sherlock Holmes", "Arthur Conan Doyle",R.drawable.sherlock),
                new Book("janeeyre","Jane Eyre", "Charlotte Brontë",R.drawable.janeeyre),
                new Book("artofwar","The Art of War", "Sun Tzu",R.drawable.artofwar),
        };
        BookAdapter bookAdapter = new BookAdapter(bookData, this);
        recyclerView.setAdapter(bookAdapter);
    }
}