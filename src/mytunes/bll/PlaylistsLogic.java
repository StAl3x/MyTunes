package mytunes.bll;

import mytunes.be.Playlist;
import mytunes.dal.PlaylistDAO;

import java.util.List;

public class PlaylistsLogic {

    private PlaylistDAO playlistDAO;

    public PlaylistsLogic() {
        this.playlistDAO = new PlaylistDAO();
    }

    public List<Playlist> getAll() {
        return playlistDAO.getAll();
    }

    public void add(Playlist playlist) {
        int id = playlistDAO.add(playlist);
        playlist.setID(id);
    }

    public void update(Playlist updatedPlaylist) {
        playlistDAO.update(updatedPlaylist);
    }

    public void delete(Playlist playlist) {
        playlistDAO.delete(playlist);
    }
}
