package mytunes.gui.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import mytunes.be.Playlist;
import mytunes.be.Song;
import mytunes.bll.SongsOnPlaylistLogic;

import java.util.List;

public class ListViewSongsModel {

    ObservableList<Song> songsOnPlaylist;
    SongsOnPlaylistLogic songsOnPlaylistLogic;

    public ListViewSongsModel()
    {
        songsOnPlaylist = FXCollections.observableArrayList();
        songsOnPlaylistLogic = new SongsOnPlaylistLogic();
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

    public void addSong(Song song, Playlist playlist)
    {
        songsOnPlaylistLogic.add(song, playlist);
        songsOnPlaylist.add(song);
    }

    public void delete(Song song, Playlist playlist){
        songsOnPlaylistLogic.delete(song, playlist);
        songsOnPlaylist.remove(song);
    }

    public void select(Playlist playlist) {
        songsOnPlaylist.remove(0, songsOnPlaylist.size());
        songsOnPlaylist.addAll(songsOnPlaylistLogic.getAll(playlist));
    }
}
