package model;

/**
 * Represents an instance of a Space Invaders game. 
 * 
 * @author Claire Lodermeier
 */

public class Game {

	private int score; 
	private int numLives;
	private boolean gameOver;
	
	public Game() {
		score = 0;
		numLives = 3; 
		gameOver = false;
	}
	
	/**
	 * Sets the score attribute of the game. 
	 * 
	 * @param score		The new score
	 *  
	 */
	public void setScore(int score) {
		this.score = score;
	}
	
	/**
	 * Sets the gameOver attribute of the game. 
	 * 
	 * @param gameOver	Boolean for whether the game is over
	 *  
	 */
	public void setGameOver(boolean gameOver) {
		this.gameOver = gameOver;
	}
	
	/**
	 * Gets the score attribute of the game. 
	 * 
	 * @return score		The current score of the game
	 *  
	 */
	public int getScore() {
		return score;
	}
	
	/**
	 * Increments the score attribute of the game by amount.
	 * 
	 * @param amount		The amount to add
	 *  
	 */
	public void incrementScore(int amount) {
		score += amount;
	}
	
	/**
	 * Gets the numLives attribute of the game. 
	 * 
	 * @return		The current number of lives in the game
	 *  
	 */
	public int getNumLives() {
		return numLives;
	}
	
	/**
	 * Gets the gameOver attribute of the game. 
	 * 
	 * @return gameOver		the gameOver boolean (true if game is over, false if still playing)
	 *  
	 */
	public boolean getGameOver() {
		return gameOver;
	}
	
	/**
	 * Decrements the current number of lives by 1. 
	 *  
	 */
	public void decrementLives() {
		numLives--;
	}
}
