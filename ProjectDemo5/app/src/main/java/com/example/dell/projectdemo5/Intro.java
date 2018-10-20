package com.example.dell.projectdemo5;

import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;

public class Intro extends AppCompatActivity {
     ImageView img;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);
        final DataUniversity dataUniversity = new DataUniversity(this);
        img = findViewById(R.id.imgintro);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intro.this,MainActivity.class);
                Bundle data = new Bundle();
                data.putParcelableArrayList("uni",dataUniversity.getUniversityList());
                intent.putExtra("data",data);
                startActivity(intent);
            }
        });
    }
}
