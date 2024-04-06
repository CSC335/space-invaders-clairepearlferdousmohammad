// @author: Frances Pearl McNabb
package model;

public class PlayerTank {
	
	private int damages;
	private int xPosition;
	private int yPosition;
	private String imageFile;
	
	public PlayerTank(int xPos, int yPos) {
		this.damages = 0;
		this.xPosition = xPos;
		this.yPosition = yPos;
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

	public void addDamages(int damages) {
		this.damages += damages;
	}
	
}
