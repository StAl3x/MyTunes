package mytunes.dal;

import mytunes.be.Song;
import mytunes.be.SongGenre;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SongDAO {
    private DBConnector dbConnector;
    public SongDAO()
    {
        dbConnector = new DBConnector();
    }

    public List<Song> getAllSongs() throws SQLException
    {
        ArrayList<Song> allSongs = new ArrayList<>();
        try(Connection connection = dbConnector.getConnection())
        {
            String sql = "SELECT * FROM Songs";
            Statement statement = connection.createStatement();
            if(statement.execute(sql))
            {
                ResultSet resultSet = statement.getResultSet();
                while (resultSet.next())
                {
                    int id = resultSet.getInt("SongID");
                    String title = resultSet.getString("Title");
                    String artist = resultSet.getString("Artist");
                    String genre = resultSet.getString("Genre");
                    String source = resultSet.getString("Source");

                    Song song = new Song(title, artist,  SongGenre.valueOf(genre), source);
                    allSongs.add(song);

                }
            }
        }
        return allSongs;
    }
    public void addSong(String title, String artist, String genre, String source) throws SQLException
    {
        try (Connection connection = dbConnector.getConnection())
        {
            String sql = "INSERT INTO Songs( Title, Artist, Genre, Source) VALUES ( ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, title);
            statement.setString(2, artist);
            statement.setString(3, genre);
            statement.setString(4, source);
            statement.execute();
        }
    }
}
