package mytunes.dal;

import mytunes.be.Song;
import mytunes.be.SongGenre;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SongDAO {

    private DBConnector dbConnector;

    public SongDAO() {
        dbConnector = new DBConnector();
    }

    public List<Song> getAll() {
        ArrayList<Song> allSongs = new ArrayList<>();

        try (Connection connection = dbConnector.getConnection()) {
            String sql = "SELECT * FROM Songs";
            Statement statement = connection.createStatement();

            ResultSet rs = statement.executeQuery(sql);

            while (rs.next()) {
                int id = rs.getInt("ID");
                String title = rs.getString("TITLE");
                String artist = rs.getString("ARTIST");
                String genre = rs.getString("GENRE");
                int time = rs.getInt("TIME");
                String source = rs.getString("SOURCE");
                SongGenre songGenre = SongGenre.valueOf(genre.replaceAll(" ", ""));

                Song song = new Song(title, artist, songGenre, time, source);
                song.setID(id);
                allSongs.add(song);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return allSongs;
    }

    public int add(Song song) {
        String title = song.getTitle();
        String artist = song.getArtist();
        String genre = song.getGenre().toString();
        int time = song.getTime();
        String source = song.getSource();

        try (Connection connection = dbConnector.getConnection()) {
            String sql = "INSERT INTO Songs(TITLE, ARTIST, GENRE, TIME, SOURCE) OUTPUT inserted.ID VALUES (?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, title);
            statement.setString(2, artist);
            statement.setString(3, genre);
            statement.setInt(4, time);
            statement.setString(5, source);

            ResultSet rs = statement.executeQuery();

            //returns the ID in DB of just added Song
            rs.next();
            return rs.getInt("ID");
        } catch (SQLException exception) {
            exception.printStackTrace();
            return -1;
        }
    }

    public void update(Song song) {

        int id = song.getID();
        String title = song.getTitle();
        String artist = song.getArtist();
        String genre = song.getGenre().toString();
        int time = song.getTime();
        String source = song.getSource();

        try (Connection connection = dbConnector.getConnection()) {

            String sql = "UPDATE Songs SET TITLE = ?, ARTIST = ?, GENRE = ?, TIME = ?, SOURCE = ? WHERE ID = ?";
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, title);
            statement.setString(2, artist);
            statement.setString(3, genre);
            statement.setInt(4, time);
            statement.setString(5, source);
            statement.setInt(6, id);

            statement.execute();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    public void delete(Song song) {
        try (Connection connection = dbConnector.getConnection()) {
            //first, we delete all Song occurrences in Playlists
            String sqlPlaylists = "DELETE FROM SongsOnPlaylist WHERE SongID=?";
            PreparedStatement statementPlaylists = connection.prepareStatement(sqlPlaylists);

            statementPlaylists.setInt(1, song.getID());

            statementPlaylists.execute();

            //then we delete the Song itself
            String sqlSong = "DELETE FROM Songs WHERE ID = ?";
            PreparedStatement statement = connection.prepareStatement(sqlSong);

            statement.setInt(1, song.getID());

            statement.execute();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }
}
