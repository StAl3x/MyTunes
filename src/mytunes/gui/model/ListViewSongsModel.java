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

    public void addPlaylistToListView(List<Song> songs)
    {
        songsOnPlaylist.addAll(songs);
    }

}
