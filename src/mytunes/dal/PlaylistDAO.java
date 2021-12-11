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
            //first, we delete all Songs from the Playlist
            String sqlSongs = "DELETE FROM SongsOnPlaylist WHERE PlaylistID=?";
            PreparedStatement statementSongs = connection.prepareStatement(sqlSongs);

            statementSongs.setInt(1, playlist.getID());

            statementSongs.execute();

            //then we delete the playlist itself from DB
            String sqlPlaylist = "DELETE FROM Playlists WHERE ID = ?";
            PreparedStatement statement = connection.prepareStatement(sqlPlaylist);

            statement.setInt(1, playlist.getID());

            statement.execute();
        } catch (SQLException exception){
            exception.printStackTrace();
        }
    }
}
