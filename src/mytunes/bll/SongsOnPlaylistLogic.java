package mytunes.bll;

import mytunes.be.Playlist;
import mytunes.be.Song;
import mytunes.dal.Exceptions.DataException;
import mytunes.dal.SongsOnPlaylistDAO;

import java.util.Collections;
import java.util.List;

public class SongsOnPlaylistLogic {

    private SongsOnPlaylistDAO songsOnPlaylistDAO;

    public SongsOnPlaylistLogic(){
        this.songsOnPlaylistDAO = new SongsOnPlaylistDAO();
    }

    public List<Song> getAll(Playlist playlist) throws DataException {
        return songsOnPlaylistDAO.getAll(playlist);
    }

    public void add(Song song, Playlist playlist) throws DataException {
        songsOnPlaylistDAO.add(song, playlist);
    }

    public void update(Playlist playlist) throws DataException {
        songsOnPlaylistDAO.updatePlaylist(playlist);
    }

    public void moveUp(int index, int swapOther, Playlist playlist) throws DataException {
        Collections.swap(playlist.getSongs(), index, swapOther);
        this.update(playlist);
    }

    public void moveDown(int index, int swapOther, Playlist playlist) throws DataException {
        Collections.swap(playlist.getSongs(), index, swapOther);
        this.update(playlist);
    }
}
