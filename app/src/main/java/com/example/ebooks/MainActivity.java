package com.example.ebooks;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ebooks.adapter.BookAdapter;
import com.example.ebooks.dbScreens.LoginScreen;
import com.example.ebooks.model.Book;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    ImageView fav;
    private FirebaseAuth firebaseAuth;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.options_menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.fav){
            Intent intentFav = new Intent(MainActivity.this, LikedItemsScreen.class);
            startActivity(intentFav);
        }else if (item.getItemId() == R.id.exit){
            firebaseAuth.signOut();
            Intent intentExit = new Intent(MainActivity.this, LoginScreen.class);
            startActivity(intentExit);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        fav = findViewById(R.id.imageView2);

        firebaseAuth = FirebaseAuth.getInstance();

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