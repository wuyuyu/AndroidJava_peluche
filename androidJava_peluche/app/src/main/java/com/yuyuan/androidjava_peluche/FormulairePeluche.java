package com.yuyuan.androidjava_peluche;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@IgnoreExtraProperties

public class FormulairePeluche {


        public String uid;
        public String reponse1;
        public ArrayList reponse2;
        public String reponse3;
        public String reponse3b;
        public String reponse4;

        public FormulairePeluche() {
            // Default constructor required for calls to DataSnapshot.getValue(Post.class)
        }

        public FormulairePeluche(String answer1, ArrayList answer2, String answer3, String answer3b, String answer4) {
            //this.uid = uid;
            this.reponse1 = answer1;
            this.reponse2 = answer2;
            this.reponse3 = answer3;
            this.reponse3b = answer3b;
            this.reponse4 = answer4;
        }

        @Exclude
        public Map<String, Object> toMap() {
            HashMap<String, Object> reponse = new HashMap<>();
            reponse.put("uid", uid);
            reponse.put("reponse1", reponse1);
            reponse.put("reponse2", reponse2);
            reponse.put("reponse3", reponse3);
            reponse.put("reponse3b", reponse3b);
            reponse.put("reponse4", reponse4);

            return reponse;
        }


}
