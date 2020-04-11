package com.yuyuan.androidjava_peluche;

import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class TopicAdapter extends RecyclerView.Adapter<TopicAdapter.ViewHolder> {

    private List<Topic> topics;

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
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Topic topic = topics.get(position);
        int idAvatar = 700143;
        User utilisateur = topic.user;
        switch (utilisateur.userAvatar){
            case "chat":
                idAvatar = 700046;
                break;

            case "chien":
                idAvatar = 700010;
                break;

            case "cochon":
                idAvatar = 700144;
                break;

            case "hamster":
                idAvatar = 700102;
                break;

            case "hibou":
                idAvatar = 700028;
                break;

            case "lion":
                idAvatar = 700108;
                break;

            case "panda":
                idAvatar = 700143;
                break;

            case "singe":
                idAvatar = 700124;
                break;

            case "souris":
                idAvatar = 700052;
                break;
        }
        holder.date.setText(topic.date);
        holder.auteur.setText(topic.user.userNickName);
        holder.sujet.setText(topic.intitule);
        holder.avatar.setImageResource(idAvatar);
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
