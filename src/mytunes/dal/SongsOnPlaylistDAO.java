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
                    int time = rsSong.getInt("TIME");
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

    /***
     * Delete whole playlist then add it back with updated Songs
     * @param playlist updated playlist
     */
    public void updatePlaylist(Playlist playlist) {

        //delete all Songs on Playlist
        //add them all back without the selected index
        int playlistID = playlist.getID();

        try(Connection connection = dbConnector.getConnection()){
            String sqlPlaylist = "DELETE FROM SongsOnPlaylist WHERE PlaylistID=?";
            PreparedStatement statementPlaylist = connection.prepareStatement(sqlPlaylist);

            statementPlaylist.setInt(1, playlistID);

            statementPlaylist.execute();

            int index_ = 0;
            for (Song song :
                    playlist.getSongs()) {
                int songID = song.getID();

                String sqlSong = "INSERT INTO SongsOnPlaylist(SongID, PlaylistID, [INDEX]) VALUES (?, ?, ?)";
                PreparedStatement statementSong = connection.prepareStatement(sqlSong);

                statementSong.setInt(1, songID);
                statementSong.setInt(2, playlistID);
                statementSong.setInt(3, index_);

                statementSong.execute();
                index_++;
            }

        } catch (SQLException exception){
            exception.printStackTrace();
        }
    }
}
