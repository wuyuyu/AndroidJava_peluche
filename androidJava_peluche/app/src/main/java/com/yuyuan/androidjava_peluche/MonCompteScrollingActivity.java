package com.yuyuan.androidjava_peluche;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.text.Layout;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MonCompteScrollingActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private String avatar;
    private EditText nomEditText;
    private String nom;
    private EditText prenomEditText;
    private String prenom;
    private EditText pseudoEditText;
    private String pseudo;
    private EditText ageEditText;
    private String age;
    private Button monCompteButton;
    private String userId;
    private DatabaseReference mDatabase;

    String TAG = "MonCompteScrolling";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();

        setContentView(R.layout.activity_mon_compte_scrolling);

        nomEditText = findViewById(R.id.nomTextInputEditText);
        prenomEditText = findViewById(R.id.prenomTextInputEditText);
        pseudoEditText = findViewById(R.id.pseudoTextInputEditText);
        ageEditText = findViewById(R.id.ageEditText);
        monCompteButton = findViewById(R.id.monCompteButton);

        FirebaseUser user = mAuth.getCurrentUser();
        userId = user.getUid();

        DatabaseReference ref = mDatabase.child("utilisateurs").child(userId);
        Log.i(TAG, "onCreate: ref = " + ref);

        if (ref != null) {
            Log.i(TAG, "onCreate: " + ref.child("nom"));

            ValueEventListener utilisateurListener = new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    User utilisateur = dataSnapshot.getValue(User.class);
                    if(utilisateur != null) {
                        if (utilisateur.userLastName != null) {
                            nomEditText.setText(utilisateur.userLastName);
                        }
                        if (utilisateur.userFirstName != null) {
                            prenomEditText.setText(utilisateur.userFirstName);
                        }
                        if (utilisateur.userNickName != null) {
                            pseudoEditText.setText(utilisateur.userNickName);
                        }
                        if (utilisateur.userChildAge != null) {
                            ageEditText.setText(utilisateur.userChildAge);
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
                            selectAvatar.setChecked(true);
                        }
                        Log.i(TAG, "onDataChange: " + utilisateur);
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }

            };
            ref.addValueEventListener(utilisateurListener);
        }


        monCompteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                avatar = onAvatarSelected(v);
                if(collectInfo(avatar) == true) {
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

    private boolean collectInfo(String avatar) {
        nom = nomEditText.getText().toString();
        prenom = prenomEditText.getText().toString();
        pseudo = pseudoEditText.getText().toString();
        age = ageEditText.getText().toString();

        if(nom.isEmpty() || prenom.isEmpty() || pseudo.isEmpty() || age.isEmpty()){
            Toast.makeText(MonCompteScrollingActivity.this, "Veuillez remplir tous les champs et sélectionner un avatar", Toast.LENGTH_SHORT)
                    .show();
            return false;
        } else {
            User user = new User(userId, avatar, nom, prenom, pseudo, age);
            Log.i(TAG, "user is " + user.toString());
            mDatabase.child("utilisateurs").child(userId).setValue(user);
            return true;
        }
    }

    public String onAvatarSelected(View view) {
        final RadioGroup radioGroup = (RadioGroup) findViewById(R.id.avatarRadioGroup);
        int id = radioGroup.getCheckedRadioButtonId();
        Log.i(TAG, "onAvatarSelected: " + id);
        switch (id) {
            case 2131230797:
                avatar = "chat";
                break;

            case 2131230798:
                avatar = "cochon";
                break;

            case 2131230799:
                avatar = "hamster";
                break;

            case 2131230800:
                avatar = "panda";
                break;

            case 2131230801:
                avatar = "lion";
                break;

            case 2131230802:
                avatar = "singe";
                break;

            case 2131230803:
                avatar = "hibou";
                break;

            case 2131230804:
                avatar = "souris";
                break;

            case 2131230805:
                avatar = "chien";
                break;

            default:
                Toast.makeText(MonCompteScrollingActivity.this, "Veuillez sélectionner un avatar", Toast.LENGTH_SHORT)
                        .show();
                avatar = null;
            }
            return avatar;
        }

}
