package com.yuyuan.androidjava_peluche;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class DeconnexionActivity extends AppCompatActivity {
    private Button deconnexionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deconnexion);

        deconnexionButton = findViewById(R.id.deconnexionButton);
        deconnexionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Toast.makeText(DeconnexionActivity.this, "Vous êtes déconnecté(e)", Toast.LENGTH_SHORT)
                        .show();
                goToAcceuil();
            }
        });
    }

    private void goToAcceuil() {
        Intent intentAcceuil = new Intent(this , MainActivity.class);
        startActivity(intentAcceuil);
        finish();
    }
}
