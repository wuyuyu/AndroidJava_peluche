package com.yuyuan.androidjava_peluche;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class ForumActivity extends AppCompatActivity {

    private List<Topic> topics;
    private Button newTopicButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forum);

        topics = new ArrayList<>();
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
