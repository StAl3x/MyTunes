package mytunes.dal;

import com.microsoft.sqlserver.jdbc.SQLServerException;
import mytunes.be.Playlist;
import mytunes.be.Song;
import mytunes.bll.SongsLogic;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PlaylistDAO
{
    private DBConnector dbConnector;
    private SongsLogic songsLogic;
    public PlaylistDAO()
    {
        dbConnector = new DBConnector();
    }

    public int getNextAvailableID()
    {
        int playlistID = -1;
        try(Connection connection = dbConnector.getConnection())
        {
            String sql = "SELECT * FROM Playlists WHERE PlaylistID=(SELECT max(PlaylistID) FROM Playlists);";
            Statement statement = connection.createStatement();
            if(statement.execute(sql))
            {
                ResultSet resultSet = statement.getResultSet();
                while(resultSet.next())
                {
                    int id = resultSet.getInt("PlaylistID");
                    playlistID = id +1;
                }
            }
        }
        catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return playlistID;
    }

    public List<Playlist> getAllPlaylists()
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
                    playlist.setPlaylistID(id);
                    allPlaylists.add(playlist);
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return allPlaylists;
    }
    public Playlist addPlaylist(String title)
    {
        try (Connection connection = dbConnector.getConnection())
        {
            String sql = "INSERT INTO Playlists(Title) VALUES ( ? )";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1,title);
            statement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        Playlist playlist = new Playlist(title);
        playlist.setPlaylistID(getNextAvailableID());
        return playlist;
    }

    public void addSongToPlaylistConnector( int songId , int playlistId , int songIndex ) throws SQLException
    {
        try (Connection connection = dbConnector.getConnection())
        {
            String sql = "INSERT INTO PlaylistConnector(SongId,PlaylistId, SongIndex) VALUES ( ? , ? , ? )";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1,songId);
            statement.setInt(2,playlistId);
            statement.setInt(3,songIndex);
            statement.execute();
        }
    }

    public void seedPlaylist ( Playlist playlist )
    {
        try (Connection connection = dbConnector.getConnection())
        {
            String sql = "SELECT * FROM PlaylistConnector WHERE PlaylistId = ? ORDER BY SongIndex ASC;";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, playlist.getPlaylistID());
            List<Song> playlistSongs = new ArrayList<>();
            if(statement.execute())
            {
                List<Song> allSongs = songsLogic.getAllSongs();
                ResultSet resultSet = statement.getResultSet();
                while (resultSet.next())
                {
                    int songId = resultSet.getInt("SongID");
                    playlist.addSong(allSongs.get(songId));
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void removeSong(Playlist playlist)
    {
        try (Connection connection = dbConnector.getConnection())
        {
            String sql1 = "DELETE FROM PlaylistConnector WHERE PlaylistID = ?";
            PreparedStatement preparedStatement1 =connection.prepareStatement(sql1);
            preparedStatement1.setInt(1, playlist.getPlaylistID());
            preparedStatement1.execute();
            String sql2 = "DELETE FROM Playlists WHERE PlaylistID = ?";
            PreparedStatement preparedStatement2 =connection.prepareStatement(sql2);
            preparedStatement2.setInt(1, playlist.getPlaylistID());
            preparedStatement2.execute();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public Playlist editPlaylist(int playlistID, Playlist editedPlaylist)
    {
        String title = editedPlaylist.getName();
        try (Connection connection = dbConnector.getConnection())
        {
            String sql = "UPDATE Playlists SET Title=? WHERE PlaylistID=?;";
            PreparedStatement preparedStatement =connection.prepareStatement(sql);

            preparedStatement.setString(1, title);
            preparedStatement.setInt(2, playlistID);
            preparedStatement.execute();
        }
        catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        Playlist newPlaylist = new Playlist(title);
        newPlaylist.setPlaylistID(playlistID);
        return newPlaylist;
    }

    public void deletePlaylist(Playlist playlist) {
        try (Connection connection = dbConnector.getConnection())
        {
            String sql2 = "DELETE FROM Playlists WHERE PlaylistID = ?";
            PreparedStatement preparedStatement2 =connection.prepareStatement(sql2);
            preparedStatement2.setInt(1, playlist.getPlaylistID());
            preparedStatement2.execute();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
