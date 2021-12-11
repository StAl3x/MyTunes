package mytunes.dal;

import com.microsoft.sqlserver.jdbc.SQLServerException;
import mytunes.be.Playlist;
import mytunes.be.Song;

import java.sql.*;

public class PlaylistConnectorDAO {

    private DBConnector dbConnector;

    public PlaylistConnectorDAO()
    {
        this.dbConnector = new DBConnector();
    }

    //Get next available position in the playlist for the song
    public int getNextAvailableIndex(Playlist playlist)
    {
        int playlistID = playlist.getPlaylistID();
        int songIndex = 0;
        try(Connection connection = dbConnector.getConnection())
        {
            String sql = "SELECT * FROM PlaylistConnector WHERE SongIndex=(SELECT max(SongIndex) FROM PlaylistConnector WHERE PlaylistID=(?));";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, playlistID);
            if(preparedStatement.execute())
            {
                ResultSet resultSet = preparedStatement.getResultSet();
                while(resultSet.next())
                {
                    int id = resultSet.getInt("SongIndex");
                    songIndex = id +1;
                }
            }
        }
        catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return songIndex;
    }

    //Adds a row to the table containing the playlistID and the songID, along with an index indicating song position
    public void addSongToPlaylist(Song song, Playlist playlist)
    {
        int songID = song.getSongID();
        int playlistID = playlist.getPlaylistID();
        int songIndex = getNextAvailableIndex(playlist);

        try (Connection connection = dbConnector.getConnection())
        {
            String sql = "INSERT INTO PlaylistConnector(PlaylistId, SongId, SongIndex) VALUES ( ? , ? , ? );";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1,playlistID);
            statement.setInt(2,songID);
            statement.setInt(3,songIndex);
            statement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public void removeSong(Playlist playlist, Song song)
    {
        try (Connection connection = dbConnector.getConnection())
        {
            String sql1 = "DELETE FROM PlaylistConnector WHERE PlaylistID = ? AND SongID = ? AND SongIndex = ?;";
            PreparedStatement preparedStatement1 =connection.prepareStatement(sql1);
            preparedStatement1.setInt(1, playlist.getPlaylistID());
            preparedStatement1.setInt(2, song.getSongID());
            preparedStatement1.setInt(3, song.getIndex());
            preparedStatement1.execute();
            System.out.println(playlist.getPlaylistID());
            System.out.println(song.getSongID());
            System.out.println(song.getIndex());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void moveSongUp(Playlist playlist, Song song)
    {
        int desiredIndex = song.getIndex() -1;
        int index = song.getIndex();

        try (Connection connection = dbConnector.getConnection())
        {
            String sql1 = "UPDATE PlaylistConnector SET SongIndex = ? WHERE PlaylistID = ? AND SongID = ? AND SongIndex = ?";
            PreparedStatement preparedStatement1 =connection.prepareStatement(sql1);

            preparedStatement1.setInt(1, desiredIndex);
            preparedStatement1.setInt(2, song.getSongID());
            preparedStatement1.setInt(3, song.getIndex());
            preparedStatement1.execute();
            System.out.println(playlist.getPlaylistID());
            System.out.println(song.getSongID());
            System.out.println(song.getIndex());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

}
