package model;

public class Bullet {

	private int xPosition;
	private int yPosition;
	private String imageName;
	
	public Bullet(int xPos, int yPos, String fileName) {
		this.xPosition = xPos;
		this.yPosition = yPos;
		this.imageName = fileName;
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
	
	public String getImage() {
		return this.imageName;
	}
}
