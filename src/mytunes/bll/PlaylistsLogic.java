package mytunes.bll;

import mytunes.be.Playlist;
import mytunes.be.Song;
import mytunes.dal.Exceptions.DataException;
import mytunes.dal.PlaylistDAO;
import mytunes.dal.SongsOnPlaylistDAO;

import java.util.List;

public class PlaylistsLogic {

    private PlaylistDAO playlistDAO;
    private SongsOnPlaylistDAO songsOnPlaylistDAO;

    public PlaylistsLogic() {
        this.playlistDAO = new PlaylistDAO();
        this.songsOnPlaylistDAO = new SongsOnPlaylistDAO();
    }

    public List<Playlist> getAll() throws DataException {
        List<Playlist> allPlaylists = playlistDAO.getAll();
        for (Playlist playlist :
                allPlaylists) {
            List<Song> songsOnPlaylist = songsOnPlaylistDAO.getAll(playlist);
            playlist.addSongs(songsOnPlaylist);
        }
        return allPlaylists;
    }

    public int add(Playlist playlist) throws DataException {
        return playlistDAO.add(playlist);
    }

    public void update(Playlist updatedPlaylist) throws DataException {
        playlistDAO.update(updatedPlaylist);
    }

    public void delete(Playlist playlist) throws DataException {
        playlistDAO.delete(playlist);
    }
}
