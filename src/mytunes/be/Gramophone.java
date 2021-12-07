package mytunes.be;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Gramophone {
    MediaPlayer mediaPlayer;
    private int songIndex;
    private boolean isPlaying;
    private List<Media> mediaToPlay = new ArrayList<>();
    private List<Song> songsToPlay = new ArrayList<>();
        public Gramophone(List<Song> songs){
            songsToPlay = songs;
            for(Song s: songs){
                File f = new File(s.getSource());
                mediaToPlay.add(new Media(f.toURI().toString()));
            }
        }
        //gui operations
    //play handles play and stop at the same time
        public void play(){
            mediaPlayer = new MediaPlayer(mediaToPlay.get(songIndex));
            if(!isPlaying) {
                mediaPlayer.play();
                isPlaying = true;
                //auto play = media.getDuration
            }
            else{
                mediaPlayer.stop();
                isPlaying = false;
            }
        }


        public void nextSong() {
            if (songIndex == mediaToPlay.size())
                songIndex = 0;
            else
                songIndex++;
            play();
            isPlaying = true;
        }

        public void previousSong(){
            if(songIndex == 0)
                songIndex = mediaToPlay.size()-1;
            else
                songIndex--;
            play();
            isPlaying = true;
        }



        public void setVolume(double volumeValue){
            this.mediaPlayer.setVolume(volumeValue);
            System.out.println(mediaPlayer.getVolume());
        }

        //additional methods
        public String getSongInfo(){
            return songsToPlay.get(songIndex).getTitle()+"\n"+ songsToPlay.get(songIndex).getArtist();
        }
        public String setPlayBtnValue(){
            if(isPlaying)
                return "Pause";
            else
                return "Play";
    }





}
