package mytunes.bll;

import mytunes.be.Playlist;
import mytunes.be.Song;
import mytunes.dal.SongsOnPlaylistDAO;

import java.util.List;

public class SongsOnPlaylistLogic {

    private SongsOnPlaylistDAO songsOnPlaylistDAO;

    public SongsOnPlaylistLogic(){
        this.songsOnPlaylistDAO = new SongsOnPlaylistDAO();
    }

    public List<Song> getAll(Playlist playlist){
        return songsOnPlaylistDAO.getAll(playlist);
    }

    public void add(Song song, Playlist playlist){
        songsOnPlaylistDAO.add(song, playlist);
    }

    public void update(Playlist playlist){
        songsOnPlaylistDAO.update(playlist);
    }

    public void delete(Song song, Playlist playlist){
        songsOnPlaylistDAO.delete(song, playlist);
    }
}
