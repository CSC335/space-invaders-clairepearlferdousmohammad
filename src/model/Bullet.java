// @author: Frances Pearl McNabb
package model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

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

	public float getX() {
		return this.xPosition;
	}

	public float getY() {
		return this.yPosition;
	}

	public void moveUp() {
		this.yPosition -= 1;
	}

	public void moveDown() {
		this.yPosition += 1;
	}

	public Image getImage() {
		return this.image;
	}
	
	public void draw(GraphicsContext gc) {
		gc.drawImage(image, xPosition, yPosition);
	}
	
	public void fire() {
		if (!this.alienShoot) {
			while (this.yPosition >= 0) {
				moveUp();
				try {
					// if waiting 2 milliseconds, it will take 1200 seconds to go across the screen
					  Thread.sleep(2);
					} catch (InterruptedException e) {
					  Thread.currentThread().interrupt();
					}
			}
		}
		else {
			while (this.yPosition <= 600) {
				moveDown();
				try {
					  Thread.sleep(2);
					} catch (InterruptedException e) {
					  Thread.currentThread().interrupt();
					}
			}
		}
	}
}
