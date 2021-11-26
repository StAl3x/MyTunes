package mytunes.gui.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import mytunes.be.SongGenre;

import java.util.Arrays;

public class ChoiceBoxGenresModel {

    ObservableList<SongGenre> allGenres;

    public ChoiceBoxGenresModel(){
        allGenres = FXCollections.observableArrayList();
        allGenres.addAll(Arrays.asList(SongGenre.values()));
    }

    public ObservableList<SongGenre> getAllGenres(){
        return this.allGenres;
    }
}
