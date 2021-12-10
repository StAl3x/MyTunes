package mytunes.gui.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import mytunes.be.Song;
import mytunes.bll.SongsLogic;

public class TableViewSongsModel {

    ObservableList<Song> songsList;
    SongsLogic songsLogic;

    public TableViewSongsModel(){
        songsList = FXCollections.observableArrayList();
        songsLogic = new SongsLogic();
        songsList.addAll(songsLogic.getAll());
    }

    public void addSong(Song song){
        songsLogic.add(song);
        songsList.add(song);
    }

    public ObservableList<Song> getSongsList(){
        return this.songsList;
    }

    public void edit(Song uneditedSong, Song editedSong){
        songsLogic.update(editedSong);
        songsList.set(songsList.indexOf(uneditedSong), editedSong);
        /*int id = songsLogic.requestID(uneditedSong);
        songsLogic.editSong(songsList.indexOf(selectedSong), editedSong);
        songsList.set(songsList.indexOf(selectedSong), editedSong);*/
    }

    public void deleteSong(Song song)
    {
        songsLogic.delete(song);
        songsList.remove(song);
    }
}
