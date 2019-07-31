package com.app.frda.lahaula;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity2 extends AppCompatActivity {

    //Deklarasi Variable
    private FirebaseAuth.AuthStateListener authListener;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Button Logout = findViewById(R.id.logout);

        //Instance Firebasee Auth
        auth = FirebaseAuth.getInstance();

        //Menambahkan Listener untuk mengecek apakah user telah logout / keluar
        authListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

                //Jika Iya atau Null, maka akan berpindah pada halaman Login
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user == null) {
                    Toast.makeText(MainActivity2.this, "Logout Success", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(MainActivity2.this, LoginActivity2.class));
                    finish();
                }
            }
        };

        Logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Fungsi untuk logout
                auth.signOut();
            }
        });
    }

    //Menerapkan Listener
    @Override
    protected void onStart() {
        super.onStart();
        auth.addAuthStateListener(authListener);
    }

    //Melepaskan Litener
    @Override
    protected void onStop() {
        super.onStop();
        if(authListener != null){
            auth.removeAuthStateListener(authListener);
        }
    }
}