package mytunes.gui.controller;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import mytunes.be.SongGenre;
import mytunes.gui.model.ChoiceBoxGenresModel;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class SongDialogController implements Initializable {

    public TextField txtFieldTitle;
    public TextField txtFieldArtist;
    public TextField txtFieldTime;
    public TextField txtFieldFile;

    public ChoiceBox<SongGenre> choiceBoxGenre;
    private ChoiceBoxGenresModel choiceModel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        choiceModel = new ChoiceBoxGenresModel();
        choiceBoxGenre.getItems().addAll(choiceModel.getAllGenres());
        choiceBoxGenre.setValue(SongGenre.values()[0]);
    }

    public String getTitle() {
        return this.txtFieldTitle.getText();
    }

    public String getArtist() {
        return this.txtFieldArtist.getText();
    }

    public SongGenre getGenre() {
        return this.choiceBoxGenre.getValue();
    }

    public int getTime() {
        return Integer.parseInt(this.txtFieldTime.getText());
    }

    public String getPath() {
        return this.txtFieldFile.getText();
    }

    public void setTitle(String title) {
        this.txtFieldTitle.setText(title);
    }

    public void setArtist(String artist) {
        this.txtFieldArtist.setText(artist);
    }

    public void setGenre(SongGenre genre) {
        this.choiceBoxGenre.setValue(genre);
    }

    public void setTime(String time) {
        this.txtFieldTime.setText(time);
    }

    public void setSource(String path) {
        this.txtFieldFile.setText(path);
    }

    public void handleChoose(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select file resource");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("MP3 File", "*.mp3"),
                new FileChooser.ExtensionFilter("WAV File", "*.wav")
        );
        Node source = (Node) event.getSource();
        File file = fileChooser.showOpenDialog(source.getScene().getWindow());
        if (file != null) {
            String filePath = file.getPath();
            txtFieldFile.setText(filePath);
        }
    }
}
