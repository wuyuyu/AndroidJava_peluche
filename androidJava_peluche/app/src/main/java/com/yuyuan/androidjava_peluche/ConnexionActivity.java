package com.yuyuan.androidjava_peluche;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ConnexionActivity extends AppCompatActivity {
    private TextView pasInscritTextView;
    private FirebaseAuth mAuth;
    private EditText mailEditText;
    private String mail;
    private EditText mdpConnexionEditText;
    private String mdpConnexion;
    private Button connexionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connexion);

        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.toolbar);
        View view =getSupportActionBar().getCustomView();
        //getActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);

        ImageButton imageButton= (ImageButton)view.findViewById(R.id.action_bar_back);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToAcceuil();
            }
        });

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        FirebaseUser user = mAuth.getCurrentUser();
        if (user != null) {
            // User is signed in
            goToDeconnexion();
        }
            // No user is signed in
        mailEditText = findViewById(R.id.mailEditText);

        mdpConnexionEditText = findViewById(R.id.mdpConnexionEditText);

        connexionButton = findViewById(R.id.connexionButton);
        connexionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkMail() == true && checkPassword() == true) {
                    connect();
                }
            }
        });

        pasInscritTextView = findViewById(R.id.textViewPasInscrit);
        pasInscritTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToInscription();
            }
        });
    }

    private void connect(){
        mAuth.signInWithEmailAndPassword(mail, mdpConnexion)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            FirebaseUser user = mAuth.getCurrentUser();
                            Toast.makeText(ConnexionActivity.this, "Vous êtes connecté(e).",
                                    Toast.LENGTH_SHORT).show();
                            goToMonCompte();
                        } else {
                            // If sign in fails, display a message to the user
                            Toast.makeText(ConnexionActivity.this, "La connexion a échoué.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }

    private boolean checkMail(){
        mail = mailEditText.getText().toString();
        if (mail.isEmpty()) {
            Toast.makeText(ConnexionActivity.this, "Email non renseigné", Toast.LENGTH_SHORT)
                    .show();
            return false;
        }else {
            return true;
        }
    }

    private boolean checkPassword(){
        mdpConnexion = mdpConnexionEditText.getText().toString();
        if (mdpConnexion.isEmpty()) {
            Toast.makeText(ConnexionActivity.this, "Mot de passe non renseigné", Toast.LENGTH_SHORT)
                    .show();
            return false;
        } else {
            return true;
        }
    }

    private void goToMonCompte(){
        final Intent intentMonCompte = new Intent(this , MonCompteScrollingActivity.class);
        startActivity(intentMonCompte);
        finish();
    }

    private void goToInscription() {
        final Intent intentInscription = new Intent(this, InscriptionActivity.class);
        startActivity(intentInscription);
        finish();
    }

    private void goToDeconnexion(){
        final Intent intentDeconnexion = new Intent(this , DeconnexionActivity.class);
        startActivity(intentDeconnexion);
    }

    private void goToAcceuil() {
        final Intent intentAcceuil = new Intent(this, MainActivity.class);
        startActivity(intentAcceuil);
        finish();
    }
}
