package mytunes.bll;

import mytunes.be.Song;
import mytunes.dal.SongDAO;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

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

    public List<Song> filter(String query) {
        List<Song> filtered = new ArrayList<>();
        List<Song> allSongs = this.getAll();
        for (Song song :
                allSongs) {
            if (song.getArtist().toLowerCase().contains(query) ||
                song.getTitle().toLowerCase().contains(query) ||
                song.getGenre().toString().toLowerCase().contains(query)) {
                filtered.add(song);
            }
        }
        return filtered.size() > 0 ? filtered : getAll();
    }
}
