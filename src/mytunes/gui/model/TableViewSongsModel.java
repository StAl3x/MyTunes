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
        songsList.add(song);
        songsLogic.newSong(song);
    }

    public ObservableList<Song> getSongsList(){
        return this.songsList;
    }

    public void edit(Song selectedSong, Song editedSong){
        songsList.set(songsList.indexOf(selectedSong), editedSong);
    }

    public void deleteSong(Song song)
    {
        songsList.remove(song);
    }
}
