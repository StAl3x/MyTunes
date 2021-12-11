package mytunes.bll;

import mytunes.be.Song;
import mytunes.dal.SongDAO;

import java.util.List;

public class SongsLogic {

    private SongDAO songDAO;

    public SongsLogic() {
        this.songDAO = new SongDAO();
    }

    public List<Song> getAll() {
        return songDAO.getAll();
    }

    public int add(Song song) {
        return songDAO.add(song);
    }

    public void update(Song updatedSong) {
        songDAO.update(updatedSong);
    }

    public void delete(Song song) {
        songDAO.delete(song);
    }
}
