package mytunes.dal;

import mytunes.be.Playlist;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PlaylistDAO {
    private DBConnector dbConnector;

    public PlaylistDAO() {
        dbConnector = new DBConnector();
    }

    public List<Playlist> getAll() {
        List<Playlist> allPlaylists = new ArrayList<>();

        try (Connection connection = dbConnector.getConnection()) {
            String sql = "SELECT * FROM Playlists";
            Statement statement = connection.createStatement();

            ResultSet rs = statement.executeQuery(sql);

            while (rs.next()) {
                int id = rs.getInt("ID");
                String name = rs.getString("NAME");
                Playlist playlist = new Playlist(name);
                playlist.setID(id);
                allPlaylists.add(playlist);
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return allPlaylists;
    }

    public int add(Playlist playlist){
        String name = playlist.getName();

        try(Connection connection = dbConnector.getConnection()){
            String sql = "INSERT INTO Playlists(NAME) OUTPUT inserted.ID VALUES (?)";
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, name);

            ResultSet rs = statement.executeQuery();

            //returns the ID in DB of just added Playlist
            rs.next();
            int id = rs.getInt("ID");
            return id;
        } catch (SQLException exception){
            exception.printStackTrace();
            return -1;
        }
    }

    public void update(Playlist playlist){
        int id = playlist.getID();

        String name = playlist.getName();

        try(Connection connection = dbConnector.getConnection()){
            String sql = "UPDATE Playlists SET NAME = ? WHERE ID = ?";
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, name);
            statement.setInt(2, id);

            statement.execute();
        } catch (SQLException exception){
            exception.printStackTrace();
        }
    }

    public void delete(Playlist playlist){
        try(Connection connection = dbConnector.getConnection()){
            //! Will have problems in future with FKs on SongsOnPlaylist Table !
            String sql = "DELETE FROM Playlists WHERE ID = ?";
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setInt(1, playlist.getID());

            statement.execute();
        } catch (SQLException exception){
            exception.printStackTrace();
        }
    }

    /*public void addPlaylist(String title) throws SQLException {
        try (Connection connection = dbConnector.getConnection()) {
            String sql = "INSERT INTO Playlists(Title) VALUES ( ? )";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, title);
            statement.execute();
        }
    }*/

    /*public void addSongToPlaylistConnector(int songId, int playlistId, int songIndex) throws SQLException {
        try (Connection connection = dbConnector.getConnection()) {
            String sql = "INSERT INTO PlaylistConnector(SongId,PlaylistId, SongIndex) VALUES ( ? , ? , ? )";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, songId);
            statement.setInt(2, playlistId);
            statement.setInt(3, songIndex);
            statement.execute();
        }
    }*/

    /*public void seedPlaylist(Playlist playlist) throws SQLException {
        try (Connection connection = dbConnector.getConnection()) {
            String sql = "SELECT * FROM PlaylistConnector WHERE PlaylistId = ? ORDER BY PlaylistIndex ASC;";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, playlist.getPlaylistID());
            List<Song> playlistSongs = new ArrayList<>();
            if (statement.execute(sql)) {
                List<Song> allSongs = songsLogic.getAll();
                ResultSet resultSet = statement.getResultSet();
                while (resultSet.next()) {
                    int songId = resultSet.getInt("SongID");
                    playlist.addSong(allSongs.get(songId));
                }
            }
        }
    }*/

    /*public void removeSong(Playlist playlist) {
        try (Connection connection = dbConnector.getConnection()) {
            String sql1 = "DELETE FROM PlaylistConnector WHERE PlaylistID = ?";
            PreparedStatement preparedStatement1 = connection.prepareStatement(sql1);
            preparedStatement1.setInt(1, playlist.getID());
            preparedStatement1.execute();
            String sql2 = "DELETE FROM Playlists WHERE PlaylistID = ?";
            PreparedStatement preparedStatement2 = connection.prepareStatement(sql2);
            preparedStatement2.setInt(1, playlist.getID());
            preparedStatement2.execute();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }*/

}
