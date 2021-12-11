package mytunes.gui.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import mytunes.be.Playlist;
import mytunes.be.Song;
import mytunes.gui.controller.PlaylistDialogController;

import java.io.IOException;
import java.util.ArrayList;

public class PlaylistDialog extends Dialog<Playlist>{

    private PlaylistDialogController controller;

    public PlaylistDialog(){
        super();
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("PlaylistDialogView.fxml"));
            DialogPane dp = loader.load();
            controller = loader.getController();
            this.setTitle("Add/Edit Playlist");
            this.setDialogPane(dp);
            this.setResultConverter(buttonType -> {
                if(buttonType == ButtonType.APPLY){
                    return new Playlist(controller.getPlaylistName());
                }
                return null;
            });

        } catch (IOException ioex){
            System.out.println("Couldn't load view!");
        }
    }

    public void setFields(Playlist playlist){
        controller.setPlaylistName(playlist.getName());
    }
}
