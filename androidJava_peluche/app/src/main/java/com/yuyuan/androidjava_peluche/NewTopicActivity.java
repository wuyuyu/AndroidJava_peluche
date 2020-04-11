package com.yuyuan.androidjava_peluche;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class NewTopicActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;
    private FirebaseUser user;
    private String userId;
    private User utilisateur;
    private TextView dateTextView;
    private TextView createurTopicTextView;
    private EditText topicEditText;
    private EditText message1EditText;
    private Button validerTopicButton;
    private String dateFormate;
    private SimpleDateFormat format;
    private String pseudo;
    private String nouveauSujet;
    private String message;
    private Topic sujet;
    private Message msg1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_topic);

        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();
        user = mAuth.getCurrentUser();
        userId = user.getUid();
        DatabaseReference ref = mDatabase.child("utilisateurs").child(userId);
        Date aujourdhui = new Date();
        format = new SimpleDateFormat("dd/MM/YYYY HH:mm");
        dateFormate = format.format(aujourdhui);
        dateTextView = findViewById(R.id.dateTextView);
        createurTopicTextView = findViewById(R.id.createurTopicTextView);
        topicEditText = findViewById(R.id.topicEditText);
        message1EditText = findViewById(R.id.message1EditText);
        validerTopicButton = findViewById(R.id.validerTopicButton);

        dateTextView.setText(" " + dateFormate + " ");
        Log.i("NewTopic", "onCreate: " + dateFormate);

        if (ref != null) {
            ValueEventListener utilisateurListener = new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    utilisateur = dataSnapshot.getValue(User.class);
                    if(utilisateur != null) {
                        if (utilisateur.userFirstName != null) {
                            pseudo = utilisateur.userNickName;
                            createurTopicTextView.setText(" " + pseudo + " ");
                        }
                    }
                }
                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                }
            };
            ref.addValueEventListener(utilisateurListener);
        }

        validerTopicButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                creationTopic();
                goToForum();
            }
        });

    }

    private void goToForum() {
        Intent intentForum = new Intent(this , ForumActivity.class);
        startActivity(intentForum);
        finish();
    }

    private void creationTopic() {
        nouveauSujet = topicEditText.getText().toString();
        message = message1EditText.getText().toString();
        Date maintenant = new Date();
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/YYYY HH:mm");
        String dateF = format.format(maintenant);

        msg1 = new Message(utilisateur , dateF , nouveauSujet , message);
        String keyMsg = mDatabase.child("Messages").push().getKey();
        Map<String, Object> msgValues = msg1.toMap();
        Map<String, Object> childUpdates = new HashMap<>();
        childUpdates.put("/Messages/" + keyMsg, msgValues);
        mDatabase.updateChildren(childUpdates);

        sujet = new Topic(utilisateur , dateFormate , nouveauSujet , msg1);
        Map<String, Object> tpcValues = sujet.toMap();
        Map<String, Object> childUpdatesTpc = new HashMap<>();
        childUpdatesTpc.put("/Topics/" + sujet.uid, tpcValues);
        mDatabase.updateChildren(childUpdatesTpc);
    }
}