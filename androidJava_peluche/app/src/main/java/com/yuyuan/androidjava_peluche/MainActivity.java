package com.yuyuan.androidjava_peluche;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
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
    private DatabaseReference mDatabase;
    private String userId;
    private TextView welcomeTextView;
    private Button buttonInscription;
    private Button buttonMonCompte;
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


        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();

        welcomeTextView = findViewById(R.id.welcomeTextView);
        buttonMonCompte = findViewById(R.id.monCompteButton);
        buttonInscription = findViewById(R.id.buttonInscription);
        buttonForum = findViewById(R.id.buttonForum);


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
                            if (utilisateur.userNickName != null) {
                                welcomeTextView.setText("Bienvenue " + utilisateur.userNickName);
                            }
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
            buttonForum.setVisibility(View.VISIBLE);
            buttonForum.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    goToForum();
                }
            });
        } else {
            // No user is signed in
            buttonMonCompte.setVisibility(View.GONE);
            buttonInscription.setText("Inscription/Connexion");
            buttonInscription.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    goToConnection();
                }
            });
            buttonForum.setVisibility(View.GONE);
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

    private void goToForum() {
        final Intent intentForum = new Intent(this, ForumActivity.class);
        startActivity(intentForum);
        finish();
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
