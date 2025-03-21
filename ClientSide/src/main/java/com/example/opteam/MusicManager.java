package com.example.opteam;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.nio.file.Paths;

public class MusicManager {

    private static MediaPlayer mediaPlayer;
    private static MediaPlayer spinPlayer;

    public static void playBackgroundMusic() {
        if (mediaPlayer == null) {
            String musicPath = Paths.get("src/main/resources/com/example/opteam/football_match_background.mp3").toUri().toString();
            Media media = new Media(musicPath);
            mediaPlayer = new MediaPlayer(media);
            mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE); // Loop music

            mediaPlayer.setVolume(0.3); // Set volume (30% of max)
        }
        mediaPlayer.play();
    }

    public static void clickSound(){
        String soundPath = Paths.get("src/main/resources/com/example/loginpage/button-click.mp3").toUri().toString();
        Media clickMedia = new Media(soundPath);
        MediaPlayer clickPlayer = new MediaPlayer(clickMedia);

        clickPlayer.setVolume(0.5); // Adjust click sound volume
        clickPlayer.setOnEndOfMedia(clickPlayer::dispose); // Free resources after playing
        clickPlayer.play();
    }

    public static void spinwheel() {
        if (spinPlayer == null) {
            String soundPath = Paths.get("src/main/resources/com/example/loginpage/spinwheel.mp3").toUri().toString();
            Media spinMedia = new Media(soundPath);
            spinPlayer = new MediaPlayer(spinMedia);
        }
        spinPlayer.setVolume(0.5); // Adjust spin sound volume
        spinPlayer.setOnEndOfMedia(spinPlayer::stop); // Stop playback after media ends
        spinPlayer.play();
    }

    public static void stopSpinwheelSound() {
        if (spinPlayer != null) {
            spinPlayer.stop();
            spinPlayer.dispose(); // Release resources
            spinPlayer = null;   // Reset the player reference
        }
    }

    public static void stopBackgroundMusic() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
        }
    }
}
