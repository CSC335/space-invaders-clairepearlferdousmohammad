package view_controller;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

/// author: Ferdous Khan

public class SoundManager {
    private final Map<String, MediaPlayer> mediaPlayerMap;
    private MediaPlayer backgroundMusicPlayer;

    public SoundManager() {
        mediaPlayerMap = new HashMap<>();
        // Initialize media players for each sound effect, death noise can also work as a GameOver
        initializeMediaPlayer("Death", "SoundEffects/Death.mp3");
        initializeMediaPlayer("GameStart", "SoundEffects/GameStart.mp3");
        initializeMediaPlayer("Shooting", "SoundEffects/Shooting.mp3");
        //  background music 
        initializeBackgroundMusic("SoundEffects/BackgroundMusic.mp3");
    }

    private void initializeMediaPlayer(String soundName, String soundFilePath) {
        try {
            Media sound = new Media(new File(soundFilePath).toURI().toString());
            MediaPlayer mediaPlayer = new MediaPlayer(sound);
            mediaPlayerMap.put(soundName, mediaPlayer);
        } catch (Exception e) {
            System.err.println("Error initializing MediaPlayer for sound: " + soundName);
            e.printStackTrace();
        }
    }

    private void initializeBackgroundMusic(String musicFilePath) {
        try {
            Media sound = new Media(new File(musicFilePath).toURI().toString());
            backgroundMusicPlayer = new MediaPlayer(sound);
            backgroundMusicPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        } catch (Exception e) {
            System.err.println("Error initializing MediaPlayer for background music.");
            e.printStackTrace();
        }
    }

    public void playSound(String soundName) {
        MediaPlayer mediaPlayer = mediaPlayerMap.get(soundName);
        if (mediaPlayer != null) {
            if (mediaPlayer.getStatus() == MediaPlayer.Status.PLAYING) {
                mediaPlayer.stop();
            }
            mediaPlayer.seek(Duration.ZERO);
            mediaPlayer.play();
        } else {
            System.err.println("MediaPlayer for sound not found: " + soundName);
        }
    }

    public void playBackgroundMusic() {
        if (backgroundMusicPlayer != null) {
            backgroundMusicPlayer.play();
        }
    }

    public void stopBackgroundMusic() {
        if (backgroundMusicPlayer != null && backgroundMusicPlayer.getStatus() == MediaPlayer.Status.PLAYING) {
            backgroundMusicPlayer.stop();
        }
    }

    public void stopSound(String soundName) {
        MediaPlayer mediaPlayer = mediaPlayerMap.get(soundName);
        if (mediaPlayer != null && mediaPlayer.getStatus() == MediaPlayer.Status.PLAYING) {
            mediaPlayer.stop();
        }
    }

    public void loopSound(String soundName) {
        MediaPlayer mediaPlayer = mediaPlayerMap.get(soundName);
        if (mediaPlayer != null) {
            mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
            playSound(soundName);
        }
    }
}