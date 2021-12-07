package mytunes.dal;

import com.microsoft.sqlserver.jdbc.SQLServerException;
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

    public List<Song> getAllSongs()
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
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return allSongs;
    }
    public void addSong(Song song)
    {
        String title = song.getTitle();
        String artist = song.getArtist();
        String genre = song.getGenre().toString();
        String source = song.getSource();

        try (Connection connection = dbConnector.getConnection())
        {
            String sql = "INSERT INTO Songs( Title, Artist, Genre, Source) VALUES ( ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, title);
            statement.setString(2, artist);
            statement.setString(3, genre);
            statement.setString(4, source);
            statement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void editSong(int indexOfSelectedSong, Song editedSong)
    {
        try (Connection connection = dbConnector.getConnection())
        {
            String sql = "UPDATE Songs SET Title=?, Artist=?, Genre=?, Source=? WHERE SongID=?;";
            PreparedStatement preparedStatement =connection.prepareStatement(sql);

            preparedStatement.setString(1, editedSong.getTitle());
            preparedStatement.setString(2, editedSong.getTitle());
            preparedStatement.setString(3, editedSong.getGenre().toString());
            preparedStatement.setString(4, editedSong.getSource());
            preparedStatement.setInt(5, indexOfSelectedSong);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
