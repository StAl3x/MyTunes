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

    public TextField txtFieldFilter;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.tvSongsModel = new TableViewSongsModel();
        this.tblViewRight.setItems(tvSongsModel.getSongsList());

        this.tvPlaylistsModel = new TableViewPlaylistsModel();
        this.tblViewLeft.setItems(tvPlaylistsModel.getPlaylistList());

        this.lvSongsModel = new ListViewSongsModel();
        this.lstViewMiddle.setItems(lvSongsModel.getSongs());

        this.initTables();
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
            this.tvSongsModel.addSong(response);
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
                this.tvSongsModel.edit(selectedSong, response);
            });
        }
    }

    public void handleDeleteSong(ActionEvent event) {
        Song selectedSong = tblViewRight.getSelectionModel().getSelectedItem();
        if(selectedSong != null)
        {
            this.tvSongsModel.deleteSong(selectedSong);
            this.lvSongsModel.removeOccurrence(selectedSong);
            this.tvPlaylistsModel.refresh();
        }
    }

    public void handleSongToPlaylist(ActionEvent event) {
        Song selectedSong = tblViewRight.getSelectionModel().getSelectedItem();
        Playlist selectedPlaylist = tblViewLeft.getSelectionModel().getSelectedItem();
        int index = tblViewLeft.getSelectionModel().getSelectedIndex();

        if(selectedSong != null && selectedPlaylist != null) {
            this.lvSongsModel.addSong(selectedSong, selectedPlaylist);
            selectedPlaylist.addSong(selectedSong);
            this.tvPlaylistsModel.update(index, selectedPlaylist);
        }
    }

    /*
        LEFT SIDE
     */
    public void handleNewPlaylist(ActionEvent event) {
        PlaylistDialog dialog = new PlaylistDialog();
        Optional<Playlist> result = dialog.showAndWait();
        result.ifPresent(response -> {
            this.tvPlaylistsModel.addPlaylist(response);
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
                this.tvPlaylistsModel.edit(selectedPlaylist, response);
            });
        }

    }

    public void handleDeletePlaylist(ActionEvent event) {
        Playlist selectedPlaylist = tblViewLeft.getSelectionModel().getSelectedItem();
        if(selectedPlaylist != null)
        {
            this.tvPlaylistsModel.delete(selectedPlaylist);
            this.lvSongsModel.removeAll();
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
        }
    }

    public void handleMoveUp(ActionEvent event) {
        int index = lstViewMiddle.getSelectionModel().getSelectedIndex();
        Playlist selectedPlaylist = tblViewLeft.getSelectionModel().getSelectedItem();
        if(selectedPlaylist != null && index != -1){
            lvSongsModel.moveUp(index, selectedPlaylist);
        }
    }

    public void handleMoveDown(ActionEvent event) {
        int index = lstViewMiddle.getSelectionModel().getSelectedIndex();
        Playlist selectedPlaylist = tblViewLeft.getSelectionModel().getSelectedItem();
        if(selectedPlaylist != null && index != -1){
            lvSongsModel.moveDown(index, selectedPlaylist);
        }
    }

    public void handleRemoveFromPlaylist(ActionEvent event) {
        Song selectedSong = lstViewMiddle.getSelectionModel().getSelectedItem();
        Playlist selectedPlaylist = tblViewLeft.getSelectionModel().getSelectedItem();
        int index = lstViewMiddle.getSelectionModel().getSelectedIndex();
        if(selectedSong != null && selectedPlaylist != null){
            selectedPlaylist.remove(index);
            this.lvSongsModel.delete(selectedPlaylist, index);
        }
    }


    /*
        TOP PART
     */
    public void handlePlay(ActionEvent event) {
        System.out.println("Play");
        System.out.println(tblViewLeft.getSelectionModel().getSelectedItem());
        System.out.println(tblViewRight.getSelectionModel().getSelectedItem());
        System.out.println(lstViewMiddle.getSelectionModel().getSelectedItem());
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
        String query = this.txtFieldFilter.getText().toLowerCase();
        tvSongsModel.filter(query);
    }
}
