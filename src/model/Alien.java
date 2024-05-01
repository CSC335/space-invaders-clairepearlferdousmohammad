package model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

// @author: MohammadHossein Rezaei

/**
 * The Alien class represents an alien object in a game. It contains methods to
 * move the alien, shoot bullets, and retrieve its image.
 */
public abstract class Alien {
	private float x_1Position;
	private float x_2Position;
	private float y_1Position;
	private float y_2Position;
	private Image image;
	private Image image2;
	private boolean armUp = true;
	private int flipIndex = 0;

	/**
	 * Constructs an Alien object with the specified positions and image file.
	 *
	 * @param x1Pos    the x-coordinate of the first position top left
	 * @param x2Pos    the x-coordinate of the second position top right
	 * @param y1Pos    the y-coordinate of the first position top left
	 * @param y2Pos    the y-coordinate of the second position bottom left
	 * @param fileName the file name of the image
	 */
	public Alien(float x1Pos, float x2Pos, float y1Pos, float y2Pos, String fileName, String fileName2) {
		this.x_1Position = x1Pos;
		this.x_2Position = x2Pos;
		this.y_1Position = y1Pos;
		this.y_2Position = y2Pos;
		this.image = new Image(getClass().getResourceAsStream(fileName));
		this.image2 = new Image(getClass().getResourceAsStream(fileName2));
	}

	/**
	 * Returns the x-coordinate of the first position of the alien.
	 *
	 * @return the x-coordinate of the first position
	 */
	public float getX1() {
		return this.x_1Position;
	}

	/**
	 * Returns the x-coordinate of the second position of the alien.
	 *
	 * @return the x-coordinate of the second position
	 */
	public float getX2() {
		return this.x_2Position;
	}

	/**
	 * Returns the y-coordinate of the first position of the alien.
	 *
	 * @return the y-coordinate of the first position
	 */
	public float getY1() {
		return this.y_1Position;
	}

	/**
	 * Returns the y-coordinate of the second position of the alien.
	 *
	 * @return the y-coordinate of the second position
	 */
	public float getY2() {
		return this.y_2Position;
	}

	/**
	 * Moves the alien up by the specified distance.
	 *
	 * @param distance the distance to move up
	 */
	public void moveUp(float distance) {
		this.y_1Position -= distance;
		this.y_2Position -= distance;
	}

	/**
	 * Moves the alien down by the specified distance.
	 *
	 * @param d the distance to move down
	 */
	public void moveDown(double d) {
		this.y_1Position += d;
		this.y_2Position += d;
	}

	/**
	 * Moves the alien left by the specified distance.
	 *
	 * @param distance the distance to move left
	 */
	public void moveLeft(float distance) {
		this.x_1Position -= distance;
		this.x_2Position -= distance;
	}

	/**
	 * Moves the alien right by the specified distance.
	 *
	 * @param distance the distance to move right
	 */
	public void moveRight(float distance) {
		this.x_1Position += distance;
		this.x_2Position += distance;
	}

	/**
	 * Shoots a bullet from the alien. TODO: This needs to be updated when the
	 * Bullet class is updated with two x and y positions.
	 *
	 * @return the bullet object
	 */
	public Bullet shoot() {
		return new Bullet((this.x_1Position + this.x_2Position) / 2, this.y_1Position, true);
	}

	/**
	 * Returns the image of the alien.
	 *
	 * @return the image of the alien
	 */
	public Image getImage() {
		// return this.image;
		if (flipIndex % 12 == 0) {
			armUp = !armUp;
			flipIndex = 0;
		}
		flipIndex++;
		if (armUp) {
			return this.image;
		} else {
			return this.image2;
		}
	}

	/**
	 * Draws the alien on the specified GraphicsContext with the given width and
	 * height.
	 *
	 * @param gc     the GraphicsContext on which to draw the alien
	 * @param width  the width of the alien
	 * @param height the height of the alien
	 */
	public void draw(GraphicsContext gc, float width, float height) {
		gc.drawImage(getImage(), this.x_1Position, this.y_1Position, width, height);
	}

	/**
	 * Returns the number for type of Alien (1, 2, or 3).
	 * 
	 * @return num the number for the Alien Type
	 */
	public abstract int getTypeNum();
}
