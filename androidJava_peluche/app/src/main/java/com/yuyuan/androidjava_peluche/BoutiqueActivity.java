package com.yuyuan.androidjava_peluche;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class BoutiqueActivity extends AppCompatActivity {


    //    private TextView prix = findViewById(R.id.textViewPrix);
    private RadioButton livraison1;
    private RadioButton livraison2;
    private TextView prixTotal;
    private Button acheter;
    private RadioGroup radioGroupLivraison;
    private EditText quantite;

    private int quantiteInt;
    float prixLivraison;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_boutique);

        Log.i("boutique", "onCreate");

        livraison1 = findViewById(R.id.radioButtonLivraison1);
        livraison2 = findViewById(R.id.radioButtonLivraison2);
        prixTotal = findViewById(R.id.textViewPrixTotal);
        acheter = findViewById(R.id.buttonAcheter);
        radioGroupLivraison = findViewById(R.id.radioGroupLivraison);
        quantite = findViewById(R.id.editTextQuantite);

        quantiteInt = 1;




        quantite.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                String quantiteStr  = quantite.getText().toString();
                quantiteInt = Integer.parseInt(quantiteStr);
                prixTotal.setText("Total: " + quantiteInt *29.99 + "€");
            }
        });

        livraison1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                prixLivraison = 0;
                Log.i("boutique", "set onClickListener livraison gratuit");
            }
        });

        livraison2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                prixLivraison = (float) 5.99;
                Log.i("boutique", "set onClickListener livraison 5.99€");
            }
        });

        prixTotal.setText("Total: " + quantiteInt*( 29.99 + prixLivraison) + "€");

        /*if (quantiteStr.isEmpty()) {
            Toast.makeText(BoutiqueActivity.this, "Montant vide", Toast.LENGTH_SHORT)
                    .show();

            return;
        }

         */





    }


}
