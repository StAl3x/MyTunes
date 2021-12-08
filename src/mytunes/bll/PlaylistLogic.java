package mytunes.bll;

import mytunes.be.Playlist;
import mytunes.dal.PlaylistDAO;

import java.util.List;

public class PlaylistLogic {

    private PlaylistDAO playlistDAO;

    public PlaylistLogic()
    {
        this.playlistDAO = new PlaylistDAO();
    }

    public List<Playlist> getAllPlaylists() {
       return playlistDAO.getAllPlaylists();
    }

    public Playlist newPlaylist(Playlist playlist)
    {
        String title = playlist.getName();
        return playlistDAO.addPlaylist(title);
    }
}
