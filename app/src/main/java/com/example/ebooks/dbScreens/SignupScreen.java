package com.example.ebooks.dbScreens;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.example.ebooks.MainActivity;
import com.example.ebooks.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignupScreen extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;
    EditText emailText, passwordText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_screen);

        firebaseAuth = FirebaseAuth.getInstance();
        emailText = findViewById(R.id.etEm);
        passwordText = findViewById(R.id.etPss);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

    }

    public void signUpClicked(View view){
        String email = emailText.getText().toString();
        String password = passwordText.getText().toString();
//        if (email.matches("")){
//            AlertDialog.Builder builder = new AlertDialog.Builder(LoginScreen.this);
//            builder.setTitle("Hata Mesajı");
//            builder.setMessage("E-mail alanı boş bırakılamaz!");
//            builder.setNegativeButton("Tamam", null);
//            builder.show();
//        }
//        else if (password.matches("")){
//            AlertDialog.Builder builder = new AlertDialog.Builder(LoginScreen.this);
//            builder.setTitle("Hata Mesajı");
//            builder.setMessage("Şifre alanı boş bırakılamaz!");
//            builder.setNegativeButton("Tamam", null);
//            builder.show();
//        }
        firebaseAuth.createUserWithEmailAndPassword(email,password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        }).addOnFailureListener(e -> Log.v("Error",e.getLocalizedMessage()));
    }

}