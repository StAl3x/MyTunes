package mytunes.gui.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import mytunes.be.Playlist;
import mytunes.be.Song;
import mytunes.bll.PlaylistLogic;
import java.util.List;

public class TableViewPlaylistsModel {

    ObservableList<Playlist> playlistList;
    PlaylistLogic playlistLogic;

    public TableViewPlaylistsModel()
    {
        playlistLogic = new PlaylistLogic();
        playlistList = FXCollections.observableArrayList();
        playlistList.addAll(playlistLogic.getAllPlaylists());
    }

    private void seedPlaylists(List<Playlist> list)
    {
        for(Playlist p: list)
        {
            playlistLogic.seedPlaylist(p);
        }
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
        List<Song> songList = selectedPlaylist.getSongs();
        Playlist playlist = playlistLogic.editPlaylist(selectedPlaylist.getPlaylistID(), editedPlaylist);
        playlist.addSongList(songList);
        playlistList.set(playlistList.indexOf(selectedPlaylist), playlist);
    }


    public void deletePlaylist(Playlist playlist) {
        playlistList.remove(playlist);
        playlistLogic.deletePlaylist(playlist);
    }
}

