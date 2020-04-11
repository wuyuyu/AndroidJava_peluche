package com.yuyuan.androidjava_peluche;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

import java.util.HashMap;
import java.util.Map;

@IgnoreExtraProperties
public class Topic {
    public String uid;
    public User user;
    public String date;
    public String intitule;
    public Message message1;

    public Topic() {
        // Default constructor required for calls to DataSnapshot.getValue(Topic.class)
    }

    public Topic(User utilisateur, String date, String titre, Message message1st) {
        this.uid = titre;
        this.user = utilisateur;
        this.date = date;
        this.intitule = titre;
        this.message1 = message1st;
    }

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> title = new HashMap<>();
        title.put("uid", uid);
        title.put("Utilisateur", user.userId);
        title.put("Date", date);
        title.put("Topic", intitule);

        return title;
    }
}
