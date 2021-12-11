package mytunes.dal;

import mytunes.be.Playlist;
import mytunes.be.Song;
import mytunes.be.SongGenre;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SongsOnPlaylistDAO {
    private DBConnector dbConnector;

    public SongsOnPlaylistDAO(){
        dbConnector = new DBConnector();
    }

    public List<Song> getAll(Playlist playlist) {
        List<Song> songsOnPlaylist = new ArrayList<>();

        try(Connection connection = dbConnector.getConnection()){
            String sql = "SELECT * FROM SongsOnPlaylist WHERE PlaylistID = ? ORDER BY [INDEX] ASC";
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setInt(1, playlist.getID());
            ResultSet rsSongs = statement.executeQuery();

            while(rsSongs.next()){
                int songID = rsSongs.getInt("SongID");

                String sqlSong = "SELECT * FROM Songs WHERE ID = ?";
                PreparedStatement statementSong = connection.prepareStatement(sqlSong);

                statementSong.setInt(1, songID);
                ResultSet rsSong = statementSong.executeQuery();

                while(rsSong.next()){
                    String title = rsSong.getString("TITLE");
                    String artist = rsSong.getString("ARTIST");
                    String genre = rsSong.getString("GENRE");
                    String time = rsSong.getString("TIME");
                    String source = rsSong.getString("SOURCE");
                    SongGenre songGenre = SongGenre.valueOf(genre.replaceAll(" ", ""));

                    Song song = new Song(title, artist, songGenre, time, source);
                    song.setID(songID);
                    songsOnPlaylist.add(song);
                }
            }
        } catch (SQLException exception){
            exception.printStackTrace();
        }
        return songsOnPlaylist;
    }

    public void add(Song song, Playlist playlist) {
        int songID = song.getID();
        int playlistID = playlist.getID();

        try(Connection connection = dbConnector.getConnection()){
            String sql = "INSERT INTO SongsOnPlaylist(SongID, PlaylistID, [INDEX]) VALUES (?, ? ,?)";
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setInt(1, songID);
            statement.setInt(2, playlistID);
            statement.setInt(3, playlist.getSongs().size());

            statement.execute();
        } catch (SQLException exception){
            exception.printStackTrace();
        }
    }

    public void update(Playlist playlist) {
    }

    public void delete(Song song, Playlist playlist) {
        int songID = song.getID();
        int playlistID = playlist.getID();

        try(Connection connection = dbConnector.getConnection()){
            String sql = "DELETE FROM SongsOnPlaylist WHERE SongID=? AND PlaylistID=?";
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setInt(1, songID);
            statement.setInt(2, playlistID);

            statement.execute();
        } catch (SQLException exception){
            exception.printStackTrace();
        }
    }
}
