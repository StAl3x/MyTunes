package mytunes.dal;

import mytunes.be.Playlist;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PlaylistDAO
{
    private DBConnector dbConnector;
    public PlaylistDAO()
    {
        dbConnector = new DBConnector();
    }
    public List<Playlist> getAllPlaylists() throws SQLException
    {
        ArrayList<Playlist> allPlaylists = new ArrayList<>();
        try(Connection connection = dbConnector.getConnection())
        {
            String sql = "SELECT * FROM Playlists";
            Statement statement = connection.createStatement();
            if(statement.execute(sql))
            {
                ResultSet resultSet = statement.getResultSet();
                while (resultSet.next())
                {
                    int id = resultSet.getInt("PlaylistID");
                    String title= resultSet.getString("Title");
                    Playlist playlist = new Playlist(title);
                    playlist.setId(id);
                    allPlaylists.add(playlist);
                }
            }
        }
        return allPlaylists;
    }
    public void addPlaylist(String title) throws SQLException
    {
        try (Connection connection = dbConnector.getConnection())
        {
            String sql = "INSERT INTO Playlists(Title) VALUES ( ? )";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1,title);
            statement.execute();
        }
    }
    public void addSongToPlaylist( int songId , int playlistId , int songIndex ) throws SQLException
    {
        try (Connection connection = dbConnector.getConnection())
        {
            String sql = "INSERT INTO PlaylistConnector(SongId,PlaylistId, Index) VALUES ( ? , ? , ? )";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1,songId);
            statement.setInt(2,playlistId);
            statement.setInt(3,songIndex);
            statement.execute();
        }
    }

    public void seedPlaylist ( Playlist playlist ) throws SQLException
    {
        try (Connection connection = dbConnector.getConnection())
        {
            String sql = "SELECT * FROM PlaylistConnector WHERE PlaylistId = ? ORDER BY PlaylistIndex ASC;";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, playlist.getId());

            if(statement.execute(sql))
            {
                ResultSet resultSet = statement.getResultSet();
                while (resultSet.next())
                {
                    int songId = resultSet.getInt("SongID");


                }
            }
        }


    }

}
