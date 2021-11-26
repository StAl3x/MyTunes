package mytunes.gui.controller;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;

public class MyTunesController {


    public ListView lstViewMiddle;
    public TableView tblViewLeft;
    public Label lblSongPlaying;
    /*
        RIGHT SIDE
     */
    public TableView tblViewRight;

    public void handleNewSong(ActionEvent event) {
        System.out.println("New Song");
    }

    public void handleEditSong(ActionEvent event) {
        System.out.println("Edit Song");

    }

    public void handleDeleteSong(ActionEvent event) {
        System.out.println("Delete Song");

    }

    public void handleSongToPlaylist(ActionEvent event) {
        System.out.println("To Playlist");

    }

    /*
        LEFT SIDE
     */
    public void handleNewPlaylist(ActionEvent event) {
        System.out.println("New Playlist");

    }

    public void handleEditPlaylist(ActionEvent event) {
        System.out.println("Edit Playlist");

    }

    public void handleDeletePlaylist(ActionEvent event) {
        System.out.println("Delete Playlist");

    }

    /*
        MIDDLE PART
     */
    public void handleMoveUp(ActionEvent event) {
        System.out.println("Move Up");

    }

    public void handleMoveDown(ActionEvent event) {
        System.out.println("Move Down");

    }

    public void handleRemoveFromPlaylist(ActionEvent event) {
        System.out.println("Remove From Playlist");

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
