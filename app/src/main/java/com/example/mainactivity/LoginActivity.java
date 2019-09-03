package com.example.mainactivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
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
import android.content.Intent;
public class LoginActivity extends AppCompatActivity implements View.OnClickListener{
    private Button buttonSignIn;
    private EditText editLoginEmail;
    private EditText editLoginPassword;
    private TextView textViewSignup;
    private ProgressDialog progressBar;

    private FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        firebaseAuth = FirebaseAuth.getInstance();
        if(firebaseAuth.getCurrentUser() !=null) {
            //finish();
            //startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
        }
        editLoginEmail = (EditText) findViewById(R.id.editTextEmail);
        editLoginPassword = (EditText) findViewById(R.id.editTextPassword);
        buttonSignIn = (Button) findViewById(R.id.buttonLogin);
        textViewSignup = (TextView) findViewById(R.id.textViewSignUp);
        progressBar = new ProgressDialog(this);
        buttonSignIn.setOnClickListener(this);
        textViewSignup.setOnClickListener(this);

    }

    private void userLogin() {
        String email = editLoginEmail.getText().toString().trim();
        String password = editLoginPassword.getText().toString().trim();
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
        firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                progressBar.dismiss();
                if(task.isSuccessful()) {
                    finish();
                    startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
                }

            }
        });
    }
    @Override
    public void onClick(View view) {
        if(view == buttonSignIn) {
            userLogin();
        }
        if(view == textViewSignup) {
            finish();
            startActivity(new Intent(this, MainActivity.class));
        }
    }
}
