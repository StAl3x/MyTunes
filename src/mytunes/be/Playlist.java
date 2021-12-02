package mytunes.be;

import java.util.List;

public class Playlist {
    private int id;
    private String name;
    private List<Song> songs;

    public Playlist(int id, String name, List<Song> songs){
        this.id= id;
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
}
