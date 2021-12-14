package mytunes.gui.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import mytunes.be.Song;
import mytunes.bll.SongsLogic;
import mytunes.dal.Exceptions.DataException;

public class TableViewSongsModel {

    ObservableList<Song> songsList;
    SongsLogic songsLogic;

    public TableViewSongsModel() throws DataException {
        this.songsList = FXCollections.observableArrayList();
        this.songsLogic = new SongsLogic();
        this.songsList.addAll(songsLogic.getAll());
    }

    public ObservableList<Song> getSongsList(){
        return this.songsList;
    }

    public void addSong(Song song) throws DataException {
        int id = this.songsLogic.add(song);
        song.setID(id);
        this.songsList.add(song);
    }

    public void edit(Song uneditedSong, Song editedSong) throws DataException {
        this.songsLogic.update(editedSong);
        this.songsList.set(songsList.indexOf(uneditedSong), editedSong);
    }

    public void deleteSong(Song song) throws DataException {
        this.songsLogic.delete(song);
        this.songsList.remove(song);
    }

    public void filter(String query) throws DataException {
        this.songsList.remove(0, this.songsList.size());
        this.songsList.addAll(this.songsLogic.filter(query));
    }
}
