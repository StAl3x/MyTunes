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

    public int getNextAvailableID()
    {
        int songID = -1;
        try(Connection connection = dbConnector.getConnection())
        {
            String sql = "SELECT * FROM Songs WHERE SongID=(SELECT max(SongID) FROM Songs);";
            Statement statement = connection.createStatement();
            if(statement.execute(sql))
            {
                ResultSet resultSet = statement.getResultSet();
                while(resultSet.next())
                {
                    int id = resultSet.getInt("SongID");
                    songID = id +1;
                }
            }
        }

        catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return songID;
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
                    song.setSongID(id);
                    allSongs.add(song);

                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return allSongs;
    }
    public Song addSong(Song song)
    {
        String title = song.getTitle();
        String artist = song.getArtist();
        SongGenre genre = song.getGenre();
        String genreToString = song.getGenre().toString();
        String source = song.getSource();

        try (Connection connection = dbConnector.getConnection())
        {
            String sql = "INSERT INTO Songs( Title, Artist, Genre, Source) VALUES ( ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, title);
            statement.setString(2, artist);
            statement.setString(3, genreToString);
            statement.setString(4, source);
            statement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        Song returnSong = new Song(title, artist, genre, source);
        returnSong.setSongID(getNextAvailableID());
        return returnSong;
    }

    public Song editSong(int songID, Song editedSong)
    {

        String title = editedSong.getTitle();
        String artist = editedSong.getArtist();
        SongGenre genre = editedSong.getGenre();
        String source = editedSong.getSource();

        try (Connection connection = dbConnector.getConnection())
        {
            String sql = "UPDATE Songs SET Title=?, Artist=?, Genre=?, Source=? WHERE SongID=?;";
            PreparedStatement preparedStatement =connection.prepareStatement(sql);

            preparedStatement.setString(1, title);
            preparedStatement.setString(2, artist);
            preparedStatement.setString(3, editedSong.getGenre().toString());
            preparedStatement.setString(4, source);
            preparedStatement.setInt(5, songID);
            preparedStatement.execute();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        Song newSong = new Song(title, artist, genre, source);
        newSong.setSongID(songID);
        return newSong;
    }
}
