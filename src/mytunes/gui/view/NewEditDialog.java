package mytunes.gui.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import mytunes.be.Song;
import mytunes.gui.controller.NewEditController;

import java.io.IOException;

public class NewEditDialog extends Dialog<Song>{

    public NewEditDialog(){
        super();
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("NewEditView.fxml"));
            DialogPane dp = loader.load();
            NewEditController controller = loader.getController();
            this.setTitle("Add/Edit Song");
            this.setDialogPane(dp);
            this.setResultConverter(buttonType -> {
                if(buttonType == ButtonType.APPLY){
                    return new Song(controller.getTitle(), controller.getArtist(), controller.getGenre(), controller.getPath());
                }
                return null;
            });

        } catch (IOException ioex){
            System.out.println("Couldn't load view!");
        }
    }
}
