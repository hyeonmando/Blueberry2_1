package com.example.blueberry2.ui.login;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.blueberry2.R;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

    }
    @Override
    public void onBackPressed() {
        setContentView(R.layout.activity_login);
    }

}