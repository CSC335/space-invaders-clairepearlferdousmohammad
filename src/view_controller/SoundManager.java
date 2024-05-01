package view_controller;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Ferdous Khan
 * 
 *         Manages the sounds for the entire space invaders game including the
 *         starting music, the game music, the shooting sound, and the game over
 *         sound.
 */

public class SoundManager {
	private final Map<String, MediaPlayer> mediaPlayerMap;
	private MediaPlayer backgroundMusicPlayer;

	public SoundManager() {
		mediaPlayerMap = new HashMap<>();
		// Initialize media players for each sound effect, death noise can also work as
		// a GameOver
		initializeMediaPlayer("Death", "SoundEffects/Death.mp3");
		initializeMediaPlayer("GameStart", "SoundEffects/GameStart.mp3");
		initializeMediaPlayer("Shooting", "SoundEffects/Shooting.mp3");
		// background music
		initializeBackgroundMusic("SoundEffects/BackgroundMusic.mp3");
	}

	/**
	 * Creates a MediaPlayer for the desired song to play in the game
	 * 
	 * @param soundName,     the name of the sound to initialize in the media player
	 * @param soundFilePath, the file path for the sound to initialize in the player
	 */
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

	/**
	 * Creates a player for the background music and plays it indefinitely - the end
	 * of the game/music ending is dealt with in other methods
	 * 
	 * @param musicFilePath, the filepath to the sound for the background music
	 */
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

	/**
	 * With the name of the song, and the initialized media player, the desired song
	 * is played during the space invaders game
	 * 
	 * @param soundName, the name of the song to play
	 */
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

	/**
	 * plays the background music
	 */
	public void playBackgroundMusic() {
		if (backgroundMusicPlayer != null) {
			backgroundMusicPlayer.play();
		}
	}

	/**
	 * stops the background music from playing since it's playing indefinitely, used
	 * at the end of the game
	 */
	public void stopBackgroundMusic() {
		if (backgroundMusicPlayer != null && backgroundMusicPlayer.getStatus() == MediaPlayer.Status.PLAYING) {
			backgroundMusicPlayer.stop();
		}
	}

	/**
	 * Stops playing the desired song
	 * 
	 * @param soundName, the name of the song to stop playing
	 */
	public void stopSound(String soundName) {
		MediaPlayer mediaPlayer = mediaPlayerMap.get(soundName);
		if (mediaPlayer != null && mediaPlayer.getStatus() == MediaPlayer.Status.PLAYING) {
			mediaPlayer.stop();
		}
	}

	/**
	 * Indefinitely loops the desired song
	 * 
	 * @param soundName, the name of the song to loop
	 */
	public void loopSound(String soundName) {
		MediaPlayer mediaPlayer = mediaPlayerMap.get(soundName);
		if (mediaPlayer != null) {
			mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
			playSound(soundName);
		}
	}
}