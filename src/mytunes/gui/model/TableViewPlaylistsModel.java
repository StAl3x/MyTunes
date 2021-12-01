package mytunes.gui.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import mytunes.be.Playlist;
import mytunes.be.Song;

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

    public void edit(Playlist selectedPlaylist, Playlist editedPlaylist){
        playlistList.set(playlistList.indexOf(selectedPlaylist), editedPlaylist);
    }


    public void deletePlaylist(Playlist playlist) {
        playlistList.remove(playlist);
    }
}
