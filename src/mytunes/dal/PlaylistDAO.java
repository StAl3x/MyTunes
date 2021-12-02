package mytunes.dal;

import mytunes.be.Playlist;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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

                    Playlist playlist = new Playlist(id, title);
                    allPlaylists.add(playlist);

                }
            }
        }
        return allPlaylists;
    }
}
