package com.yuyuan.androidjava_peluche;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ContactScrollingActivity extends AppCompatActivity {
    private String TAG = "ContactActivity";
    private DatabaseReference mDatabase;
    private CheckBox checkBoxPropFonction1;
    private CheckBox checkBoxPropFonction2;
    private CheckBox checkBoxPropFonction3;
    private CheckBox checkBoxPropFonction4;
    private CheckBox checkBoxPropFonction5;
    private String cb1;
    private String cb2;
    private String cb3;
    private String cb4;
    private String cb5;
    private EditText textInputEditTextQ3b;
    private EditText suggestionEditText;
    private String reponse1;
    private ArrayList reponse2;
    private String reponse3;
    private String reponse3b;
    private String reponse4;
    private Button contactButton;


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_scrolling);

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
        mDatabase = FirebaseDatabase.getInstance().getReference();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();*/
                Intent i = new Intent(Intent.ACTION_SEND);
                i.setType("message/rfc822");
                i.putExtra(android.content.Intent.EXTRA_EMAIL, new String[]{"tania.rojas@edu.itescia.fr , yuyuan.wu@edu.itescia.fr"});
                i.putExtra(Intent.EXTRA_SUBJECT, "Ton Sujet");
                startActivity(Intent.createChooser(i, "Titre:"));
            }
        });

        checkBoxPropFonction1 = findViewById(R.id.checkBoxPropFonction1);
        checkBoxPropFonction2 = findViewById(R.id.checkBoxPropFonction2);
        checkBoxPropFonction3 = findViewById(R.id.checkBoxPropFonction3);
        checkBoxPropFonction4 = findViewById(R.id.checkBoxPropFonction4);
        checkBoxPropFonction5 = findViewById(R.id.checkBoxPropFonction5);

        cb1 = checkBoxPropFonction1.getText().toString();
        cb2 = checkBoxPropFonction2.getText().toString();
        cb3 = checkBoxPropFonction3.getText().toString();
        cb4 = checkBoxPropFonction4.getText().toString();
        cb5 = checkBoxPropFonction5.getText().toString();

        textInputEditTextQ3b = findViewById(R.id.textInputEditTextQ3b);
        suggestionEditText = findViewById(R.id.suggestionEditText);

        contactButton = findViewById(R.id.contactButton);
        contactButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                collectInfo();
            }
        });
    }

    private void registerForm(String rep1, ArrayList rep2, String rep3, String rep3b, String rep4) {
        // Create new post at /user-posts/$userid/$postid and at
        // /posts/$postid simultaneously
        String key = mDatabase.child("formulaire_peluche").push().getKey();
        FormulairePeluche form = new FormulairePeluche(rep1, rep2, rep3, rep3b, rep4);
        Map<String, Object> formValues = form.toMap();

        Map<String, Object> childUpdates = new HashMap<>();
        childUpdates.put("/formulaire_peluche/" + key, formValues);

        mDatabase.updateChildren(childUpdates);
    }

    private void collectInfo() {
        reponse2 = new ArrayList();

        final RadioGroup radioGroup1 = (RadioGroup) findViewById(R.id.radioGroupQuestion1);
        int id1 = radioGroup1.getCheckedRadioButtonId();
        Log.i(TAG , "collectInfo: " + id1);
        if(id1 != -1){
            RadioButton selectButton1 = findViewById(id1);
            reponse1 = selectButton1.getText().toString();
            Log.i(TAG , "collectInfo: 1 = " + reponse1);
        }else {
            reponse1 = "";
        }
        if(checkBoxPropFonction1.isChecked()){
            reponse2.add(cb1);
        }
        if(checkBoxPropFonction2.isChecked()){
            reponse2.add(cb2);
        }
        if(checkBoxPropFonction3.isChecked()){
            reponse2.add(cb3);
        }
        if(checkBoxPropFonction4.isChecked()){
            reponse2.add(cb4);
        }
        if(checkBoxPropFonction5.isChecked()){
            reponse2.add(cb5);
        }
        if(reponse2.size() < 1){
            reponse2.add("");
        }

        final RadioGroup radioGroup3 = (RadioGroup) findViewById(R.id.radioGroupQuestion3);
        int id3 = radioGroup3.getCheckedRadioButtonId();
        RadioButton selectButton3;
        reponse3 = null;
        if(id1 != -1){
            selectButton3 = findViewById(id3);
            reponse3 = selectButton3.getText().toString();
            Log.i(TAG , "collectInfo: 3 = " + reponse3);
        }else {
            reponse3 = "";
        }
        reponse3b = textInputEditTextQ3b.getText().toString();
        if(reponse3.equals("Non") || reponse3b.isEmpty()){
            reponse3b = "";
        }
        reponse4 = suggestionEditText.getText().toString();
        if(reponse4.isEmpty()){
            reponse4 = "";
        }
        if(id1 == -1){
            Toast.makeText(ContactScrollingActivity.this, "Veuillez sélectionner un prix", Toast.LENGTH_SHORT)
                    .show();
        } else {
            registerForm(reponse1,reponse2,reponse3,reponse3b,reponse4);
        }
    }

    private void goToAcceuil() {
        final Intent intentAcceuil = new Intent(this, MainActivity.class);
        startActivity(intentAcceuil);
        finish();
    }


}
