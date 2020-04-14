package com.yuyuan.androidjava_peluche;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

import java.util.HashMap;
import java.util.Map;

@IgnoreExtraProperties
public class Message {

        public String uid;
        public String user;
        public String date;
        public String topic;
        public String message;

        public Message() {
            // Default constructor required for calls to DataSnapshot.getValue(Message.class)
        }

        public Message(String userId, String date, String sujet, String message) {
            this.user = userId;
            this.date = date;
            this.topic = sujet;
            this.message = message;
        }

        @Exclude
        public Map<String, Object> toMap() {
            HashMap<String, Object> msg = new HashMap<>();
            msg.put("uid", uid);
            msg.put("Utilisateur", user);
            msg.put("Date", date);
            msg.put("Topic", topic);
            msg.put("Message", message);

            return msg;
        }
}
