package mytunes.be;

import java.util.ArrayList;
import java.util.List;

public class Playlist {
    private int playlistID;
    private String name;
    private List<Song> songs = new ArrayList<>();


    public Playlist(String name){this.name=name;}

    public Playlist(String name, List<Song> songs){
        this.playlistID= playlistID;
        this.name = name;
        this.songs = songs;
    }



    public void removeSong(Song song){
        songs.remove(song);
    }

    public void addSong(Song song){
        songs.add(song);
    }

    public String getName(){
        return this.name;
    }

    public List<Song> getSongs() {
        return this.songs;
    }

    public void setID(int id) {
        this.playlistID = id;
    }
        public int getID() {
        return playlistID;
    }
}
