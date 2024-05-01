package model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 * @author MohammadHossein Rezaei and Ferdous Zubair Khan The fastBullet class
 *         represents a fast bullet object in the game. It is responsible for
 *         managing the activation, deactivation, and drawing of the bullet. It
 *         also checks for collisions with the player tank.
 */
public class fastBullet {

	GraphicsContext gc;
	Image image;
	float x1Position;
	float x2Position;
	float y1Position;
	float y2Position;
	float height;
	float width;
	public Boolean isActivated;
	float respawningCounter;

	/**
	 * Constructs a fastBullet object with the specified y1 position.
	 * 
	 * @param y1Pos the y1 position of the bullet
	 */
	public fastBullet(float y1Pos) {
		this.y1Position = y1Pos;
		this.height = 40;
		this.width = 50;
		this.isActivated = false;
		this.respawningCounter = 0;
		this.image = new Image(getClass().getResourceAsStream("fast.png"));
	}

	/**
	 * Activates the bullet by setting the isActivated flag to true and generating a
	 * random x1 position.
	 */
	public void activate() {
		this.isActivated = true;
		this.x1Position = (float) (Math.random() * 530);
	}

	/**
	 * Deactivates the bullet by setting the isActivated flag to false and resetting
	 * the respawning counter.
	 */
	public void deactivate() {
		this.isActivated = false;
		this.respawningCounter = 0;
	}

	/**
	 * Gets the image of the bullet.
	 * 
	 * @return the image of the bullet, or null if the bullet is respawning
	 */
	public Image getImage() {
		respawningCounter++;
		if (respawningCounter > 60) {
			this.deactivate();
		}

		if (respawningCounter % 4 < 2) {
			return null;
		} else {
			return this.image;
		}
	}

	/**
	 * Draws the bullet on the specified GraphicsContext.
	 * 
	 * @param gc the GraphicsContext to draw on
	 */
	public void draw(GraphicsContext gc) {
		if (isActivated) {
			if (this.getImage() != null) {
				gc.drawImage(this.image, this.x1Position, this.y1Position, this.width, this.height);
			}
		}
	}

	/**
	 * Checks if the bullet hits the player tank.
	 * 
	 * @param playerTank the player tank to check for collision with
	 * @return true if the bullet hits the player tank, false otherwise
	 */
	public Boolean doesHit(PlayerTank playerTank) {
		if (playerTank.getX1() < this.x1Position + this.width && playerTank.getX2() > this.x1Position
				&& playerTank.getY1() < this.y1Position + this.height && playerTank.getY2() > this.y1Position) {
			this.deactivate();
			return true;
		} else {
			return false;
		}
	}

}
