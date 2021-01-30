package com.example.ebooks;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.example.ebooks.adapter.LikedListAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class LikedItemsScreen extends AppCompatActivity {

    String email;
    ArrayList<String> likedBookList;
    ArrayList<String> likedBookImage;

    private FirebaseAuth firebaseAuth;
    private FirebaseFirestore firebaseFirestore;

    LikedListAdapter likedListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liked_items_screen);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Beğenilen Kitaplar");
        actionBar.setDisplayHomeAsUpEnabled(true);

        likedBookList = new ArrayList<>();
        likedBookImage = new ArrayList<>();

        firebaseFirestore = FirebaseFirestore.getInstance();
        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
        email = firebaseUser.getEmail();

        getDataFromSnapshot();

        RecyclerView recyclerView = findViewById(R.id.recyclerView2);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        likedListAdapter = new LikedListAdapter(likedBookList,likedBookImage);
        recyclerView.setAdapter(likedListAdapter);
    }

    public void getDataFromSnapshot(){
       CollectionReference collectionReference = firebaseFirestore.collection("Liked");
       collectionReference.addSnapshotListener((value, error) -> {
           if (value != null){
             for (DocumentSnapshot snapshot : value.getDocuments()){
                 Map<String, Object> likedlist = snapshot.getData();

                 String bookN = (String) likedlist.get("bookname");
                 String img = (String) likedlist.get("bookname");
                 likedBookList.add(bookN);
                 System.out.println(bookN);

                 likedListAdapter.notifyDataSetChanged();
             }
           }
       });
    }
}