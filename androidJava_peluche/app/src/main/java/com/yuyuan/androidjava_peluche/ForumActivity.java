package com.yuyuan.androidjava_peluche;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ForumActivity extends AppCompatActivity {

    private List<Topic> topics;
    private Button newTopicButton;
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;
    private DataSnapshot postSnapshot;
    private String userId;
    private TopicAdapter adapter;
    String TAG = "forum";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forum);

        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();
        topics = new ArrayList<>();
        final DatabaseReference ref = mDatabase.child("Topics");

        if (ref != null) {

            ValueEventListener topicListener = new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                        Topic sujet = postSnapshot.getValue(Topic.class);
                        topics.add(sujet);
                        Log.i(TAG, "onDataChange: " + topics);
                    }
                    Log.i(TAG, "onDataChange: out " + topics);
                    adapter = new TopicAdapter(topics);
                    RecyclerView topicRecyclerView = findViewById(R.id.topicRecyclerView);
                    topicRecyclerView.setAdapter(adapter);
                    topicRecyclerView.setLayoutManager(new LinearLayoutManager(ForumActivity.this));
                }
                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                }
            };
            ref.addValueEventListener(topicListener);
        }
        newTopicButton = findViewById(R.id.newTopicButton);
        newTopicButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToNewTopic();
            }
        });

    }

    private void goToNewTopic() {
        Intent intentNewTopic = new Intent(this , NewTopicActivity.class);
        startActivity(intentNewTopic);
    }
}
