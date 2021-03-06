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

public class InscriptionActivity extends AppCompatActivity {
    private EditText emailEditText;
    private String email;
    private EditText mdpEditText;
    private String mdp;
    private EditText mdpConfirmEditText;
    private String mdpConfirm;
    private Button inscriptionButton;
    private TextView dejaInscritTextView;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscription);

        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.toolbar);
        View view =getSupportActionBar().getCustomView();

        ImageButton imageButton= (ImageButton)view.findViewById(R.id.action_bar_back);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToAcceuil();
            }
        });

        mAuth = FirebaseAuth.getInstance();

        emailEditText = findViewById(R.id.emailEditText);

        mdpEditText = findViewById(R.id.mdpEditText);

        mdpConfirmEditText = findViewById(R.id.mdpConfirmEditText);

        inscriptionButton = findViewById(R.id.inscriptionButton);
        inscriptionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkMail() == true && checkPassword()== true){
                    signIn();
                }
            }
        });

        dejaInscritTextView = findViewById(R.id.textViewDejaInscrit);
        dejaInscritTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToConnexion();
            }
        });
    }

    private boolean checkMail(){
        email = emailEditText.getText().toString();
        if (email.isEmpty()) {
            Toast.makeText(InscriptionActivity.this, "Email non renseigné", Toast.LENGTH_SHORT)
                    .show();
            return false;
        }else {
            return true;
        }
    }

    private boolean checkPassword(){
        mdp = mdpEditText.getText().toString();
        mdpConfirm = mdpConfirmEditText.getText().toString();
        if (mdp.isEmpty() || mdpConfirm.isEmpty()) {
            Toast.makeText(InscriptionActivity.this, "Mot de passe non renseigné", Toast.LENGTH_SHORT)
                    .show();
            return false;
        } else if (!mdp.equals(mdpConfirm)) {
            Toast.makeText(InscriptionActivity.this, "Mots de passe différents", Toast.LENGTH_SHORT)
                    .show();
            return false;
        } else {
            return true;
        }
    }

    private void signIn(){
        mAuth.createUserWithEmailAndPassword(email, mdp)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            FirebaseUser user = mAuth.getCurrentUser();
                            Toast.makeText(InscriptionActivity.this, "Inscription réussie.",
                                    Toast.LENGTH_SHORT).show();
                            goToMonCompte();
                            finish();
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(InscriptionActivity.this, "Inscription échouée.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void goToConnexion() {
        final Intent intentConnexion = new Intent(this, ConnexionActivity.class);
        startActivity(intentConnexion);
    }

    private void goToMonCompte(){
        final Intent intentMonCompte = new Intent(this , MonCompteScrollingActivity.class);
        startActivity(intentMonCompte);
        finish();
    }

    private void goToAcceuil() {
        final Intent intentAcceuil = new Intent(this, MainActivity.class);
        startActivity(intentAcceuil);
        finish();
    }
}
