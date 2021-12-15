package mytunes.be;

import java.util.ArrayList;
import java.util.List;

public class Playlist {
    private int ID;
    private String name;
    private List<Song> songs;
    private int totalSongs;
    private int totalTime;
    private int playingIndex = 0;

    public Playlist(String name) {
        this.name = name;
        this.songs = new ArrayList<>();
        this.totalSongs = 0;
        this.totalTime = 0;
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

    public String getTotalSongs(){
        return ""+this.totalSongs;
    }
    public String getTotalTime(){
        return ""+this.totalTime;
    }

    public void remove(Song song) {
        this.songs.remove(song);
        this.totalTime -= song.getTime();
        this.totalSongs--;
    }

    public void remove(int index){
        this.totalTime -= this.songs.get(index).getTime();
        this.songs.remove(index);
        this.totalSongs--;
    }

    public void addSong(Song song) {
        this.songs.add(song);
        this.totalSongs++;
        this.totalTime += song.getTime();
    }

    public void addSongs(List<Song> songs) {
        this.songs = songs;
        this.totalSongs = songs.size();
        this.totalTime = 0;
        for (Song song :
                songs) {
            this.totalTime += song.getTime();
        }
    }

    public List<Song> getSongs() {
        return this.songs;
    }

    public void playFromIndex(int index){
        this.songs.get(index).play();
    }

    public void selectIndex(int songIndex) {
        this.playingIndex = songIndex;
    }
}
