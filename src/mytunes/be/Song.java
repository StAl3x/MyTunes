package mytunes.be;

import java.sql.Time;

public class Song {
    private int songID;
    private String title;
    private String artist;
    private SongGenre genre;
    private String time;
    private String source;

    public Song(String title, String artist, SongGenre genre, String time, String source) {
        this.title = title;
        this.artist = artist;
        this.genre = genre;
        this.time = time;
        this.source = source;
    }

    public String getTitle() {
        return this.title;
    }

    public String getArtist() {
        return this.artist;
    }

    public SongGenre getGenre() {
        return this.genre;
    }

    public String getTime() {
        return this.time;
    }

    public String getSource() {
        return this.source;
    }

    public int getID(){return this.songID; }

    public void setID(int id) {
        this.songID = id;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public void setArtist(String artist){
        this.artist = artist;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public void setGenre(SongGenre genre){
        this.genre = genre;
    }

    @Override
    public String toString() {
        return "%d\n%s: %s\n%s\t%s\n%s".formatted(
                this.songID,
                this.artist,
                this.title,
                this.genre.toString(),
                this.time,
                this.source
        );
    }


}