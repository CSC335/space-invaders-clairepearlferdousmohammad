// @author: Frances Pearl McNabb
package model;

import javafx.scene.image.Image;

public class Bullet {

	private int xPosition;
	private int yPosition;
	private Image image;

	public Bullet(int xPos, int yPos, String fileName) {
		this.xPosition = xPos;
		this.yPosition = yPos;
		this.image = new Image(getClass().getResourceAsStream(fileName));
	}

	public int getX() {
		return this.xPosition;
	}

	public int getY() {
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
