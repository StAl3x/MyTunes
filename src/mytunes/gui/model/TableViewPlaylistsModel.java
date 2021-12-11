package mytunes.gui.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import mytunes.be.Playlist;
import mytunes.be.Song;
import mytunes.bll.PlaylistsLogic;

public class TableViewPlaylistsModel {

    ObservableList<Playlist> playlistList;
    PlaylistsLogic playlistsLogic;

    public TableViewPlaylistsModel() {
        playlistList = FXCollections.observableArrayList();
        playlistsLogic = new PlaylistsLogic();
        refresh();
    }

    public ObservableList<Playlist> getPlaylistList() {
        return this.playlistList;
    }

    public void addPlaylist(Playlist playlist) {
        int id = playlistsLogic.add(playlist);
        playlist.setID(id);
        playlistList.add(playlist);
    }

    public void edit(Playlist uneditedPlaylist, Playlist editedPlaylist) {
        playlistsLogic.update(editedPlaylist);
        playlistList.set(playlistList.indexOf(uneditedPlaylist), editedPlaylist);
    }


    public void delete(Playlist playlist) {
        playlistsLogic.delete(playlist);
        playlistList.remove(playlist);
    }

    public void update(int index, Playlist playlist) {
        this.playlistList.set(index, playlist);
    }

    public void deleteAll(){
        this.playlistList.remove(0, this.playlistList.size());
    }

    public void refresh() {
        this.deleteAll();
        this.playlistList.addAll(playlistsLogic.getAll());
    }
}