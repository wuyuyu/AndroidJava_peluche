package com.yuyuan.androidjava_peluche;

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

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.ViewHolder> {
    private List<Message> messages;
    private DatabaseReference mDatabase;
    private User utilisateur;
    private Message message;

    public MessageAdapter(List<Message> messages) {
        this.messages = messages;
    }

    @NonNull
    @Override
    public MessageAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_message , parent , false);
        return new MessageAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MessageAdapter.ViewHolder holder, final int position) {
        message = messages.get(position);
        holder.date.setText(message.date);
        holder.sujet.setText(message.topic);
        holder.msgTV.setText(message.message);

        mDatabase = FirebaseDatabase.getInstance().getReference();
        DatabaseReference ref = mDatabase.child("utilisateurs").child(message.user);
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
        return messages.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        final TextView date;
        final TextView auteur;
        final TextView sujet;
        final ImageView avatar;
        final TextView msgTV;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            date = itemView.findViewById(R.id.dateMessageTextView);
            auteur = itemView.findViewById(R.id.utilisateurTextView);
            sujet = itemView.findViewById(R.id.topicTextView);
            avatar = itemView.findViewById(R.id.userAvatarImageView);
            msgTV = itemView.findViewById(R.id.messageTextView);
        }
    }
}
