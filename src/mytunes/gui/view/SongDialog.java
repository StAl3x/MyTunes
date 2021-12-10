package mytunes.gui.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import mytunes.be.Song;
import mytunes.gui.controller.SongDialogController;

import java.io.IOException;

public class SongDialog extends Dialog<Song>{

    private SongDialogController controller;

    public SongDialog(){
        super();
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("SongDialogView.fxml"));
            DialogPane dp = loader.load();
            controller = loader.getController();
            this.setTitle("Add/Edit Song");
            this.setDialogPane(dp);
            this.setResultConverter(buttonType -> {
                if(buttonType == ButtonType.APPLY){
                    return new Song(controller.getTitle(), controller.getArtist(), controller.getGenre(), controller.getTime(), controller.getPath());
                }
                return null;
            });

        } catch (IOException ioex){
            System.out.println("Couldn't load view!");
        }
    }

    public void setFields(Song song){
        controller.setTitle(song.getTitle());
        controller.setArtist(song.getArtist());
        controller.setGenre(song.getGenre());
        controller.setTime(song.getTime());
        controller.setSource(song.getSource());
    }
}
