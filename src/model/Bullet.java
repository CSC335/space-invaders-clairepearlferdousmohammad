package model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 * Represents an instance of a bullet within a Space Invaders game.
 * 
 * @author Pearl McNabb
 */

public class Bullet {

	private float xPosition;
	private float yPosition;
	private boolean alienShoot;
	private Image image;

	public Bullet(float x_1Position, float y_1Position, String fileName, boolean alienFire) {
		this.xPosition = x_1Position;
		this.yPosition = y_1Position;
		this.image = new Image(getClass().getResourceAsStream(fileName));
		this.alienShoot = alienFire;
	}

	/**
	 * Gets the x-coordinate position of the bullet
	 * 
	 * @return xPosition The current x position of the bullet
	 */
	public float getX() {
		return this.xPosition;
	}

	/**
	 * Gets the y-coordinate position of the bullet
	 * 
	 * @return yPosition The current y position of the bullet
	 */
	public float getY() {
		return this.yPosition;
	}

	/**
	 * Decreases the y-coordinate position of the bullet by 1 Moves the bullet up in
	 * terms of the canvas coordinate system
	 */
	public void moveUp() {
		this.yPosition -= 1.0;
	}

	/**
	 * Increases the y-coordinate position of the bullet by 1 Moves the bullet up in
	 * terms of the canvas coordinate system
	 */
	public void moveDown() {
		this.yPosition += 1.0;
	}

	/**
	 * Gets the image to put on the canvas to represent the bullet
	 * 
	 * @return image The image that should be shown to represent the bullet
	 */
	public Image getImage() {
		return this.image;
	}

	/**
	 * Draws the bullet using the given image onto the graphics context
	 * 
	 * @param gc GraphicsContext gc that is used in the GUI
	 */
	public void draw(GraphicsContext gc) {
		gc.drawImage(image, xPosition, yPosition);
	}

	/**
	 * If an alien is firing down, the bullet moves down. If the tank is firing, the
	 * bullet moves up. Both with sleep to make the bullet seem as though it is
	 * moving and both move until hitting the edge of the canvas.
	 */
	public void fire() {
		if (!this.alienShoot) {
			while (this.yPosition >= 1) {
				try {
					// if waiting 2 milliseconds, it will take 1200 seconds to go across the screen
					Thread.sleep(1);
				} catch (InterruptedException e) {
					Thread.currentThread().interrupt();
				}
				moveUp();
			}
		} else {
			while (this.yPosition <= 599.0) {
				try {
					Thread.sleep(1);
				} catch (InterruptedException e) {
					Thread.currentThread().interrupt();
				}
				moveDown();
			}
		}
	}
}
