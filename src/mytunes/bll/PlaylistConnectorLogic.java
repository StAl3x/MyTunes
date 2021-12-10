package mytunes.bll;

import mytunes.be.Playlist;
import mytunes.be.Song;
import mytunes.dal.PlaylistConnectorDAO;

public class PlaylistConnectorLogic {

    private PlaylistConnectorDAO playlistConnectorDAO;

    public PlaylistConnectorLogic()
    {
        playlistConnectorDAO = new PlaylistConnectorDAO();
    }

    public void addSongToPlaylist(Song song, Playlist playlist)
    {
        playlistConnectorDAO.addSongToPlaylist(song, playlist);
    }

    public void removeSong(Playlist playlist, Song song)
    {
        playlistConnectorDAO.removeSong(playlist, song);
    }

}
