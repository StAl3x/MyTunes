package mytunes.be;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Gramophone {
        private Media song;
    MediaPlayer mediaPlayer;
        public Gramophone(Song song){
            File f = new File(song.getSource());
            this.song = new Media(f.toURI().toString());
        }

        public void play(){
            mediaPlayer = new MediaPlayer(this.song);
            mediaPlayer.play();
        }

        public void stop(){
            mediaPlayer.stop();
        }

        public Duration getTimeOfPause(){
        return this.song.getDuration();
        }


        public void setVolume(double volumeValue){
            this.mediaPlayer.setVolume(volumeValue);
            System.out.println(mediaPlayer.getVolume());
        }





}
