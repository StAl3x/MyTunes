package mytunes.dal;

import mytunes.be.Playlist;
import mytunes.be.Song;
import mytunes.dal.Exceptions.DataException;

import java.util.List;

public interface ISongOnPlaylistData {

    List<Song> getAll(Playlist playlist) throws DataException;

    void add(Song song, Playlist playlist) throws DataException;

    void updatePlaylist(Playlist playlist) throws DataException;
}
