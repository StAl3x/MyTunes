package mytunes.be;

import java.util.ArrayList;
import java.util.List;

public class Playlist {
    private String name;
    private List<Song> songs = new ArrayList<>();

    public Playlist(String name)
    {
        this.name = name;
    }

    public Playlist(String name, List<Song> songs){
        this.name = name;
        this.songs = songs;
    }

    public void removeSong(Song song){
        songs.remove(song);
    }

    public void addSong(Song song){
        Song newSong = new Song(song.getTitle(), song.getArtist(), song.getGenre(), song.getSource());
        songs.add(newSong);
    }

    public String getName(){
        return this.name;
    }

    public List<Song> getSongs()
    {
        return this.songs;
    }
}
