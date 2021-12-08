package mytunes.gui.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import mytunes.be.Playlist;
import mytunes.be.Song;
import mytunes.bll.PlaylistConnectorLogic;

import java.util.List;

public class ListViewSongsModel {

    ObservableList<Song> songsOnPlaylist;
    PlaylistConnectorLogic playlistConnectorLogic;

    public ListViewSongsModel() {
        playlistConnectorLogic = new PlaylistConnectorLogic();
        songsOnPlaylist = FXCollections.observableArrayList();
    }

    public ObservableList<Song> getSongs()
    {
        return songsOnPlaylist;
    }

    public void addSongsToListView(List<Song> songs)
    {
        songsOnPlaylist.addAll(songs);

    }

    public void deleteSongsFromListView(){
        songsOnPlaylist.remove(0, songsOnPlaylist.size());
    }

    public void addOneSongToListView(Song song)
    {
        songsOnPlaylist.add(song);
    }

    public void addSongToPlaylist(Song song, Playlist playlist)
    {
        playlistConnectorLogic.addSongToPlaylist(song, playlist);
    }
}
