package com.example.ebooks;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ebooks.adapter.BookAdapter;
import com.example.ebooks.dbScreens.LoginScreen;
import com.example.ebooks.model.Book;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.like.LikeButton;
import com.like.OnLikeListener;

public class MainActivity extends AppCompatActivity{

    private FirebaseAuth firebaseAuth;
    private FirebaseStorage firebaseStorage;
    private StorageReference storageReference;
    private FirebaseFirestore firebaseFirestore;

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

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseStorage = FirebaseStorage.getInstance();
        storageReference = firebaseStorage.getReference();
        firebaseFirestore = FirebaseFirestore.getInstance();



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