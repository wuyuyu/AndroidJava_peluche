package com.yuyuan.androidjava_peluche;

import androidx.annotation.NonNull;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class User {

    public String userId;
    public String userAvatar;
    public String userLastName;
    public String userFirstName;
    public String userNickName;
    public String userChildAge;

    public User() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public User(String id, String avatar, String nom, String prenom, String pseudo, String age) {
        this.userId = id;
        this.userAvatar = avatar;
        this.userLastName = nom;
        this.userFirstName = prenom;
        this.userNickName = pseudo;
        this.userChildAge = age;
    }

    @NonNull
    @Override
    public String toString() {
        return ("id = " + this.userId + ", avatar = " + this.userAvatar + ", nom = "
                + this.userLastName + ", prenom = " + this.userFirstName + ", pseudo = "
                + this.userNickName + ", age = " + this.userChildAge);

    }
}
