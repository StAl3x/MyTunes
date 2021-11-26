package mytunes.be;

import java.sql.Time;

public class Song {
    private int songID;
    private String title;
    private String artist;
    private SongGenre genre;
    private Time time;
    private String source;

    public Song(String title, String artist, SongGenre genre, String source) {
        this.title = title;
        this.artist = artist;
        this.genre = genre;
        this.source = source;
    }

    public String getTitle(){
        return this.title;
    }

    public String getArtist(){
        return this.artist;
    }

    public String getGenre(){
        return this.genre.toString();
    }

    public String getTime(){
        return this.time.toString();
    }

    public void setSongID(int id){
        this.songID = id;
    }

    public void setTime(Time time){
        this.time = time;
    }

    public void setSource(String source){
        this.source = source;
    }
}