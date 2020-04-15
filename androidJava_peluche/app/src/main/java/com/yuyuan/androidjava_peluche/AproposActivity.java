package com.yuyuan.androidjava_peluche;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class AproposActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apropos);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.toolbar);
        View view =getSupportActionBar().getCustomView();
        //getActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);

        ImageButton imageButton= (ImageButton)view.findViewById(R.id.action_bar_back);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToAcceuil();
                finish();
            }
        });

        Intent srcIntent = getIntent();
        String s = srcIntent.getStringExtra("TextViewVersion");


        TextView textViewVersion = findViewById(R.id.TextViewVersion);
        textViewVersion.setText(s);
    }

    private void goToAcceuil() {
        final Intent intentAcceuil = new Intent(this, MainActivity.class);
        startActivity(intentAcceuil);
        finish();
    }
}
