package mytunes.bll;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import mytunes.be.Gramophone;
import mytunes.be.Song;
import mytunes.dal.SongDAO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MyTunesBusiness {

    SongDAO songDAO = new SongDAO();
    private List<Song> allSongs = songDAO.getAllSongs();
    Gramophone gramophone = new Gramophone(allSongs);
    public MyTunesBusiness() throws SQLException {


    }

    private Button btnPlay;
    private Slider sldrVolume;
    private TextField txtFieldFilter;
    public Label lblSongPlaying;

    public void playSong(){
            gramophone.play();
            btnPlay.setText(gramophone.setPlayBtnValue());
            lblSongPlaying.setText(gramophone.getSongInfo());
    }


    public void nextSong(){
        gramophone.nextSong();
        lblSongPlaying.setText(gramophone.getSongInfo());
    }

    public void previousSong(){
        gramophone.previousSong();
        lblSongPlaying.setText(gramophone.getSongInfo());
    }

    public void setVolume(){
        gramophone.setVolume(sldrVolume.getValue());
    }

    public void filter(){
        List<Song> filterList = new ArrayList<>();
        String selection = txtFieldFilter.getText().toLowerCase();
        for (Song s :allSongs){
            if(s.getArtist().toLowerCase().contains(selection) ||
                    s.getGenre().toString().toLowerCase().contains(selection) ||
                    s.getTitle().toLowerCase().contains(selection))
                filterList.add(s);
        }
    }

}
