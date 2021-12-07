package mytunes.bll;

import mytunes.be.Song;
import mytunes.dal.SongDAO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SongsLogic {

    private SongDAO songDAO;
    private List<Song> allSongs;

    public SongsLogic(){
        this.songDAO = new SongDAO();
        this.allSongs = songDAO.getAllSongs();
    }

    public List<Song> getAllSongs()
    {
        return allSongs;
    }

    public void newSong (Song song)
    {
        try {
            songDAO.addSong(song);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void editSong(Song song)
    {
    }

}
