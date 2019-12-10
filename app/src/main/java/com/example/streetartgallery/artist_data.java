package com.example.streetartgallery;

public class artist_data {

    String artist_fname,artist_lname;
    int artist_id;

    public artist_data(int artist_id, String artist_fname, String artist_lname) {
        this.artist_fname = artist_fname;
        this.artist_lname = artist_lname;
        this.artist_id = artist_id;
    }

    public String getArtist_fname() {
        return artist_fname;
    }

    public String getArtist_lname() {
        return artist_lname;
    }

    public int getArtist_id() {
        return artist_id;
    }
}

