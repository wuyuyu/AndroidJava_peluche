package com.yuyuan.androidjava_peluche;

import androidx.annotation.NonNull;

import com.google.firebase.database.IgnoreExtraProperties;


@IgnoreExtraProperties
public class Topic {
    public String uid;
    public String user;
    public String date;
    public String intitule;


    public Topic() {
        // Default constructor required for calls to DataSnapshot.getValue(Topic.class)
    }

    public Topic(User utilisateur, String date, String titre, Message message1st) {
        this.uid = titre;
        this.user = utilisateur.userId;
        this.date = date;
        this.intitule = titre;
    }

    @NonNull
    @Override
    public String toString() {
        return(this.uid + "/" + this.date );
    }
}
