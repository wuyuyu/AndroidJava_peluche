package com.yuyuan.androidjava_peluche;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class BoutiqueActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private FirebaseUser user;
    private DatabaseReference mDatabase;
    private String userMail;
    private RadioGroup radioGroupLivraison;
    private RadioButton livraison1;
    private RadioButton livraison2;
    private String modeLivraison;
    private TextView prixTotal;
    private Button acheterButton;
    private EditText quantite;
    private int quantiteInt;
    private float prixLivraison;
    private String aPayer;
    private View mailTextInputLayout;
    private EditText mailTextInputEditText;
    private float total;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_boutique);

        radioGroupLivraison = findViewById(R.id.radioGroupLivraison);
        livraison1 = findViewById(R.id.radioButtonLivraison1);
        livraison2 = findViewById(R.id.radioButtonLivraison2);
        prixTotal = findViewById(R.id.textViewPrixTotal);
        acheterButton = findViewById(R.id.buttonAcheter);
        quantite = findViewById(R.id.editTextQuantite);
        mailTextInputLayout = findViewById(R.id.mailTextInputLayout);
        mailTextInputEditText = findViewById(R.id.mailTextInputEditText);
        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();
        mDatabase = FirebaseDatabase.getInstance().getReference();

        if(user != null){
            mailTextInputLayout.setVisibility(View.INVISIBLE);
            userMail = user.getEmail();
        }else {
            mailTextInputLayout.setVisibility(View.VISIBLE);
        }

        livraison1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                prixLivraison = 0;
                total = calculer();
                prixTotal.setText("Total: " + total + "€");
                Log.i("boutique", "set onClickListener livraison gratuit");
                modeLivraison = "Livraison gratuite";

            }
        });

        livraison2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                prixLivraison = (float) 5.99;
                total = calculer();
                prixTotal.setText("Total: " + total + "€");
                Log.i("boutique", "set onClickListener livraison 5.99€");
                modeLivraison = "Livraison payante";

            }
        });

        acheterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkLivraison() == true && checkMail() == true) {
                    reserver();
                    Toast.makeText(BoutiqueActivity.this, "Vous serez prévenu(e) par mail lorsque la peluche sera disponible.", Toast.LENGTH_SHORT)
                            .show();
                    goToAcceuil();
                }
            }
        });

    }

    private void goToAcceuil() {
        Intent intentAcceuil = new Intent(this , MainActivity.class);
        startActivity(intentAcceuil);
        finish();
    }

    private float calculer() {
        String quantiteStr  = quantite.getText().toString();
        if (quantiteStr.isEmpty()) {
            quantiteInt = 1;
        }else {

            quantiteInt = Integer.parseInt(quantiteStr);
        }
        float coutTotal =(float) (quantiteInt *29.99) + prixLivraison;
        return coutTotal;
    }

    private void reserver() {
            aPayer = String.valueOf(total);
            String key = mDatabase.child("liste_reservation").push().getKey();
            Reservation resa = new Reservation(userMail, quantiteInt, modeLivraison, aPayer);
            Map<String, Object> resaValues = resa.toMap();
            Map<String, Object> childUpdates = new HashMap<>();
            childUpdates.put("/liste_reservation/" + key, resaValues);
            mDatabase.updateChildren(childUpdates);

    }

    private boolean checkLivraison(){
        if (radioGroupLivraison.getCheckedRadioButtonId() == -1){
            Toast.makeText(BoutiqueActivity.this, "Veuillez choisir un mode de livraison.", Toast.LENGTH_SHORT)
                    .show();
            return false;
        }else {
            return true;
        }
    }

    private boolean checkMail(){
        if(user == null) {
            userMail = mailTextInputEditText.getText().toString();
            if (userMail.isEmpty()){
                Toast.makeText(BoutiqueActivity.this, "Veuillez entrer un mail afin d'être inscrit sur la liste de résevation", Toast.LENGTH_SHORT)
                        .show();
                return false;
            }else {
                return true;
            }
        }else {
            return true;
        }
    }

}
