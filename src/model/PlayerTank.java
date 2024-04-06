// @author: Frances Pearl McNabb
package model;

import javafx.scene.image.Image;

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
	
	public int getX() {
		return this.xPosition;
	}
	
	public int getY() {
		return this.yPosition;
	}
	
	public void moveLeft() {
		this.xPosition -= 1;
	}
	
	public void moveRight() {
		this.xPosition += 1;
	}

	public int getDamages() {
		return damages;
	}
	
	public Image getImage() {
		return image;
	}

	public void addDamages(int damages) {
		this.damages += damages;
	}
	
}
