package mytunes.gui.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import mytunes.be.Song;

public class ListViewSongsModel {

    ObservableList<Song> songsList;

    public ListViewSongsModel(){
        songsList = FXCollections.observableArrayList();
    }

    public void addSong(Song song){
        songsList.add(song);
    }

    public ObservableList<Song> getSongsList(){
        return this.songsList;
    }

    public void deleteAllSongs(){
        songsList.clear();
    }
}
