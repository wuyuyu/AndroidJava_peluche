package com.yuyuan.androidjava_peluche;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
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
        EditText quantite = findViewById(R.id.editTextQuantite);
        String quantiteStr = quantite.getText().toString();

        try {
            int quantiteAchat = 1;
            prixTotal.setText("Total: " + quantiteAchat *29.99 + "€");
            Log.i("boutique", "setText quantiteAchat");
            quantiteAchat = Integer.parseInt(quantite.getText().toString());

            if (quantiteStr.isEmpty()) {
                Toast.makeText(BoutiqueActivity.this, "Quantité vide", Toast.LENGTH_SHORT)
                        .show();

                return;
            }



            if (livraison1.isSelected()) {

                Log.i("boutique", "livraison 1");

                prixTotal.setText("Total: " + quantiteAchat * 29.99 + "€");
            }
            if (livraison2.isSelected()) {
                prixTotal.setText("Total: " + quantiteAchat * 35.98 + "€");
                Log.i("boutique", "livraison 2");
            }

        } catch (NumberFormatException nfe) {
            nfe.printStackTrace();
        }

    }


}
