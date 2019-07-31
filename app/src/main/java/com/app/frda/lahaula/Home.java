package com.app.frda.lahaula;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;

public class Home extends AppCompatActivity {

    private ImageButton tes1,search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        LinearLayout button1 = (LinearLayout)findViewById(R.id.hilang);
        LinearLayout button2 = (LinearLayout)findViewById(R.id.temukan);
        LinearLayout button3 = (LinearLayout)findViewById(R.id.about);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home.this,ActivityBerita.class);
                startActivity(intent);
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home.this, VideoActivity.class);
                startActivity(intent);
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home.this,About.class);
                startActivity(intent);

            }
        });


    }
}