package model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;



/**
 * Represents a playerTank, movable by the player, in an instance of a Space
 * Invaders game.
 * 
 * @author Pearl McNabb
 */

public class PlayerTank {

	private int damages;
	private float x1Position;
	private float x2Position;
	private float y1Position;
	private float y2Position;
	private Image image;

	public PlayerTank(float x1Pos, float y1Pos, float width, float height) {
		this.damages = 0;
		this.x1Position = x1Pos;
		this.y1Position = y1Pos;
		this.x2Position = x1Pos + width;
		this.y2Position = y1Pos + height;
		this.image = new Image(getClass().getResourceAsStream("tank.png"));
	}

	/**
	 * Gets the x1-coordinate position of the tank
	 * 
	 * @return x1Position The current x1 position of the tank
	 */
	public float getX1() {
		return this.x1Position;
	}

	/**
	 * Gets the x2-coordinate position of the tank
	 * 
	 * @return x2Position The current x2 position of the tank
	 */
	public float getX2() {
		return this.x2Position;
	}

	/**
	 * Gets the y1-coordinate position of the tank
	 * 
	 * @return y1Position The current y1 position of the tank
	 */
	public float getY1() {
		return this.y1Position;
	}

	/**
	 * Gets the y2-coordinate position of the tank
	 * 
	 * @return y2Position The current y2 position of the tank
	 */
	public float getY2() {
		return this.y2Position;
	}

	/**
	 * Moves the tank left by the specified distance in terms of the canvas coordinate system
	 * 
	 * @param d The distance to move the tank left
	 */
	public void moveLeft(double d) {
		this.x1Position -= d;
		this.x2Position -= d;
	}

	/**
	 * Moves the tank right by the specified distance in terms of the canvas coordinate system
	 * 
	 * @param d The distance to move the tank right
	 */
	public void moveRight(double d) {
		this.x1Position += d;
		this.x2Position += d;
	}

	/**
	 * Gets the amount of damage that the tank has taken during the game
	 * 
	 * @return damages		The current damage on the tank
	 */
	public int getDamages() {
		return damages;
	}

	/**
	 * Gets the image to put on the canvas to represent the tank
	 * 
	 * @return image 	The image that should be shown to represent the tank
	 */
	public Image getImage() {
		return image;
	}

	/**
	 * When the tank takes on damages, it must accumulate this amount into damages
	 * to keep track of how much damages until death
	 * 
	 * @param damages Amount of damages that the tank took
	 */
	public void addDamages(int damages) {
		this.damages += damages;
	}

	/**
	 * Draws the tank using the given image onto the graphics context
	 * 
	 * @param gc GraphicsContext gc that is used in the GUI
	 */
	public void draw(GraphicsContext gc) {
		gc.drawImage(this.image, this.x1Position, this.y1Position, this.x2Position - this.x1Position, this.y2Position - this.y1Position);
	}
	
	/**
	 * Creates a bullet object from the tank, fires it, and then returns the object
	 * 
	 * @return bullet	initialized bullet object that is fired
	 */
	public Bullet shoot() {
		Bullet bullet = new Bullet((this.x1Position + this.x2Position) / 2, this.y1Position, "bullet1.png", false);
		return bullet;
	}

}
