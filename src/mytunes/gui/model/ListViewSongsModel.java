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

    public void removeAll(){
        songsOnPlaylist.remove(0, songsOnPlaylist.size());
    }

    public void addSong(Song song, Playlist playlist)
    {
        songsOnPlaylistLogic.add(song, playlist);
        songsOnPlaylist.add(song);
    }

    public void delete(Playlist playlist, int index){
        songsOnPlaylistLogic.delete(playlist);
        songsOnPlaylist.remove(index);
    }

    public void select(Playlist playlist) {
        songsOnPlaylist.remove(0, songsOnPlaylist.size());
        List<Song> allSongs = songsOnPlaylistLogic.getAll(playlist);
        songsOnPlaylist.addAll(allSongs);
        playlist.addSongs(allSongs);
    }

    public void removeOccurrence(Song song) {
        songsOnPlaylist.removeAll(song);
    }
}
