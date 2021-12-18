package mytunes.gui.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import mytunes.be.Playlist;
import mytunes.be.Song;
import mytunes.bll.SongsOnPlaylistLogic;
import mytunes.dal.Exceptions.DataException;

import java.util.Collections;

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

    public void removeAll(){
        songsOnPlaylist.remove(0, songsOnPlaylist.size());
    }

    public void addSong(Song song, Playlist playlist) throws DataException {
        songsOnPlaylistLogic.add(song, playlist);
        songsOnPlaylist.add(song);
    }

    public void delete(Playlist playlist, int index) throws DataException {
        songsOnPlaylistLogic.update(playlist);
        songsOnPlaylist.remove(index);
    }

    public void select(Playlist playlist) {
        songsOnPlaylist.remove(0, songsOnPlaylist.size());
        songsOnPlaylist.addAll(playlist.getSongs());
    }

    public void removeOccurrence(Song song) {
        songsOnPlaylist.removeAll(song);
    }

    public void moveUp(int index, Playlist playlist) throws DataException {
        int swapOther = Math.max(0, index-1);
        Collections.swap(this.songsOnPlaylist, index, swapOther);
        this.songsOnPlaylistLogic.moveUp(index, swapOther, playlist);
    }

    public void moveDown(int index, Playlist playlist) throws DataException {
        int swapOther = Math.min(playlist.getSongs().size()-1, index+1);
        Collections.swap(this.songsOnPlaylist, index, swapOther);
        this.songsOnPlaylistLogic.moveDown(index, swapOther, playlist);
    }
}
