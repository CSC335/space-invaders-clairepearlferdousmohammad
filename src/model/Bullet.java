// @author: Frances Pearl McNabb
package model;

import javafx.scene.image.Image;

public class Bullet {

	private float xPosition;
	private float yPosition;
	private Image image;

	public Bullet(float x_1Position, float y_1Position, String fileName) {
		this.xPosition = x_1Position;
		this.yPosition = y_1Position;
		this.image = new Image(getClass().getResourceAsStream(fileName));
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
}
