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
	
	public void setScore(int score) {
		this.score = score;
	}

	public void setGameOver(boolean gameOver) {
		this.gameOver = gameOver;
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
	
	public void decrementLives() {
		numLives--;
	}
}
