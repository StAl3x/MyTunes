package mytunes.gui.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import mytunes.be.Song;
import mytunes.bll.SongsLogic;

public class TableViewSongsModel {

    ObservableList<Song> songsList;
    SongsLogic songsLogic;

    public TableViewSongsModel(){
        this.songsList = FXCollections.observableArrayList();
        this.songsLogic = new SongsLogic();
        this.songsList.addAll(songsLogic.getAll());
    }

    public ObservableList<Song> getSongsList(){
        return this.songsList;
    }

    public void addSong(Song song){
        int id = this.songsLogic.add(song);
        song.setID(id);
        this.songsList.add(song);
    }

    public void edit(Song uneditedSong, Song editedSong){
        this.songsLogic.update(editedSong);
        this.songsList.set(songsList.indexOf(uneditedSong), editedSong);
    }

    public void deleteSong(Song song)
    {
        this.songsLogic.delete(song);
        this.songsList.remove(song);
    }
}
