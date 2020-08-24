package com.myfirstwork.myfirstwork.activity.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.myfirstwork.myfirstwork.R;
import com.myfirstwork.myfirstwork.data.DBHelper;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener authStateListener;
    private FirebaseAnalytics mFirebaseAnalytics;
    EditText email;
    EditText password;
    Button signIn,signUp,go;
    private Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context=this;
        DBHelper dbHelper = new DBHelper(this);
        dbHelper.close();
        go=findViewById(R.id.go);
        email=findViewById(R.id.email);
        password=findViewById(R.id.password);
        signIn=findViewById(R.id.sign_in);
        signUp=findViewById(R.id.sign_up);
        signIn.setOnClickListener(this);
        signUp.setOnClickListener(this);
        go.setOnClickListener(this);
        mAuth=FirebaseAuth.getInstance();
        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if(user!=null){
                    Log.e("CurrentUser is ", user.getEmail());
                }else{
                    Log.e("Current user is ", "out");
                }
            }
        };
    }

    @Override
    public void onClick(View view) {
        if(view.getId()==signIn.getId()){
            signIng(String.valueOf(email.getText()),String.valueOf(password.getText()));
        }else if (view.getId()==signUp.getId()){
            signUping(String.valueOf(email.getText()),String.valueOf(password.getText()));
        }else {
            Intent intent = new Intent(context, LentaActivity.class);
            startActivity(intent);
        }
    }
    private void signIng(String login,String password){
        mAuth.signInWithEmailAndPassword(login,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()) {
                    Toast toast = Toast.makeText(getApplicationContext(), "Авторизация успешна", Toast.LENGTH_LONG);
                    toast.show();
                    Intent intent = new Intent(context,LentaActivity.class);
                    startActivity(intent);
                }else{
                    Toast toast = Toast.makeText(getApplicationContext(), "Авторизация провалена", Toast.LENGTH_LONG);
                    toast.show();
                }
            }
        });
    }
    private void signUping(String email,String password){
        mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast toast = Toast.makeText(getApplicationContext(), "Регистрация успешна", Toast.LENGTH_LONG);
                    toast.show();
                }else {
                    Toast toast = Toast.makeText(getApplicationContext(), "Регистрация провалена", Toast.LENGTH_LONG);
                    toast.show();
                }
            }
        });
    }
}
