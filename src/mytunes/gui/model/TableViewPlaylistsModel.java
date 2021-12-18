package mytunes.gui.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import mytunes.be.Playlist;
import mytunes.bll.PlaylistsLogic;
import mytunes.dal.Exceptions.DataException;

public class TableViewPlaylistsModel {

    ObservableList<Playlist> playlistList;
    PlaylistsLogic playlistsLogic;

    public TableViewPlaylistsModel() throws DataException {
        playlistList = FXCollections.observableArrayList();
        playlistsLogic = new PlaylistsLogic();
        refresh();
    }

    public ObservableList<Playlist> getPlaylistList() {
        return this.playlistList;
    }

    public void addPlaylist(Playlist playlist) throws DataException {
        int id = playlistsLogic.add(playlist);
        playlist.setID(id);
        playlistList.add(playlist);
    }

    public void edit(Playlist uneditedPlaylist, Playlist editedPlaylist) throws DataException {
        playlistsLogic.update(editedPlaylist);
        playlistList.set(playlistList.indexOf(uneditedPlaylist), editedPlaylist);
    }


    public void delete(Playlist playlist) throws DataException {
        playlistsLogic.delete(playlist);
        playlistList.remove(playlist);
    }

    public void update(int index, Playlist playlist) {
        this.playlistList.set(index, playlist);
    }

    public void deleteAll(){
        this.playlistList.remove(0, this.playlistList.size());
    }

    public void refresh() throws DataException {
        this.deleteAll();
        this.playlistList.addAll(playlistsLogic.getAll());
    }
}