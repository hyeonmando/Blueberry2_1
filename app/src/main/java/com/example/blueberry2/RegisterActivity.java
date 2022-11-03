package com.example.blueberry2;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.blueberry2.ClientSave;
import com.example.blueberry2.MainActivity;
import com.example.blueberry2.R;
import com.example.blueberry2.UserModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;

public class RegisterActivity extends AppCompatActivity {

    private static final String TAG = "RegisterActivity";
    private FirebaseAuth mAuth;

    private FirebaseDatabase database = FirebaseDatabase.getInstance(); // 파이어베이스 데이터베이스 연동
    private DatabaseReference databaseReference = database.getReference();
    //DatabaseReference는 데이터베이스의 특정 위치로 연결하는 거라고 생각하면 된다.
    //현재 연결은 데이터베이스에만 딱 연결해놓고
    //키값(테이블 또는 속성)의 위치 까지는 들어가지는 않은 모습이다.

    private EditText email;
    private EditText name;
    private EditText password;
    private EditText birth;
    private Button signup;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        mAuth = FirebaseAuth.getInstance();

        email=(EditText)findViewById(R.id.join_email);
        name=(EditText) findViewById(R.id.join_name);
        password=(EditText)findViewById(R.id.join_password);
        birth=findViewById(R.id.join_bir_6);
        signup=(Button)findViewById(R.id.join_button);


        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(email.getText().toString()==null || name.getText().toString()==null||password.getText().toString()==null||birth.getText().toString()==null){
                    Toast.makeText(RegisterActivity.this, "등록 에러", Toast.LENGTH_SHORT).show();
                    return;
                }
                FirebaseAuth.getInstance()
                        .createUserWithEmailAndPassword(email.getText().toString(), password.getText().toString())
                        .addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                UserModel userModel=new UserModel();
                                userModel.userName=name.getText().toString();
                                userModel.userEmail=email.getText().toString();
                                userModel.userPassword=password.getText().toString();
                                userModel.userBirth=birth.getText().toString();

                                String uid=task.getResult().getUser().getUid();
                                FirebaseDatabase.getInstance().getReference().child("users").child(uid).setValue(userModel);


                            }
                        });
            }
        });


    }

    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        mAuth.getCurrentUser();
    }

    //값을 파이어베이스 Realtime database로 넘기는 함수


}