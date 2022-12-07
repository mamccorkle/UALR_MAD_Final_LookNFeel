package com.example.cinemahub_looknfeel.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class Event implements Parcelable {
    Movie        movie;
    Theater      theater;
    Seat         seat;
    int          persons;
    List<String> concessions;
    String       showTime;

    // Parcelables Required describeContents:
    @Override
    public int describeContents() {
        return 0;
    }

    // Parcelables Required writeToParcel:
    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(this.movie, i);
        parcel.writeParcelable(this.theater, i);        // Got to make theater parcelable
        parcel.writeParcelable(this.seat, i);           // Got to make seat parcelable
        parcel.writeInt(this.persons);
        parcel.writeStringList(this.concessions);
        parcel.writeString(this.showTime);
    }

    // Parcelables Required Creator:
    public static final Parcelable.Creator<Event> CREATOR = new Parcelable.Creator<Event>() {
        @Override
        public Event createFromParcel(Parcel in) {
            return new Event(in);
        }
        @Override
        public Event[] newArray(int size) {
            return new Event[size];
        }
    };

    // Parcelables Required constructor:
    protected Event(Parcel in) {
        this.movie       = in.readParcelable(Movie.class.getClassLoader());
        this.theater     = in.readParcelable(Theater.class.getClassLoader());
        this.seat        = in.readParcelable(Seat.class.getClassLoader());
        this.persons     = in.readInt();
        this.concessions = in.createStringArrayList();
        this.showTime    = in.readString();
    }

    // Constructors:
    public Event( ) { }
    public Event(Movie movie) {
        this.movie = movie;
    }
    public Event(Movie movie, String showTime) {
        this.movie = movie;
        this.showTime = showTime;
    }
    public Event(Movie movie, Theater theater, Seat seat, int persons, List<String> concessions, String showTime) {
        this.movie = movie;
        this.theater = theater;
        this.seat = seat;
        this.persons = persons;
        this.concessions = concessions;
        this.showTime = showTime;
    }

    // Getters:
    public Movie getMovie() { return movie; }
    public Theater getTheater() { return theater; }
    public Seat getSeat() { return seat; }
    public int getPersons() { return persons; }
    public List<String> getConcessions() { return concessions; }
    public String getShowTime() { return showTime; }
    public List<String> getAvailableShowTimes() { return this.movie.getShowTimes(); }

    // Setters:
    public void setMovie(Movie movie) { this.movie = movie; }
    public void setTheater(Theater theater) { this.theater = theater; }
    public void setSeat(Seat seat) { this.seat = seat; }
    public void setPersons(int persons) { this.persons = persons; }
    public void setConcessions(List<String> concessions) { this.concessions = concessions; }
    public void setShowTime(String showTime) { this.showTime = showTime; }
}
