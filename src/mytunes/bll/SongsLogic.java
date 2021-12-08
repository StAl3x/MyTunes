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

    public Song newSong (Song song)
    {
        return songDAO.addSong(song);
    }

    public Song editSong(int songID, Song editedSong)
    {
        return songDAO.editSong(songID, editedSong);
    }

}
