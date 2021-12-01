package mytunes.gui.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import mytunes.be.Playlist;

public class TableViewPlaylistsModel {

    ObservableList<Playlist> playlistList;

    public TableViewPlaylistsModel()
    {
        playlistList = FXCollections.observableArrayList();
    }

    public void addPlaylist(Playlist playlist)
    {
        playlistList.add(playlist);
    }

    public ObservableList<Playlist> getPlaylistList()
    {
        return this.playlistList;
    }



}
