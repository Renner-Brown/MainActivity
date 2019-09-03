package com.example.mainactivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private Button buttonRegister;
    private EditText editTextEmail;
    private EditText editTextPassword;
    private TextView textViewSignIn;
    private ProgressDialog progressBar;
    private FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        firebaseAuth = FirebaseAuth.getInstance();
        if(firebaseAuth.getCurrentUser() !=null) {
            //finish();
            //startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
        }
        progressBar = new ProgressDialog(this);
        buttonRegister = (Button) findViewById(R.id.buttonRegister);
        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        textViewSignIn = (TextView) findViewById(R.id.textViewSign);
        buttonRegister.setOnClickListener(this);
        textViewSignIn.setOnClickListener(this);
    }

    private void registerUser(){
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();
        if(TextUtils.isEmpty(email)) {
            //email is empty
            Toast.makeText(this, "email is empty", Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(password)) {
            //password is empty
            Toast.makeText(this, "password is empty", Toast.LENGTH_SHORT).show();
            return;

        }
        progressBar.setMessage("Registering User. Please wait.");
        progressBar.show();
        firebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()) {
                    Toast.makeText(MainActivity.this, "Registered successfuly", Toast.LENGTH_SHORT).show();
                    progressBar.hide();
                    finish();
                    startActivity(new Intent(getApplicationContext(), ProfileActivity.class));                }
                else {
                    Toast.makeText(MainActivity.this, "Did not register successfuly", Toast.LENGTH_SHORT).show();
                    progressBar.hide();

                }
            }
        });
    }
    @Override
    public void onClick(View view) {
        if(view == buttonRegister) {
            registerUser();
        }
        if(view == textViewSignIn) {
            //will open sign in
            startActivity(new Intent(this, LoginActivity.class));
        }

    }
}
