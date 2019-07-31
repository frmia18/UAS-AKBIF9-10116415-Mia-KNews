package com.app.frda.lahaula;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.widget.Button;
import android.widget.TextView;

import com.app.frda.lahaula.R;

public class About extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        TextView NoHp = findViewById(R.id.NoHp);
        Button button = findViewById(R.id.button);
        TextView emailmia = findViewById(R.id.emailmia);
        emailmia.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
                emailIntent.setData(Uri.parse("mailto:frmiafm@gmail.com"));
                startActivity(emailIntent);
            }
        }));
        TextView ig = findViewById(R.id.ig);
        ig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("http://instagram.com/_u/frmia18");
                Intent likeIng = new Intent(Intent.ACTION_VIEW, uri);

                likeIng.setPackage("com.instagram.android");

                try {
                    startActivity(likeIng);
                } catch (ActivityNotFoundException e) {
                    startActivity(new Intent(Intent.ACTION_VIEW,
                            Uri.parse("http://instagram.com/frmia18")));
                }
            }
        });
    }
}