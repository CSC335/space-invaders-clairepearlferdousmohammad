package model;

public class Game {

	private int score; 
	private int numLives;
	private boolean gameOver;
	
	public Game() {
		score = 0;
		numLives = 3; 
		gameOver = false;
	}
	
	public int getScore() {
		return score;
	}
	
	public int getNumLives() {
		return numLives;
	}
	
	public boolean getGameOver() {
		return gameOver;
	}
}
