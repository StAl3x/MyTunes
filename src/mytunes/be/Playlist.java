package mytunes.be;

import java.util.ArrayList;
import java.util.List;

public class Playlist {
    private int ID;
    private String name;
    private List<Song> songs;


    public Playlist(String name) {
        this.name = name;
        this.songs = new ArrayList<>();
    }

    public int getID() {
        return ID;
    }

    public void setID(int id) {
        this.ID = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name){
        this.name = name;
    }

    public void remove(Song song) {
        songs.remove(song);
    }

    public void remove(int index){
        songs.remove(index);
    }

    public void addSong(Song song) {
        songs.add(song);
    }

    public void addSongs(List<Song> songs) {
        this.songs = songs;
    }

    public List<Song> getSongs() {
        return this.songs;
    }
}
