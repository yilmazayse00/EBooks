package com.example.ebooks;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.github.barteksc.pdfviewer.PDFView;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.HashMap;


public class BookDetailsScreen extends AppCompatActivity {

    private FirebaseStorage firebaseStorage;
    private StorageReference storageReference;
    private FirebaseAuth firebaseAuth;
    private FirebaseFirestore firebaseFirestore;

    String bookPdf;
    FloatingActionButton fav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_details_screen);

        fav = findViewById(R.id.floatingActionButton);
        Bundle bundle = getIntent().getExtras();
        bookPdf = bundle.getString("BookId");
        String bookTitle = bundle.getString("BookTitle");
        PDFView pdfView = findViewById(R.id.pdfView);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(bookTitle);
        actionBar.setDisplayHomeAsUpEnabled(true);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseStorage = FirebaseStorage.getInstance();
        storageReference = firebaseStorage.getReference();
        firebaseFirestore = FirebaseFirestore.getInstance();

        fav.setOnClickListener(v -> {
            FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
            String userMail = firebaseUser.getEmail();
            String bookName = bookTitle;

            HashMap<String, Object> hashMap = new HashMap<>();
            hashMap.put("usermail",userMail);
            hashMap.put("bookname", bookName);

            firebaseFirestore.collection("Liked").add(hashMap).addOnSuccessListener(documentReference -> {

            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {

                }
            });
        });

//        firebaseStorage = FirebaseStorage.getInstance();
//        storageReference = firebaseStorage.getReference();
//
//        final String pdfName = "pdf/" + bookPdf + ".pdf";
//        storageReference = FirebaseStorage.getInstance().getReference(pdfName);
//        storageReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
//            @Override
//            public void onSuccess(Uri uri) {

                pdfView.fromAsset(bookPdf+".pdf")
                        .enableSwipe(true)
                        .enableDoubletap(true)
                        .defaultPage(0)
                        .enableAnnotationRendering(false)
                        .password(null)
                        .scrollHandle(null)
                        .enableAntialiasing(true)
                        .spacing(0)
                        .load();
            }
//        });
//    }
}