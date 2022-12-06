package com.example.cinemahub_looknfeel.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Theater implements Parcelable {
    String name;
    String address;
    String phone;
    String website;

    // Parcelables Required describeContents:
    @Override
    public int describeContents() {
        return 0;
    }

    // Parcelables Required writeToParcel:
    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.name);
        parcel.writeString(this.address);
        parcel.writeString(this.phone);
        parcel.writeString(this.website);
    }

    // Parcelables Required Creator:
    public static final Parcelable.Creator<Theater> CREATOR = new Parcelable.Creator<Theater>() {
        @Override
        public Theater createFromParcel(Parcel in) {
            return new Theater(in);
        }
        @Override
        public Theater[] newArray(int size) {
            return new Theater[size];
        }
    };

    // Parcelables Required constructor:
    protected Theater(Parcel in) {
        this.name    = in.readString();
        this.address = in.readString();
        this.phone   = in.readString();
        this.website = in.readString();
    }

    // Constructors:
    public Theater( ){ }
    public Theater(String name, String address, String phone, String website) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.website = website;
    }

    // Getters:
    public String getName() { return name; }
    public String getAddress() { return address; }
    public String getPhone() { return phone; }
    public String getWebsite() { return website; }

    // Setters:
    public void setName(String name) { this.name = name; }
    public void setAddress(String address) { this.address = address; }
    public void setPhone(String phone) { this.phone = phone; }
    public void setWebsite(String website) { this.website = website; }
}
