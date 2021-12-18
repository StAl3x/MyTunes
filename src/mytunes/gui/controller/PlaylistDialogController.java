package mytunes.gui.controller;

import javafx.scene.control.TextField;

public class PlaylistDialogController {
    public TextField txtFieldPlaylistName;

    public String getPlaylistName() {
        return this.txtFieldPlaylistName.getText();
    }

    public void setPlaylistName(String name) {
        this.txtFieldPlaylistName.setText(name);
    }
}
