package model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

/**
 * Represents an instance of a bullet within a Space Invaders game.
 * 
 * @author Pearl McNabb and MohammadHossein Rezaei and Claire Lodermeier
 */

public class Bullet {

	private float xPosition1;
	private float xPosition2;
	private float yPosition1;
	private float yPosition2;
	private boolean alienShoot;
	private int height, width;

	/**
	 * Constructs a new Bullet object.
	 *
	 * @param xPosition1  the initial x position of the bullet
	 * @param xPosition2  the final x position of the bullet
	 * @param alienFire   indicates whether the bullet is fired by an alien or not
	 */
	public Bullet(float xPosition1, float yPosition1, boolean alienFire) {
		this.height = 15; 
		this.width = 2; 
		this.xPosition1 = xPosition1;
		this.xPosition2 = xPosition1 + width;
		this.yPosition1 = yPosition1;
		this.yPosition2 = yPosition1 + height;
		this.alienShoot = alienFire;
	}

	/**
	 * Gets the first x-coordinate position of the bullet
	 *
	 * @return xPosition1 The current first x position of the bullet
	 */
	public float getXPosition1() {
		return this.xPosition1;
	}

	/**
	 * Gets the second x-coordinate position of the bullet
	 *
	 * @return xPosition2 The current second x position of the bullet
	 */
	public float getXPosition2() {
		return this.xPosition2;
	}

	/**
	 * Gets the first y-coordinate position of the bullet
	 *
	 * @return yPosition1 The current first y position of the bullet
	 */
	public float getYPosition1() {
		return this.yPosition1;
	}

	/**
	 * Gets the second y-coordinate position of the bullet
	 *
	 * @return yPosition2 The current second y position of the bullet
	 */
	public float getYPosition2() {
		return this.yPosition2;
	}

	public void draw(GraphicsContext gc) {
		if(this.alienShoot) {
			gc.setFill(Color.ORANGE);
			gc.setStroke(Color.ORANGE);
		}
		else {
			gc.setFill(Color.CYAN);
			gc.setStroke(Color.CYAN);
		}


		gc.strokeRect(xPosition1, yPosition1, width, height);
		gc.fillRect(xPosition1, yPosition1, width, height);
	}

	/**
	 * Moves the bullet up or down, depending on shooter. 
	 * 	 
	 */
	public void move(double distance) {
		if (alienShoot) {
			// moves down
			this.yPosition1 += distance;
			this.yPosition2 += distance;
		} else {
			// moves up
			this.yPosition1 -= distance;
			this.yPosition2 -= distance;
		}
	}

	/**
	 * If an alien is firing down, the bullet moves down. If the tank is firing, the
	 * bullet moves up. Both with sleep to make the bullet seem as though it is
	 * moving and both move until hitting the edge of the canvas.
	 
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
	*/
}
