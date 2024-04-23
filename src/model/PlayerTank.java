package model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;



/**
 * Represents a playerTank, movable by the player, in an instance of a Space
 * Invaders game.
 * 
 * @author Pearl McNabb and Claire Lodermeier
 */

public class PlayerTank {

	private int damages;
	private float x1Position;
	private float x2Position;
	private float y1Position;
	private float y2Position;
	private Image image;
	private float height;

	private int respawningCounter;
	
	private Bullet currentBullet;
	
	public PlayerTank(float x1Pos, float y1Pos, float width, float height) {
		this.respawningCounter = 0;
		this.damages = 0;
		this.x1Position = x1Pos;
		this.y1Position = y1Pos;
		this.x2Position = x1Pos + width;
		this.y2Position = y1Pos + height;
		this.height = height;
		this.image = new Image(getClass().getResourceAsStream("tank.png"));
		this.currentBullet = null;
	}

	/**
	 * Returns the Bullet most recently shot by the Tank
	 * 
	 * @return currentBullet 	the Bullet most recently shot by the Tank
	 */
	public Bullet getCurrentBullet() {
		return this.currentBullet;
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
	 * Respawns a new Tank by making it flash for a few seconds. 
	 * 
	 * @return this		The current tank object
	 **/
	public void respawn(int counter) {
		this.respawningCounter = counter;
	}
	
	
	/**
	 * Determines whether the tank is respawning. 
	 * 
	 * @return 	true or false
	 **/
	public boolean isRespawning() {	
		return respawningCounter>0;
	}
	
	/**
	 * Gets the image to put on the canvas to represent the tank
	 * 
	 * @return image 	The image that should be shown to represent the tank
	 */
	public Image getImage() {
		respawningCounter--;

		if(isRespawning() && respawningCounter %  4 < 2) {
			return null;
		}

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
		if(getImage()==null) {
			return;
		}
		gc.drawImage(this.image, this.x1Position, this.y1Position, this.x2Position - this.x1Position, this.y2Position - this.y1Position);
	}
	
	/**
	 * Creates a bullet object from the tank, fires it, and then returns the object
	 * 
	 * @return bullet	initialized bullet object that is fired
	 */
	public Bullet shoot() {
		currentBullet = new Bullet(((this.x1Position + this.x2Position) / 2) - 1, this.y1Position-10, false);
		return currentBullet;
	}
		
	public TankDestroy doesHit(float x1, float x2, float y) {
		/**
		 * Will determine if a tank is being hit, will return animator for destroying the tank
		 * 
		 * @param x1	the x coordinate of the left side of the bullet
		 * @param x2	the x coordinate of the right side of the bullet
		 * @param y		the y coordinate of the bottom of the bullet
		 */
		// iterate backwards so the lower aliens are hit first
		// System.out.println("tank got hit was called");
		if (getX1() < x1 && getX2() > x2 && getY1() < y) {
			float height = getY2() - getY1();
			TankDestroy as = new TankDestroy(getX1(), getX2(), getY1(), height);
			System.out.println("player tank got hit");
			return as;
		}
		
		return null;
	}

}
