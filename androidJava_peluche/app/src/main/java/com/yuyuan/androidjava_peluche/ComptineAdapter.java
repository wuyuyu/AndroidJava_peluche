package com.yuyuan.androidjava_peluche;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class ComptineAdapter extends RecyclerView.Adapter<ComptineAdapter.ViewHolder> implements View.OnClickListener {
    private List<Comptine> comptineList;


    public ComptineAdapter(List<Comptine> comptineList) {
        this.comptineList = comptineList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_comptine, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Comptine comptine = comptineList.get(position);
        Picasso.get().load(comptineList.get(position).imageComptine).into(holder.imageComptine);
        holder.date.setText(comptine.getDate());
        holder.nom.setText(comptine.getNom());
        holder.divers.setText(comptine.getDivers());
        holder.itemView.setTag(comptine);
        holder.imageButtonPlay.setOnClickListener(this);
    }

    @Override
    public int getItemCount() {
        return comptineList.size();
    }

    @Override
    public void onClick(View v) {
        Comptine comptine = (Comptine) v.getTag();
        Context context = v.getContext();
        Intent intent = new Intent(context, UneComptineActivity.class);
        intent.putExtra("comptine",comptine);
        context.startActivity(intent);
        System.out.println("comptine adaptater:" +comptine);
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        final ImageView imageComptine;
        final TextView nom;
        final TextView date;
        final TextView divers;
        final ImageButton imageButtonPlay;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageComptine = itemView.findViewById(R.id.imageViewComptineImage);
            nom = itemView.findViewById(R.id.textViewNomComptine);
            date = itemView.findViewById(R.id.textViewDate);
            divers = itemView.findViewById(R.id.textViewDivers);
            imageButtonPlay = itemView.findViewById(R.id.imageButtonPlay);
        }
    }
}
