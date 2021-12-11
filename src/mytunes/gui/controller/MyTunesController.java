package mytunes.gui.controller;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import mytunes.be.Playlist;
import mytunes.be.Song;
import mytunes.gui.model.ListViewSongsModel;
import mytunes.gui.model.TableViewPlaylistsModel;
import mytunes.gui.model.TableViewSongsModel;
import mytunes.gui.view.SongDialog;
import mytunes.gui.view.PlaylistDialog;

import java.net.URL;
import java.util.*;

public class MyTunesController implements Initializable {

    public TableView<Song> tblViewRight;
    private TableViewSongsModel tvSongsModel;
    public TableColumn<Song, String> tblColumnTitle;
    public TableColumn<Song, String> tblColumnArtist;
    public TableColumn<Song, String> tblColumnGenre;
    public TableColumn<Song, String> tblColumnTime;

    public TableView<Playlist> tblViewLeft;
    private TableViewPlaylistsModel tvPlaylistsModel;
    public TableColumn<Playlist, String> tblColumnName;
    public TableColumn<Playlist, String> tblColumnSongs;
    public TableColumn<Playlist, String> tblColumnPlaylistTime;

    public ListView<Song> lstViewMiddle;
    private ListViewSongsModel lvSongsModel;

    public Label lblSongPlaying;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tvSongsModel = new TableViewSongsModel();
        tblViewRight.setItems(tvSongsModel.getSongsList());

        tvPlaylistsModel = new TableViewPlaylistsModel();
        tblViewLeft.setItems(tvPlaylistsModel.getPlaylistList());

        lvSongsModel = new ListViewSongsModel();
        lstViewMiddle.setItems(lvSongsModel.getSongs());
        initTables();
    }

    /*
        Adds cell value factories to columns in tables
        a.k.a takes the desired values from objects, so we don't have to do it manually
     */
    private void initTables() {
        //right side
        tblColumnTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        tblColumnArtist.setCellValueFactory(new PropertyValueFactory<>("artist"));
        tblColumnGenre.setCellValueFactory(new PropertyValueFactory<>("genre"));
        tblColumnTime.setCellValueFactory(new PropertyValueFactory<>("time"));

        //left side
        tblColumnName.setCellValueFactory(new PropertyValueFactory<>("name"));
        //tblColumnSongs.setCellValueFactory(new PropertyValueFactory<>(""));
        //tblColumnPlaylistTime.setCellValueFactory(new PropertyValueFactory<>(""));
    }

    /*
        RIGHT SIDE
     */

    /*
        Opens a Dialog for creating a new Song
        adds the Song when APPLY is clicked
     */
    public void handleNewSong(ActionEvent event) {
        SongDialog dialog = new SongDialog();
        Optional<Song> result = dialog.showAndWait();
        result.ifPresent(response -> {
            tvSongsModel.addSong(response);
        });
    }

    /*
        Get selected Item (Song) from Right TableView,
        if Song is selected, open a Dialog & fill its fields with the Song properties
        (=> nice UX - they don't have to retype everything everytime)
        edits the Song when APPLY is clicked
     */
    public void handleEditSong(ActionEvent event) {
        Song selectedSong = tblViewRight.getSelectionModel().getSelectedItem();
        if(selectedSong != null){
            SongDialog dialog = new SongDialog();
            dialog.setFields(selectedSong);
            Optional<Song> result = dialog.showAndWait();
            result.ifPresent(response -> {
                response.setID(selectedSong.getID());
                tvSongsModel.edit(selectedSong, response);
            });
        }
    }

    public void handleDeleteSong(ActionEvent event) {
        Song selectedSong = tblViewRight.getSelectionModel().getSelectedItem();
        if(selectedSong != null)
        {
            tvSongsModel.deleteSong(selectedSong);
        }
    }

    public void handleSongToPlaylist(ActionEvent event) {
        System.out.println("To Playlist");
        Song selectedSong = tblViewRight.getSelectionModel().getSelectedItem();
        Playlist selectedPlaylist = tblViewLeft.getSelectionModel().getSelectedItem();
        if(selectedSong != null && selectedPlaylist != null) {
            lvSongsModel.addSong(selectedSong, selectedPlaylist);
            selectedPlaylist.addSong(selectedSong);
        }
    }

    /*
        LEFT SIDE
     */
    public void handleNewPlaylist(ActionEvent event) {
        PlaylistDialog dialog = new PlaylistDialog();
        Optional<Playlist> result = dialog.showAndWait();
        result.ifPresent(response -> {
            tvPlaylistsModel.addPlaylist(response);
        });
    }


    public void handleEditPlaylist(ActionEvent event) {
        Playlist selectedPlaylist = tblViewLeft.getSelectionModel().getSelectedItem();
        if(selectedPlaylist != null){
            PlaylistDialog dialog = new PlaylistDialog();
            dialog.setFields(selectedPlaylist);
            Optional<Playlist> result = dialog.showAndWait();
            result.ifPresent(response -> {
                response.setID(selectedPlaylist.getID());
                tvPlaylistsModel.edit(selectedPlaylist, response);
            });
        }

    }

    public void handleDeletePlaylist(ActionEvent event) {
        Playlist selectedPlaylist = tblViewLeft.getSelectionModel().getSelectedItem();
        if(selectedPlaylist != null)
        {
            tvPlaylistsModel.deletePlaylist(selectedPlaylist);
        }
        lvSongsModel.deleteSongsFromListView();
    }

    /*
        MIDDLE PART
     */

    public void handleLeftTableClicked(MouseEvent mouseEvent) {
        Playlist playlist = tblViewLeft.getSelectionModel().getSelectedItem();
        if(playlist != null)
        {
            lvSongsModel.select(playlist);
        }
    }

    public void handleMoveUp(ActionEvent event) {
        System.out.println("Move Up");
        Song selectedSong = lstViewMiddle.getSelectionModel().getSelectedItem();
        Playlist selectedPlaylist = tblViewLeft.getSelectionModel().getSelectedItem();
        List<Song> songList = lvSongsModel.getSongs();
        if(tblViewLeft.getSelectionModel().getSelectedItem() != null)
        {
            if(selectedSong != null)
            {
                for (int i = 0; i<songList.size(); i++)
                {
                    if(songList.get(i).equals(selectedSong) && i !=0) {
                        Collections.swap(songList, i, i - 1);
                        Collections.swap(selectedPlaylist.getSongs(), i, i-1);
                        break;
                    }
                }

            }
        }
    }

    public void handleMoveDown(ActionEvent event) {
        System.out.println("Move Down");
        Song selectedSong = lstViewMiddle.getSelectionModel().getSelectedItem();
        Playlist selectedPlaylist = tblViewLeft.getSelectionModel().getSelectedItem();
        List<Song> songList = lvSongsModel.getSongs();
        if(tblViewLeft.getSelectionModel().getSelectedItem() != null)
        {
            if(selectedSong != null)
            {
                for (int i = 0; i<songList.size(); i++)
                {
                    if(songList.get(i).equals(selectedSong) && i !=songList.size()-1) {
                        Collections.swap(songList, i, i + 1);
                        Collections.swap(selectedPlaylist.getSongs(), i, i+1);
                        break;
                    }
                }

            }
        }
    }

    public void handleRemoveFromPlaylist(ActionEvent event) {
        System.out.println("Remove From Playlist");
        Song selectedSong = lstViewMiddle.getSelectionModel().getSelectedItem();
        Playlist selectedPlaylist = tblViewLeft.getSelectionModel().getSelectedItem();
        if(selectedSong != null && selectedPlaylist != null){
            lvSongsModel.delete(selectedSong, selectedPlaylist);
        }
    }


    /*
        TOP PART
     */
    public void handlePlay(ActionEvent event) {
        System.out.println("Play");

    }

    public void handlePrevious(ActionEvent event) {
        System.out.println("Previous");

    }

    public void handleNext(ActionEvent event) {
        System.out.println("Next");

    }

    public void handleVolume(MouseEvent mouseEvent) {
        System.out.println("Change of Volume");

    }

    public void handleFilter(ActionEvent event) {
        System.out.println("Filter");

    }



}
