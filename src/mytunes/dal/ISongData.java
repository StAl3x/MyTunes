package mytunes.dal;

import mytunes.be.Song;
import mytunes.dal.Exceptions.DataException;

import java.util.List;

public interface ISongData {
    List<Song> getAll() throws DataException;

    int create(Song song) throws DataException;

    void update(Song song) throws DataException;

    void delete(Song song) throws DataException;
}
