package be;

import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class PlayList {
    private String name;
    private List<Song> songs = new ArrayList<>();
    private String totalTime;
    final DateFormat dt = new SimpleDateFormat("HH:mm:ss");

    public PlayList(String name){
        this.name = name;
    }

    public List<Song> toQueue(Song song){
        songs.remove(song);
        songs.add(0, song);
        return songs;
    }

    public String estimateTime(){
        String estimatedTime = "";
        for (Song n : songs){
            estimatedTime += n.getTime().toString();
        }
        totalTime = dt.format(estimatedTime);
        return totalTime;
    }

    public void removeSong(Song song){
        songs.remove(song);
    }

    public void addSong(Song song){
        songs.add(song);
    }

    //getters
    public String getName() {
        return name;
    }

    public List<Song> getSongs() {
        return songs;
    }
}
