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
            if (statement.execute(sql)) {
                ResultSet resultSet = statement.getResultSet();
                while (resultSet.next()) {
                    int id = resultSet.getInt("ID");
                    String title = resultSet.getString("TITLE");
                    String artist = resultSet.getString("ARTIST");
                    String genre = resultSet.getString("GENRE");
                    String time = resultSet.getString("TIME");
                    String source = resultSet.getString("SOURCE");
                    SongGenre songGenre = SongGenre.valueOf(genre.replaceAll(" ", ""));

                    Song song = new Song(title, artist, songGenre, time, source);
                    song.setSongID(id);
                    allSongs.add(song);
                }
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
        String time = song.getTime();
        String source = song.getSource();

        try (Connection connection = dbConnector.getConnection()) {
            String sql = "INSERT INTO Songs(TITLE, ARTIST, GENRE, TIME, SOURCE) OUTPUT inserted.ID VALUES (?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, title);
            statement.setString(2, artist);
            statement.setString(3, genre);
            statement.setString(4, time);
            statement.setString(5, source);
            ResultSet rs = statement.executeQuery();

            //returns the ID in DB of just added Song
            rs.next();
            int id = rs.getInt("ID");
            return id;
        } catch (SQLException exception) {
            exception.printStackTrace();
            return -1;
        }
    }
    public void update(Song song) {
        int id = song.getSongID();
        String title = song.getTitle();
        String artist = song.getArtist();
        String genre = song.getGenre().toString();
        String time = song.getTime();
        String source = song.getSource();

        try (Connection connection = dbConnector.getConnection()) {
            String sql = "UPDATE Songs SET TITLE = ?, ARTIST = ?, GENRE = ?, TIME = ?, SOURCE = ? WHERE ID = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, title);
            statement.setString(2, artist);
            statement.setString(3, genre);
            statement.setString(4, time);
            statement.setString(5, source);
            statement.setInt(6, id);
            statement.execute();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    public void delete(Song song){
        try(Connection connection = dbConnector.getConnection()){
            String sql = "DELETE FROM Songs WHERE ID = ?";
            //! Will have problems in future with FKs on SongsOnPlaylist Table !
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, song.getSongID());
            statement.execute();
        } catch (SQLException exception){
            exception.printStackTrace();
        }
    }
}
