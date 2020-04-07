package com.yuyuan.androidjava_peluche;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FirebaseConnection {

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("message");

    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    /*if (user != null) {
        // User is signed in
    } else {
        // No user is signed in
    }*/



    }
