package com.yuyuan.androidjava_peluche;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private TextView welcomeTextView;
    private Button buttonInscription;
    private Button buttonMonCompte;
    private Button buttonContact;
    private Button buttonBoutique;
    private Button buttonComptines;
    private Button buttonForum;
    private Button buttonApropos;
    private String userId;
    private DatabaseReference mDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Peluche Connectée");


        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();


        welcomeTextView = findViewById(R.id.welcomeTextView);
        buttonMonCompte = findViewById(R.id.monCompteButton);
        buttonInscription = findViewById(R.id.buttonInscription);

        final FirebaseUser user = mAuth.getCurrentUser();

        if (user != null) {
            // User is signed in
            userId = user.getUid();
            DatabaseReference ref = mDatabase.child("utilisateurs").child(userId);

            if (ref != null) {

                ValueEventListener utilisateurListener = new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        User utilisateur = dataSnapshot.getValue(User.class);
                        if(utilisateur != null) {
                            if (utilisateur.userFirstName != null) {
                                welcomeTextView.setText("Bienvenue " + utilisateur.userNickName);
                            }
                            if (utilisateur.userAvatar != null) {
                                final RadioGroup radioGroup = (RadioGroup) findViewById(R.id.avatarRadioGroup);
                                RadioButton selectAvatar = findViewById(R.id.avatar4RadioButton);
                                switch (utilisateur.userAvatar) {
                                    case "chat":
                                        selectAvatar = findViewById(R.id.avatar1RadioButton);
                                        break;

                                    case "cochon":
                                        selectAvatar = findViewById(R.id.avatar2RadioButton);
                                        break;

                                    case "hamster":
                                        selectAvatar = findViewById(R.id.avatar3RadioButton);
                                        break;

                                    case "panda":
                                        selectAvatar = findViewById(R.id.avatar4RadioButton);
                                        break;

                                    case "lion":
                                        selectAvatar = findViewById(R.id.avatar5RadioButton);
                                        break;

                                    case "singe":
                                        selectAvatar = findViewById(R.id.avatar6RadioButton);
                                        break;

                                    case "hibou":
                                        selectAvatar = findViewById(R.id.avatar7RadioButton);
                                        break;

                                    case "souris":
                                        selectAvatar = findViewById(R.id.avatar8RadioButton);
                                        break;

                                    case "chien":
                                        selectAvatar = findViewById(R.id.avatar9RadioButton);
                                        break;
                                }
                            }
                            //Log.i(TAG, "onDataChange: " + utilisateur);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }

                };
                ref.addValueEventListener(utilisateurListener);
            }
            buttonMonCompte.setVisibility(View.VISIBLE);
            buttonMonCompte.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    goToMonCompte();
                }
            });
            buttonInscription.setText("Deconnexion");
            buttonInscription.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    goToDeconnexion();
                }
            });
        } else {
            // No user is signed in
            buttonMonCompte.setVisibility(View.INVISIBLE);
            buttonInscription.setText("Inscription/Connexion");
            buttonInscription.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    goToConnection();
                }
            });
        }
        welcomeTextView.setText("Bienvenue");

        buttonContact = findViewById(R.id.buttonContact);
        buttonContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToContact();
                Log.i("MainActivity", "onClick: " + user.getUid());
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

    private void goToDeconnexion() {
        final Intent intentDeconnexion = new Intent(this, DeconnexionActivity.class);
        startActivity(intentDeconnexion);
        finish();
    }

    private void goToMonCompte() {
        final Intent intentMonCompte = new Intent(this , MonCompteScrollingActivity.class);
        startActivity(intentMonCompte);
        finish();
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
        finish();
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
