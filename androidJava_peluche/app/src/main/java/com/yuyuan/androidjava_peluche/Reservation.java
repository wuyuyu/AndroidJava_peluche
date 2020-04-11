package com.yuyuan.androidjava_peluche;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@IgnoreExtraProperties

public class Reservation {

        public String uid;
        public String userMail;
        public int quantite;
        public String livraison;
        public String prixTotal;

        public Reservation() {
            // Default constructor required for calls to DataSnapshot.getValue(Reservation.class)
        }

        public Reservation(String email, int qte, String deliver, String prix) {
            this.userMail = email;
            this.quantite = qte;
            this.livraison = deliver;
            this.prixTotal = prix;
        }

        @Exclude
        public Map<String, Object> toMap() {
            HashMap<String, Object> resa = new HashMap<>();
            resa.put("uid", uid);
            resa.put("Email", userMail);
            resa.put("Quantité", quantite);
            resa.put("Mode de livraison", livraison);
            resa.put("Prix total", prixTotal);

            return resa;
        }
}
