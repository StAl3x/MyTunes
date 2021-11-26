package be;

import javafx.scene.media.MediaPlayer;

import java.sql.Time;

public class Song {
    private int songID;
    private String title;
    private String artist;
    private SongGenre genre;
    private Time time;
    private String source;

    public Song(int songID, String title, String artist, SongGenre genre, Time time, String source){
        this.songID = songID;
        this.title = title;
        this.artist = artist;
        this.genre = genre;
        this.time = time;
        this.source = source;
    }

    public String play(){
       return  source;
    }


    //getter and setters
    public int getSongID() {
        return songID;
    }

    public String getTitle() {
        return title;
    }

    public String getArtist() {
        return artist;
    }

    public SongGenre getGenre() {
        return genre;
    }

    public Time getTime() {
        return time;
    }
}
