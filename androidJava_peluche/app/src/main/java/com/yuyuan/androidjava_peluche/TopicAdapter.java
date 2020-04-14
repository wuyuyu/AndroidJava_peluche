package com.yuyuan.androidjava_peluche;

import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class TopicAdapter extends RecyclerView.Adapter<TopicAdapter.ViewHolder> {

    private List<Topic> topics;
    private DatabaseReference mDatabase;
    private User utilisateur;

    public TopicAdapter(List<Topic> topics) {
        this.topics = topics;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_topic , parent , false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        Topic topic = topics.get(position);

        holder.date.setText(topic.date);
        holder.sujet.setText(topic.intitule);

        mDatabase = FirebaseDatabase.getInstance().getReference();
        DatabaseReference ref = mDatabase.child("utilisateurs").child(topic.user);
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                utilisateur = dataSnapshot.getValue(User.class);
                holder.auteur.setText(utilisateur.userNickName);
                if (utilisateur.userAvatar != null) {
                    switch (utilisateur.userAvatar) {
                        case "chat":
                            holder.avatar.setImageResource(R.drawable.ic_chat_30dp);
                            break;

                        case "chien":
                            holder.avatar.setImageResource(R.drawable.ic_chien_30dp);
                            break;

                        case "cochon":
                            holder.avatar.setImageResource(R.drawable.ic_cochon_30dp);
                            break;

                        case "hamster":
                            holder.avatar.setImageResource(R.drawable.ic_hamster_30dp);
                            break;

                        case "hibou":
                            holder.avatar.setImageResource(R.drawable.ic_hibou_30dp);
                            break;

                        case "lion":
                            holder.avatar.setImageResource(R.drawable.ic_lion_30dp);
                            break;

                        case "panda":
                            holder.avatar.setImageResource(R.drawable.ic_panda_30dp);
                            break;

                        case "singe":
                            holder.avatar.setImageResource(R.drawable.ic_singe_30dp);
                            break;

                        case "souris":
                            holder.avatar.setImageResource(R.drawable.ic_souris_30dp);
                            break;
                    }
                }else {
                    holder.avatar.setImageResource(R.drawable.ic_panda_30dp);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return topics.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        final TextView date;
        final TextView auteur;
        final TextView sujet;
        final ImageView avatar;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            date = itemView.findViewById(R.id.dateTextView);
            auteur = itemView.findViewById(R.id.auteurTextView);
            sujet = itemView.findViewById(R.id.sujetTextView);
            avatar = itemView.findViewById(R.id.avatarImageView);
        }
    }
}
