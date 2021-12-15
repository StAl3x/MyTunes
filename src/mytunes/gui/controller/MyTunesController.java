package mytunes.gui.controller;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import mytunes.be.Playlist;
import mytunes.be.Song;
import mytunes.dal.Exceptions.DataException;
import mytunes.gui.model.ListViewSongsModel;
import mytunes.gui.model.TableViewPlaylistsModel;
import mytunes.gui.model.TableViewSongsModel;
import mytunes.gui.view.ErrorAlert;
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

    public TextField txtFieldFilter;

    public Label lblSongPlaying;
    public Slider sldrVolume;

    private Playlist playlistPlaying;
    private Playlist tempPlaylist;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            this.tvSongsModel = new TableViewSongsModel();
            this.tblViewRight.setItems(tvSongsModel.getSongsList());

            this.tvPlaylistsModel = new TableViewPlaylistsModel();
            this.tblViewLeft.setItems(tvPlaylistsModel.getPlaylistList());

            this.tvSongsModel = new TableViewSongsModel();
            this.tblViewRight.setItems(tvSongsModel.getSongsList());
            this.lvSongsModel = new ListViewSongsModel();
            this.lstViewMiddle.setItems(lvSongsModel.getSongs());

            this.initTables();
        } catch (DataException e) {
            createAlertDialog(e);
            initialize(location, resources);
        }


    }

    private void createAlertDialog(Exception e) {
        ErrorAlert dialog = new ErrorAlert(Alert.AlertType.CONFIRMATION, e.getMessage(), ButtonType.OK);
        dialog.showAndWait();
    }

    /*
        Adds cell value factories to columns in tables
        a.k.a takes the desired values from objects, so we don't have to do it manually
     */
    private void initTables() {
        //right side
        this.tblColumnTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        this.tblColumnArtist.setCellValueFactory(new PropertyValueFactory<>("artist"));
        this.tblColumnGenre.setCellValueFactory(new PropertyValueFactory<>("genre"));
        this.tblColumnTime.setCellValueFactory(new PropertyValueFactory<>("time"));

        //left side
        this.tblColumnName.setCellValueFactory(new PropertyValueFactory<>("name"));
        this.tblColumnSongs.setCellValueFactory(new PropertyValueFactory<>("totalSongs"));
        this.tblColumnPlaylistTime.setCellValueFactory(new PropertyValueFactory<>("totalTime"));
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
            try {
                this.tvSongsModel.addSong(response);
            } catch (DataException e) {
                createAlertDialog(e);
                handleNewSong(event);
            }
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
                try {
                    this.tvSongsModel.edit(selectedSong, response);
                } catch (DataException e) {
                    createAlertDialog(e);
                    handleEditSong(event);
                }
            });
        }
    }

    public void handleDeleteSong(ActionEvent event) {
        Song selectedSong = tblViewRight.getSelectionModel().getSelectedItem();
        if(selectedSong != null)
        {
            try {
                this.tvSongsModel.deleteSong(selectedSong);
                this.lvSongsModel.removeOccurrence(selectedSong);
                this.tvPlaylistsModel.refresh();
            } catch (DataException e) {
                createAlertDialog(e);
                handleDeleteSong(event);
            }
        }
    }

    public void handleSongToPlaylist(ActionEvent event) {
        Song selectedSong = tblViewRight.getSelectionModel().getSelectedItem();
        Playlist selectedPlaylist = tblViewLeft.getSelectionModel().getSelectedItem();
        int index = tblViewLeft.getSelectionModel().getSelectedIndex();

        if(selectedSong != null && selectedPlaylist != null) {
            try {
                this.lvSongsModel.addSong(selectedSong, selectedPlaylist);
                selectedPlaylist.addSong(selectedSong);
                this.tvPlaylistsModel.update(index, selectedPlaylist);
            } catch (DataException e) {
                createAlertDialog(e);
                handleSongToPlaylist(event);
            }

        }
    }

    public void handleRightTableClicked(MouseEvent event)
    {
        this.lstViewMiddle.getSelectionModel().select(null);

        this.tempPlaylist = tvSongsModel.getPlaylist();
        int selectedIndex = tblViewRight.getSelectionModel().getSelectedIndex();
        this.tempPlaylist.setTempIndex(selectedIndex);
    }

    /*
        LEFT SIDE
     */
    public void handleNewPlaylist(ActionEvent event) {
        PlaylistDialog dialog = new PlaylistDialog();
        Optional<Playlist> result = dialog.showAndWait();
        result.ifPresent(response -> {
            try {
                this.tvPlaylistsModel.addPlaylist(response);
            } catch (DataException e) {
                createAlertDialog(e);
                handleNewPlaylist(event);
            }
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
                try {
                    this.tvPlaylistsModel.edit(selectedPlaylist, response);
                } catch (DataException e) {
                    createAlertDialog(e);
                    handleEditPlaylist(event);
                }
            });
        }

    }

    public void handleDeletePlaylist(ActionEvent event) {
        Playlist selectedPlaylist = tblViewLeft.getSelectionModel().getSelectedItem();
        if(selectedPlaylist != null)
        {
            try {
                this.tvPlaylistsModel.delete(selectedPlaylist);
                this.lvSongsModel.removeAll();
            } catch (DataException e) {
                createAlertDialog(e);
                handleDeletePlaylist(event);
            }
        }
    }

    /*
        MIDDLE PART
     */

    public void handleLeftTableClicked(MouseEvent mouseEvent) {
        Playlist selectedPlaylist = tblViewLeft.getSelectionModel().getSelectedItem();
        if(selectedPlaylist != null)
        {
            this.lvSongsModel.select(selectedPlaylist);
            this.tempPlaylist = selectedPlaylist;
            this.tempPlaylist.setTempIndex(0);
        }
    }

    public void handleListViewClicked(MouseEvent mouseEvent) {
        this.tblViewRight.getSelectionModel().select(null);

        Playlist selectedPlaylist = tblViewLeft.getSelectionModel().getSelectedItem();
        int songIndex = lstViewMiddle.getSelectionModel().getSelectedIndex();
        this.tempPlaylist = selectedPlaylist;
        this.tempPlaylist.setTempIndex(songIndex);
    }

    public void handleMoveUp(ActionEvent event) {
        int index = lstViewMiddle.getSelectionModel().getSelectedIndex();
        Playlist selectedPlaylist = tblViewLeft.getSelectionModel().getSelectedItem();
        if(selectedPlaylist != null && index != -1){
            try {
                lvSongsModel.moveUp(index, selectedPlaylist);
            } catch (DataException e) {
                createAlertDialog(e);
                handleMoveUp(event);
            }
        }
    }

    public void handleMoveDown(ActionEvent event) {
        int index = lstViewMiddle.getSelectionModel().getSelectedIndex();
        Playlist selectedPlaylist = tblViewLeft.getSelectionModel().getSelectedItem();
        if(selectedPlaylist != null && index != -1){
            try {
                lvSongsModel.moveDown(index, selectedPlaylist);
            } catch (DataException e) {
                createAlertDialog(e);
                handleMoveDown(event);
            }
        }
    }

    public void handleRemoveFromPlaylist(ActionEvent event) {
        Song selectedSong = lstViewMiddle.getSelectionModel().getSelectedItem();
        Playlist selectedPlaylist = tblViewLeft.getSelectionModel().getSelectedItem();
        int index = lstViewMiddle.getSelectionModel().getSelectedIndex();
        if(selectedSong != null && selectedPlaylist != null){
            selectedPlaylist.remove(index);
            try {
                this.lvSongsModel.delete(selectedPlaylist, index);
            } catch (DataException e) {
                createAlertDialog(e);
                handleRemoveFromPlaylist(event);
            }
        }
    }


    /*
        TOP PART
     */
    public void handlePlay(ActionEvent event) {
        System.out.println("Play");
        if(this.tempPlaylist != null && this.playlistPlaying == null){
            this.playlistPlaying = this.tempPlaylist;
            this.tempPlaylist.play();
        }
    }

    public void handleStop(ActionEvent event){
        System.out.println("Stop");
        if(this.playlistPlaying != null){
            this.playlistPlaying.stop();
            this.playlistPlaying = null; //optional
        }
    }

    public void handlePrevious(ActionEvent event) {
        System.out.println("Previous");
        if(this.playlistPlaying != null){
            this.playlistPlaying.previous();
        }
    }

    public void handleNext(ActionEvent event) {
        System.out.println("Next");
        if(this.playlistPlaying != null){
            this.playlistPlaying.next();
        }
    }

    public void handleVolume(MouseEvent mouseEvent) {
        System.out.println("Change of Volume");
        if(this.playlistPlaying != null){
            this.playlistPlaying.changeVolume(sldrVolume.getValue());
        }
    }

    public void handleFilter(ActionEvent event) {
        String query = this.txtFieldFilter.getText().toLowerCase();
        try {
            tvSongsModel.filter(query);
        } catch (DataException e) {
            createAlertDialog(e);
            handleFilter(event);
        }
    }
}
