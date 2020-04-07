package com.yuyuan.androidjava_peluche;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
                            //updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(InscriptionActivity.this, "Inscription échouée.",
                                    Toast.LENGTH_SHORT).show();
                            //updateUI(null);
                        }
                    }
                });
    }

    private void goToConnexion() {
        final Intent intentConnexion = new Intent(this, ConnexionActivity.class);
        startActivity(intentConnexion);
    }
}
