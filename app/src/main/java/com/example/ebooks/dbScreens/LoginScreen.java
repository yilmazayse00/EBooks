package com.example.ebooks.dbScreens;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ebooks.MainActivity;
import com.example.ebooks.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginScreen extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;
    EditText emailText, passwordText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);

        firebaseAuth = FirebaseAuth.getInstance();
        emailText = findViewById(R.id.etEmail);
        passwordText = findViewById(R.id.etPassword);

        //Control of whether user logged
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
        if (firebaseUser != null){
            Intent intent = new Intent(LoginScreen.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }

    public void signIn_click(View view) {
        String email = emailText.getText().toString();
        String password = passwordText.getText().toString();
        if (email.matches("")) {
            AlertDialog.Builder builder = new AlertDialog.Builder(LoginScreen.this);
            builder.setTitle("Hata Mesajı");
            builder.setMessage("E-mail alanı boş bırakılamaz!");
            builder.setNegativeButton("Tamam", null);
            builder.show();
        } else {
            firebaseAuth.signInWithEmailAndPassword(email, password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                @Override
                public void onSuccess(AuthResult authResult) {
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(LoginScreen.this, e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    public void signUp_click(View view){
        Intent intent = new Intent(getApplicationContext(),SignupScreen.class);
        startActivity(intent);
    }
}