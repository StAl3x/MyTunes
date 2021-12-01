package mytunes.gui.controller;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class NewPlaylistController{
    public TextField txtFieldPlaylistName;

    public String getPlaylistName()
    {
        return this.txtFieldPlaylistName.getText();
    }

    public void setPlaylistName(String name){this.txtFieldPlaylistName.setText(name);}



}
