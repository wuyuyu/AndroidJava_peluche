package com.yuyuan.androidjava_peluche;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.text.Layout;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MonCompteScrollingActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private EditText nomEditText;
    private String nom;
    private EditText prenomEditText;
    private String prenom;
    private EditText pseudoEditText;
    private String pseudo;
    private EditText ageEditText;
    private String age;
    private Button monCompteButton;
    private String userId;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();

        setContentView(R.layout.activity_mon_compte_scrolling);

        final RadioGroup radioGroup = (RadioGroup) findViewById(R.id.avatarRadioGroup);
        nomEditText = findViewById(R.id.nomEditText);
        prenomEditText = findViewById(R.id.prenomEditText);
        pseudoEditText = findViewById(R.id.pseudoEditText);
        ageEditText = findViewById(R.id.ageEditText);
        monCompteButton = findViewById(R.id.monCompteButton);

        FirebaseUser user = mAuth.getCurrentUser();
        userId = user.getUid();


        monCompteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });



    }

    private void collectInfo(){
        nom = nomEditText.getText().toString();
        prenom = prenomEditText.getText().toString();
        pseudo = pseudoEditText.getText().toString();
        age = ageEditText.getText().toString();

        mDatabase.child("utilisateurs").child(userId);
    }
}
