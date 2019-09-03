package com.example.mainactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ProfileActivity extends AppCompatActivity implements View.OnClickListener{
    private TextView profileText;
    private Button profileButton;
    private FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        firebaseAuth = FirebaseAuth.getInstance();

        if(firebaseAuth.getCurrentUser() == null) {
            //finish();
            //startActivity(new Intent(this, LoginActivity.class));
        }
        FirebaseUser user = firebaseAuth.getCurrentUser();

        profileText = (TextView) findViewById(R.id.textViewUserEmail);
        profileButton = (Button) findViewById(R.id.buttonLogout);
        profileText.setText("Welcome " +user.getEmail());
        profileButton.setOnClickListener(this);

    }
    @Override
    public void onClick(View view) {
        if(view == profileButton) {
            System.out.println("pressed the thing");
            firebaseAuth.signOut();
            finish();
            startActivity(new Intent(this, LoginActivity.class));

        }


    }
}
