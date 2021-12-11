package mytunes.bll;

import mytunes.be.Playlist;
import mytunes.be.Song;
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

    public List<Playlist> getAll() {
        List<Playlist> allPlaylists = playlistDAO.getAll();
        for (Playlist playlist :
                allPlaylists) {
            List<Song> songsOnPlaylist = songsOnPlaylistDAO.getAll(playlist);
            playlist.addSongs(songsOnPlaylist);
        }
        return allPlaylists;
    }

    public int add(Playlist playlist) {
        return playlistDAO.add(playlist);
    }

    public void update(Playlist updatedPlaylist) {
        playlistDAO.update(updatedPlaylist);
    }

    public void delete(Playlist playlist) {
        playlistDAO.delete(playlist);
    }
}
