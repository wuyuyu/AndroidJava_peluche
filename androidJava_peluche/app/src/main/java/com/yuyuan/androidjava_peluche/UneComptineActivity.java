package com.yuyuan.androidjava_peluche;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class UneComptineActivity extends AppCompatActivity {
    Comptine comptine;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_une_comptine);

        Intent srcIntent = new Intent();
        comptine = getIntent().getParcelableExtra("comptine");
        //comptine = srcIntent.getParcelableExtra("comptine");
        final ImageView imageViewImageUneComptine = findViewById(R.id.imageViewImageUneComptine);
        final TextView textViewNomUneComptine = findViewById(R.id.textViewNomUneComptine);
        final TextView textViewDateUneComptine = findViewById(R.id.textViewDateUneComptine);
        final TextView textViewDescriptionUneComptine = findViewById(R.id.textViewDescriptionUneComptine);

        assert comptine != null;

        System.out.println("comptine:"+comptine);

        //imageViewImageUneComptine.setImageResource(Integer.parseInt(comptine.imageComptine));
        textViewNomUneComptine.setText("nom du comptine:" +comptine.nom);
        textViewDateUneComptine.setText(comptine.date);
        textViewDescriptionUneComptine.setText(comptine.divers);
    }
}
