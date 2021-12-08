package mytunes.gui.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import mytunes.be.Playlist;
import mytunes.bll.PlaylistLogic;

public class TableViewPlaylistsModel {

    ObservableList<Playlist> playlistList;
    PlaylistLogic playlistLogic;

    public TableViewPlaylistsModel()
    {
        playlistLogic = new PlaylistLogic();
        playlistList = FXCollections.observableArrayList();
        playlistList.addAll(playlistLogic.getAllPlaylists());
    }

    public void addPlaylist(Playlist playlist)
    {
        playlistList.add(playlistLogic.newPlaylist(playlist));
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