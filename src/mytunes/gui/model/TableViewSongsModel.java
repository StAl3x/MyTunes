package mytunes.gui.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import mytunes.be.Song;
import mytunes.bll.SongsLogic;

public class TableViewSongsModel {

    ObservableList<Song> songsList;
    SongsLogic songsLogic;

    public TableViewSongsModel(){
        songsLogic = new SongsLogic();
        songsList = FXCollections.observableArrayList();
        songsList.addAll(songsLogic.getAllSongs());
    }

    public void addSong(Song song){
        Song newSong = songsLogic.newSong(song);
        songsList.add(newSong);
    }

    public ObservableList<Song> getSongsList(){
        return this.songsList;
    }

    public void edit(Song selectedSong, Song editedSong){
        Song song = songsLogic.editSong(selectedSong.getSongID(), editedSong);
        songsList.set(songsList.indexOf(selectedSong), song);
    }

    public void deleteSong(Song song)
    {
        songsList.remove(song);
        songsLogic.deleteSong(song);
    }
}
