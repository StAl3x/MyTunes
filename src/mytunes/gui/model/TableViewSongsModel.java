package mytunes.gui.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import mytunes.be.Song;

public class TableViewSongsModel {

    ObservableList<Song> songsList;

    public TableViewSongsModel(){
        songsList = FXCollections.observableArrayList();
    }
}
