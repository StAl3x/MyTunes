package mytunes.gui.controller;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import mytunes.be.Gramophone;
import mytunes.be.Playlist;
import mytunes.be.Song;
import mytunes.be.SongGenre;
import mytunes.bll.MyTunesBusiness;
import mytunes.gui.model.ListViewSongsModel;
import mytunes.gui.model.TableViewPlaylistsModel;
import mytunes.gui.model.TableViewSongsModel;
import mytunes.gui.view.NewEditDialog;
import mytunes.gui.view.NewPlaylistDialog;

import java.net.URL;
import java.sql.SQLException;
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
    //MyTunesBusiness myTunesBusiness = new MyTunesBusiness();

    public MyTunesController() throws SQLException {
    }

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
        //tblColumnTime.setCellValueFactory(new PropertyValueFactory<>("time"));

        //left side
        tblColumnName.setCellValueFactory(new PropertyValueFactory<>("name"));
        //tblColumnSongs.setCellValueFactory(new PropertyValueFactory<>(""));
        //tblColumnPlaylistTime.setCellValueFactory(new PropertyValueFactory<>(""));

        Song mySong = new Song("PepoSad", "Lil Pepo", SongGenre.Rap, "C:\\UniStuff\\MyTunes\\MyTunes\\src\\mockData\\bensound-ukulele.mp3");
        Song myNewSong = new Song("PepoHappy", "Lil Pepo", SongGenre.Rap, "C:\\UniStuff\\MyTunes\\MyTunes\\src\\mockData\\bensound-anewbeginning.mp3");
        Song myMadSong = new Song("PepoSmash", "Lil Pepo", SongGenre.Rap, "C:\\UniStuff\\MyTunes\\MyTunes\\src\\mockData\\bensound-creativeminds.mp3");
        Song myGladSong = new Song("PepoSmile", "Lil Pepo", SongGenre.Rap, "C:\\UniStuff\\MyTunes\\MyTunes\\src\\mockData\\bensound-littleidea.mp3");


        Playlist myPlaylist = new Playlist("My sad playlist");
        myPlaylist.addSong(mySong);
        myPlaylist.addSong(myMadSong);
        myPlaylist.addSong(myNewSong);
        myPlaylist.addSong(myGladSong);
        tblViewLeft.getItems().add(myPlaylist);
        tblViewRight.getItems().add(mySong);
        tblViewRight.getItems().add(myNewSong);
        tblViewRight.getItems().add(myMadSong);
        tblViewRight.getItems().add(myGladSong);

    }

    /*
        RIGHT SIDE
     */

    /*
        Opens a Dialog for creating a new Song
        adds the Song when APPLY is clicked
     */
    public void handleNewSong(ActionEvent event) {
        System.out.println("New Song");
        NewEditDialog dialog = new NewEditDialog();
        Optional<Song> result = dialog.showAndWait();
        result.ifPresent(response -> {
            tvSongsModel.addSong(response);
            System.out.println(response);
        });
    }

    /*
        Get selected Item (Song) from Right TableView,
        if Song is selected, open a Dialog & fill its fields with the Song properties
        (=> nice UX - they don't have to retype everything everytime)
        edit the song when APPLY is clicked
     */
    public void handleEditSong(ActionEvent event) {
        System.out.println("Edit Song");
        Song selectedSong = tblViewRight.getSelectionModel().getSelectedItem();
        if(selectedSong != null){
            NewEditDialog dialog = new NewEditDialog();
            dialog.setFields(selectedSong);
            Optional<Song> result = dialog.showAndWait();
            result.ifPresent(response -> {
                tvSongsModel.edit(selectedSong, response);
                System.out.println(response);
            });
        }
    }

    public void handleDeleteSong(ActionEvent event) {
        System.out.println("Delete Song");
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
        if(selectedSong != null) {
            Song newSong = new Song(selectedSong.getTitle(), selectedSong.getArtist(), selectedSong.getGenre(), selectedSong.getSource());
            lvSongsModel.addOneSongToListView(newSong);
            selectedPlaylist.addSong(newSong);
        }
    }

    /*
        LEFT SIDE
     */
    public void handleNewPlaylist(ActionEvent event) {
        System.out.println("New Playlist");
        NewPlaylistDialog dialog = new NewPlaylistDialog();
        Optional<Playlist> result = dialog.showAndWait();
        result.ifPresent(response -> {
            tvPlaylistsModel.addPlaylist(response);
            System.out.println(response);
        });
    }


    public void handleEditPlaylist(ActionEvent event) {
        System.out.println("Edit Playlist");
        Playlist selectedPlaylist = tblViewLeft.getSelectionModel().getSelectedItem();
        if(selectedPlaylist != null){
            NewPlaylistDialog dialog = new NewPlaylistDialog();
            dialog.setFields(selectedPlaylist);
            Optional<Playlist> result = dialog.showAndWait();
            result.ifPresent(response -> {
                tvPlaylistsModel.edit(selectedPlaylist, response);
                System.out.println(response);
            });
        }

    }

    public void handleDeletePlaylist(ActionEvent event) {
        System.out.println("Delete Playlist");
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
            List<Song> selectedPlaylistSongs = tblViewLeft.getSelectionModel().getSelectedItem().getSongs();
            lvSongsModel.deleteSongsFromListView();
            lvSongsModel.addSongsToListView(selectedPlaylistSongs);
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
        List<Song> songList = lvSongsModel.getSongs();
        if(tblViewLeft.getSelectionModel().getSelectedItem() != null) {
            if (selectedSong != null) {
                selectedPlaylist.removeSong(selectedSong);
                songList.remove(selectedSong);
            }
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
        System.out.println("next");
    }

    public void handleVolume(MouseEvent mouseEvent) {
        System.out.println("Change of Volume");
    }

    public void handleFilter(ActionEvent event) {
        System.out.println("Filter");

    }



}
