package com.example.ebooks;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.github.barteksc.pdfviewer.PDFView;


public class BookDetailsScreen extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_details_screen);

        Bundle bundle = getIntent().getExtras();
        String bookPdf = bundle.getString("BookId");
        String bookTitle = bundle.getString("BookTitle");

        getSupportActionBar().setTitle(bookTitle);

        PDFView pdfView = findViewById(R.id.pdfView);
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
}