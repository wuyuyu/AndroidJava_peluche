package com.yuyuan.androidjava_peluche;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
    private TextView welcomeTextView;
    private Button buttonInscription;
    private Button buttonContact;
    private Button buttonBoutique;
    private Button buttonComptines;
    private Button buttonForum;
    private Button buttonApropos;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Peluche Connectée");

        welcomeTextView = findViewById(R.id.welcomeTextView);
        welcomeTextView.setText("Bienvenue");

        buttonInscription = findViewById(R.id.buttonInscription);
        buttonInscription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToConnection();
            }
        });

        buttonContact = findViewById(R.id.buttonContact);
        buttonContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToContact();
            }
        });

        buttonBoutique = findViewById(R.id.buttonBoutique);
        buttonBoutique.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToBoutique();
            }
        });

        buttonApropos = findViewById(R.id.buttonApropos);
        buttonApropos.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                goToApropos();
            }
        });

        buttonComptines = findViewById(R.id.buttonComptines);
        buttonComptines.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoComptines();
            }
        });

    }

    private void gotoComptines() {
        final Intent intentComptines = new Intent(this,ComptinesListeActivity.class);
        startActivity(intentComptines);
    }

    private void goToContact() {
        final Intent intentContact = new Intent(this, ContactScrollingActivity.class);
        startActivity(intentContact);
    }

    private void goToConnection() {
        final Intent intentConnection = new Intent(this, ConnexionActivity.class);
        startActivity(intentConnection);
    }
    private void goToBoutique() {
        final Intent intentBoutique = new Intent(this, BoutiqueActivity.class);
        startActivity(intentBoutique);
    }
    private void goToApropos() {
        final Intent intentApropos = new Intent(this,AproposActivity.class);
        intentApropos.putExtra("TextViewVersion","V.1.2.3");
        startActivity(intentApropos);
    }
}
