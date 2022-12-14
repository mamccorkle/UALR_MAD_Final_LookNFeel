package com.example.cinemahub_looknfeel.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Seat implements Parcelable {
    Boolean handicap;
    Boolean reclinable;
    int id;

    // Parcelables Required describeContents:
    @Override
    public int describeContents() {
        return 0;
    }

    // Parcelables Required writeToParcel:
    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeBoolean(this.handicap);
        parcel.writeBoolean(this.reclinable);
        parcel.writeInt(this.id);
    }

    // Parcelables Required Creator:
    public static final Parcelable.Creator<Seat> CREATOR = new Parcelable.Creator<Seat>() {
        @Override
        public Seat createFromParcel(Parcel in) {
            return new Seat(in);
        }
        @Override
        public Seat[] newArray(int size) {
            return new Seat[size];
        }
    };

    // Parcelables Required constructor:
    protected Seat(Parcel in) {
        this.handicap   = in.readBoolean();
        this.reclinable = in.readBoolean();
        this.id         = in.readInt();
    }

    // Constructors:
    public Seat( ){ }
    public Seat( Boolean handicap, Boolean reclinable, int id )
    {
        this.handicap = handicap;
        this.reclinable = reclinable;
        this.id = id;
    }

    // Getters:
    public Boolean getHandicap() { return handicap; }
    public Boolean getReclinable() { return reclinable; }
    public String  getOptions() {
        if(handicap)
            return "handicap";
        else if(reclinable)
            return "reclinable";
        else
            return "";
    }
    public int getId() { return id; }

    // Setters:
    public void setHandicap(Boolean handicap) { this.handicap = handicap; }
    public void setReclinable(Boolean reclinable) { this.reclinable = reclinable; }
    public void setId(int id) { this.id = id; }
}
