package com.example.ebooks;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class LikedItemsScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liked_items_screen);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Beğenilen Kitaplar");
        actionBar.setDisplayHomeAsUpEnabled(true);
    }
}