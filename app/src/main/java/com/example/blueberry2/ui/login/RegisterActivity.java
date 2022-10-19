package com.example.blueberry2.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.blueberry2.MainActivity;
import com.example.blueberry2.R;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {

    private static final String TAG = "RegisterActivity";
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAuth = FirebaseAuth.getInstance();

        Button sign_up = findViewById(R.id.join_button);
        sign_up.setOnClickListener(v -> signUp());

    }

    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        mAuth.getCurrentUser();
    }
    private void signUp() {
        // 이메일
        EditText emailEditText = findViewById(R.id.join_email);
        String email = emailEditText.getText().toString();
        // 비밀번호
        EditText passwordEditText = findViewById(R.id.join_password);
        String password = passwordEditText.getText().toString();

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d(TAG, "createUserWithEmail:success");
                        mAuth.getCurrentUser();
                        Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                        startActivity(intent);
                        Toast.makeText(RegisterActivity.this, "등록 완료", Toast.LENGTH_SHORT).show();
                        finish();
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w(TAG, "createUserWithEmail:failure", task.getException());
                        Toast.makeText(RegisterActivity.this, "등록 에러", Toast.LENGTH_SHORT).show();
                    }
                });
    }

}