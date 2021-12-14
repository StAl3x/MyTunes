package mytunes.be;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;
import java.nio.file.Path;

public class Song {

    private int ID;
    private String title;
    private String artist;
    private SongGenre genre;
    private int time;
    private String source;
    private Media songMedia;
    private MediaPlayer mediaPlayer;

    public Song(String title, String artist, SongGenre genre, int time, String source) {
        this.title = title;
        this.artist = artist;
        this.genre = genre;
        this.time = time;
        this.source = source;

        File file = new File(this.source);
        if(file.exists()){
            this.songMedia = new Media(file.toURI().toString());
            this.mediaPlayer = new MediaPlayer(this.songMedia);
        }
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

    public String getTimeString() {
        return ""+this.time;
    }

    public int getTime(){
        return this.time;
    }

    public String getSource() {
        return this.source;
    }

    public int getID(){return this.ID; }

    public void setID(int id) {
        this.ID = id;
    }

    public void setTime(int time) {
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

    public void play(){
        mediaPlayer.play();
    }

    public void setVolume(double volume){
        mediaPlayer.setVolume(volume);
    }

    @Override
    public String toString() {
        return "%d\n%s: %s\n%s\t%d\n%s".formatted(
                this.ID,
                this.artist,
                this.title,
                this.genre.toString(),
                this.time,
                this.source
        );
    }


}