package mytunes.dal;

import mytunes.be.Playlist;
import mytunes.dal.Exceptions.DataException;

import java.util.List;

public interface IPlaylistData {
    List<Playlist> getAll() throws DataException;

    int add(Playlist playlist) throws DataException;

    void update(Playlist playlist)throws DataException;

    void delete(Playlist playlist)throws DataException;
}
