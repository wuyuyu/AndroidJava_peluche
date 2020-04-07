package com.yuyuan.androidjava_peluche;

import android.os.Parcel;
import android.os.Parcelable;

class Comptine implements Parcelable {

    public final String imageComptine;
    public final String nom;
    public final String date;
    public final String divers;


    public Comptine(String imageComptine, String nom, String date, String divers) {
        this.imageComptine = imageComptine;
        this.nom = nom;
        this.date = date;
        this.divers = divers;
    }


    protected Comptine(Parcel in) {
        imageComptine = in.readString();
        nom = in.readString();
        date = in.readString();
        divers = in.readString();
    }

    public static final Creator<Comptine> CREATOR = new Creator<Comptine>() {
        @Override
        public Comptine createFromParcel(Parcel in) {
            return new Comptine(in);
        }

        @Override
        public Comptine[] newArray(int size) {
            return new Comptine[size];
        }
    };

    @Override
    public String toString() {
        return "Comptine{" +
                "imageComptine='" + imageComptine + '\'' +
                ", nom='" + nom + '\'' +
                ", date='" + date + '\'' +
                ", divers='" + divers + '\'' +
                '}';
    }

    protected Comptine(Parcel in, String date, String divers) {
        imageComptine = in.readString();
        nom = in.readString();
        this.date = date;
        this.divers = divers;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(imageComptine);
        dest.writeString(nom);
        dest.writeString(date);
        dest.writeString(divers);
    }

    public String getImageComptine() {
        return imageComptine;
    }

    public String getNom() {
        return nom;
    }

    public String getDate() {
        return date;
    }

    public String getDivers() {
        return divers;
    }

    public static Creator<Comptine> getCREATOR() {
        return CREATOR;
    }
}