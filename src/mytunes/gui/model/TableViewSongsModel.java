package mytunes.gui.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import mytunes.be.Song;

public class TableViewSongsModel {

    ObservableList<Song> songsList;

    public TableViewSongsModel(){
        songsList = FXCollections.observableArrayList();
    }

    public void addSong(Song song){
        songsList.add(song);
    }

    public ObservableList<Song> getSongsList(){
        return this.songsList;
    }
}
