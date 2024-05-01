package model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 * @author MohammadHossein Rezaei The scoreBooster class represents a score
 *         booster object in the game. It provides methods to activate,
 *         deactivate, draw, and check for collision with the player tank.
 */
public class scoreBooster {

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
	 * Constructs a scoreBooster object with the specified y1 position.
	 *
	 * @param y1Pos the y1 position of the score booster
	 */
	public scoreBooster(float y1Pos) {
		this.y1Position = y1Pos;
		this.height = 40;
		this.width = 50;
		this.isActivated = false;
		this.respawningCounter = 0;
		this.image = new Image(getClass().getResourceAsStream("booster.png"));
	}

	/**
	 * Activates the score booster. Sets the isActivated flag to true and randomly
	 * sets the x1 position.
	 */
	public void activate() {
		this.isActivated = true;
		this.x1Position = (float) (Math.random() * 530);
	}

	/**
	 * Deactivates the score booster. Sets the isActivated flag to false and resets
	 * the respawning counter.
	 */
	public void deactivate() {
		this.isActivated = false;
		this.respawningCounter = 0;
	}

	/**
	 * Gets the image of the score booster. Increases the respawning counter and
	 * checks if it exceeds the threshold to deactivate the booster. Returns the
	 * image if the booster is active, otherwise returns null.
	 *
	 * @return the image of the score booster or null if the booster is not active
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
	 * Draws the score booster on the specified GraphicsContext. Only draws the
	 * booster if it is activated and has a valid image.
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
	 * Checks if the score booster collides with the player tank. Returns true if
	 * there is a collision and deactivates the booster, otherwise returns false.
	 *
	 * @param playerTank the player tank to check collision with
	 * @return true if there is a collision, false otherwise
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
