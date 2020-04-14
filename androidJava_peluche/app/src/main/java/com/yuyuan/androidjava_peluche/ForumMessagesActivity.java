package com.yuyuan.androidjava_peluche;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ForumMessagesActivity extends AppCompatActivity {

    private DatabaseReference mDatabase;
    private TextView topicTextView;
    private Button newMessageButton;
    private String sujet;
    private List<Message> messages;
    private MessageAdapter adapter;
    private String u;
    private String d;
    private String t;
    private String m;
    String TAG = "messagesForum";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forum_messages);

        sujet = getIntent().getStringExtra("topic");
        topicTextView = findViewById(R.id.textViewTopic);
        topicTextView.setText(sujet);

        mDatabase = FirebaseDatabase.getInstance().getReference();
        messages = new ArrayList<>();
        final DatabaseReference ref = mDatabase.child("Messages");

        if (ref != null) {

            ValueEventListener topicListener = new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                        HashMap temp = (HashMap)postSnapshot.getValue();
                        Log.i(TAG, "onDataChange: temp" + temp);
                        u = (String)temp.get("Utilisateur");
                        d = (String)temp.get("Date");
                        t = (String)temp.get("Topic");
                        m = (String)temp.get("Message");
                        if (sujet.equals(t)){
                            Message msg = new Message(u , d , t , m);
                            messages.add(msg);
                        }
                    }
                    Log.i(TAG, "onDataChange: out " + messages);
                    adapter = new MessageAdapter(messages);
                    RecyclerView topicRecyclerView = findViewById(R.id.messagesRecyclerView);
                    topicRecyclerView.setAdapter(adapter);
                    topicRecyclerView.setLayoutManager(new LinearLayoutManager(ForumMessagesActivity.this));
                }
                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                }
            };
            ref.addValueEventListener(topicListener);
        }
        newMessageButton = findViewById(R.id.newMessageButton);
        newMessageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToNewMessage();
            }
        });
    }

    private void goToNewMessage() {
        Intent intentNewMessage = new Intent(this , NewMessageActivity.class);
        intentNewMessage.putExtra("topic", this.sujet);
        startActivity(intentNewMessage);
    }
}
