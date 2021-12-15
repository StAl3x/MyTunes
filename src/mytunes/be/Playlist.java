package mytunes.be;

import java.util.ArrayList;
import java.util.List;

public class Playlist {
    private int ID;
    private String name;
    private List<Song> songs;
    private int totalSongs;
    private int totalTime;
    private int playingIndex;
    private int tempIndex;

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

    public void setTempIndex(int index) {
        this.tempIndex = index;
    }

    private void play(int index){
        this.playingIndex = index;
        this.songs.get(index).play();
    }

    public void play() {
        this.playingIndex = this.tempIndex;
        this.songs.get(this.tempIndex).play();
    }

    public void next() {
        if(this.playingIndex+1 < this.songs.size()){
            this.stop();
            this.playingIndex++;
            this.play(this.playingIndex);
        }
    }

    public void previous() {
        if(this.playingIndex-1 >= 0){
            this.stop();
            this.playingIndex--;
            this.play(this.playingIndex);
        }
    }

    public void stop() {
        getPlayingSong().stop();
    }

    public void changeVolume(double value) {
        getPlayingSong().setVolume(value);
    }

    private Song getPlayingSong(){
        return this.songs.get(this.playingIndex);
    }
}
