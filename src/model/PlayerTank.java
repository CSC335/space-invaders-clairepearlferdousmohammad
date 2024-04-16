package model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 * Represents a playerTank, movable by the player, in an instance of a Space
 * Invaders game.
 * 
 * @author Pearl McNabb
 */

public class PlayerTank {

	private int damages;
	private int xPosition;
	private int yPosition;
	private Image image;

	public PlayerTank(int xPos, int yPos) {
		this.damages = 0;
		this.xPosition = xPos;
		this.yPosition = yPos;
		this.image = new Image(getClass().getResourceAsStream("tank.png"));
	}

	/**
	 * Gets the x-coordinate position of the tank
	 * 
	 * @return xPosition The current x position of the tank
	 */
	public int getX() {
		return this.xPosition;
	}

	/**
	 * Gets the y-coordinate position of the tank
	 * 
	 * @return yPosition The current y position of the tank
	 */
	public int getY() {
		return this.yPosition;
	}

	/**
	 * Decreases the x-coordinate position of the tank by 1 Moves the tank left in
	 * terms of the canvas coordinate system
	 */
	public void moveLeft() {
		this.xPosition -= 1;
	}

	/**
	 * Increases the x-coordinate position of the tank by 1 Moves the tank right in
	 * terms of the canvas coordinate system
	 */
	public void moveRight() {
		this.xPosition += 1;
	}

	/**
	 * Gets the amount of damage that the tank has taken during the game
	 * 
	 * @return damages The current damage on the tank
	 */
	public int getDamages() {
		return damages;
	}

	/**
	 * Gets the image to put on the canvas to represent the tank
	 * 
	 * @return image The image that should be shown to represent the tank
	 */
	public Image getImage() {
		return image;
	}

	/**
	 * When the tank takes on damages, it must accumulate this amount into damages
	 * to keep track of how much damages until death
	 * 
	 * @param damages Amount of damages that the tank took
	 */
	public void addDamages(int damages) {
		this.damages += damages;
	}

	/**
	 * Draws the tank using the given image onto the graphics context
	 * 
	 * @param gc GraphicsContext gc that is used in the GUI
	 */
	public void draw(GraphicsContext gc) {
		gc.drawImage(image, xPosition, yPosition);
	}

}
