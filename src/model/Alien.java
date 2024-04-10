package model;
import javafx.scene.image.Image;

// @author: MohammadHossein Rezaei

public class Alien {
	private int xPosition;
	private int yPosition;
	private Image image;

	public Alien(int xPos, int yPos, String fileName) {
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

    public void moveLeft() {
        this.xPosition -= 1;
    }

    public void moveRight() {
        this.xPosition += 1;
    }

	public Image getImage() {
		return this.image;
	}

    public Bullet shoot() {
        return new Bullet(this.xPosition, this.yPosition, "bullet2.png");
    }

}
