package mytunes.gui.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import mytunes.be.Playlist;
import mytunes.be.Song;

import java.util.List;

public class ListViewSongsModel {

    ObservableList<Song> songsOnPlaylist;

    public ListViewSongsModel()
    {
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

}
