package com.yuyuan.androidjava_peluche;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class AproposActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apropos);

        Intent srcIntent = getIntent();
        String s = srcIntent.getStringExtra("TextViewVersion");


        TextView textViewVersion = findViewById(R.id.TextViewVersion);
        textViewVersion.setText(s);
    }
}
