package com.yuyuan.androidjava_peluche;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
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

public class NewMessageActivity extends AppCompatActivity {
    private TextView messageDateTextView;
    private TextView createurMessageTextView;
    private TextView topicMessageTextView;
    private EditText messageEditText;
    private Button validerMessageButton;
    private String dateFormate;
    private SimpleDateFormat format;
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;
    private FirebaseUser user;
    private String userId;
    private User utilisateur;
    private String pseudo;
    private String sujet;
    private Message msg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_message);
        sujet = getIntent().getStringExtra("topic");

        messageDateTextView = findViewById(R.id.messageDateTextView);
        createurMessageTextView = findViewById(R.id.createurMessageTextView);
        topicMessageTextView = findViewById(R.id.topicMessageTextView);
        messageEditText = findViewById(R.id.messageEditText);
        validerMessageButton = findViewById(R.id.validerMessageButton);

        Date aujourdhui = new Date();
        format = new SimpleDateFormat("dd/MM/YYYY HH:mm:ss");
        dateFormate = format.format(aujourdhui);

        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();
        user = mAuth.getCurrentUser();
        userId = user.getUid();
        DatabaseReference ref = mDatabase.child("utilisateurs").child(userId);

        messageDateTextView.setText(dateFormate);
        topicMessageTextView.setText(sujet);

        if (ref != null) {
            ValueEventListener utilisateurListener = new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    utilisateur = dataSnapshot.getValue(User.class);
                    if(utilisateur != null) {
                        if (utilisateur.userFirstName != null) {
                            pseudo = utilisateur.userNickName;
                            createurMessageTextView.setText(" " + pseudo + " ");
                        }
                    }
                }
                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                }
            };
            ref.addValueEventListener(utilisateurListener);
        }

        validerMessageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                creationMessage();
                goToForumMessages();
            }
        });
    }

    private void goToForumMessages() {
        Intent intent = new Intent(this , ForumMessagesActivity.class);
        intent.putExtra("topic", sujet);
        startActivity(intent);
        finish();
    }

    private void creationMessage() {
        String message = messageEditText.getText().toString();
        msg = new Message(utilisateur.userId , dateFormate , sujet , message);
        String keyMsg = mDatabase.child("Messages").push().getKey();
        Map<String, Object> msgValues = msg.toMap();
        Map<String, Object> childUpdates = new HashMap<>();
        childUpdates.put("/Messages/" + keyMsg, msgValues);
        mDatabase.updateChildren(childUpdates);
        mDatabase.child("Messages").child(keyMsg).setValue(msg);
    }
}
