package mytunes.gui.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import mytunes.be.Playlist;
import mytunes.be.Song;
import mytunes.gui.controller.NewPlaylistController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class NewPlaylistDialog extends Dialog<Playlist>{

    private NewPlaylistController controller;

    public NewPlaylistDialog(){
        super();
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("NewPlaylistView.fxml"));
            DialogPane dp = loader.load();
            controller = loader.getController();
            this.setTitle("Create Playlist");
            this.setDialogPane(dp);
            this.setResultConverter(buttonType -> {
                if(buttonType == ButtonType.APPLY){
                    return new Playlist(controller.getPlaylistName(), new ArrayList<Song>());
                }
                return null;
            });

        } catch (IOException ioex){
            System.out.println("Couldn't load view!");
        }
    }
}
