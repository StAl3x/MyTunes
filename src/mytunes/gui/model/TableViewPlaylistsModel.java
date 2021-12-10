package mytunes.gui.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import mytunes.be.Playlist;
import mytunes.bll.PlaylistsLogic;

public class TableViewPlaylistsModel {

    ObservableList<Playlist> playlistList;
    PlaylistsLogic playlistsLogic;

    public TableViewPlaylistsModel() {
        playlistList = FXCollections.observableArrayList();
        playlistsLogic = new PlaylistsLogic();
        playlistList.addAll(playlistsLogic.getAll());
    }

    public ObservableList<Playlist> getPlaylistList() {
        return this.playlistList;
    }

    public void addPlaylist(Playlist playlist) {
        playlistsLogic.add(playlist);
        playlistList.add(playlist);
    }

    public void edit(Playlist uneditedPlaylist, Playlist editedPlaylist) {
        playlistsLogic.update(editedPlaylist);
        playlistList.set(playlistList.indexOf(uneditedPlaylist), editedPlaylist);
    }


    public void deletePlaylist(Playlist playlist) {
        playlistsLogic.delete(playlist);
        playlistList.remove(playlist);
    }
}