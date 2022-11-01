package com.example.blueberry2;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.blueberry2.ClientSave;
import com.example.blueberry2.MainActivity;
import com.example.blueberry2.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {

    private static final String TAG = "RegisterActivity";
    private FirebaseAuth mAuth;

    private FirebaseDatabase database = FirebaseDatabase.getInstance(); // 파이어베이스 데이터베이스 연동
    private DatabaseReference databaseReference = database.getReference();
    //DatabaseReference는 데이터베이스의 특정 위치로 연결하는 거라고 생각하면 된다.
    //현재 연결은 데이터베이스에만 딱 연결해놓고
    //키값(테이블 또는 속성)의 위치 까지는 들어가지는 않은 모습이다.



    private EditText name;

    private EditText birth;

    int nId=1000;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAuth = FirebaseAuth.getInstance();


        name=findViewById(R.id.join_name);
        birth=findViewById(R.id.join_bir_6);


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
                        addMember(++nId,name.getText().toString(), email, password, birth.getText().toString());
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
    //값을 파이어베이스 Realtime database로 넘기는 함수
    private void addMember(Integer nId, String name, String email, String password, String birth) {

        String nId_str;

        nId_str=Integer.toString(nId);


        ClientSave clientSave = new ClientSave(nId_str, name, email, password, birth);


        databaseReference.child("member").child(nId_str).setValue(clientSave);
        // child(__) 안에 값을 무조건 String형으로 줘야 한대서 위에 형 변환 해서 한번 더 저장했어

    }

}