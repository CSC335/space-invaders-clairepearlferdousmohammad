package model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 * @author Pearl McNabb and MohammadHossein Rezaei. This class represents an
 *         animator for when the playerTank is hit by an alien bullet and
 *         destroyed. The animation consists of an explosion spritesheet.
 */
public class TankDestroy {
	private int tic = 0;
	private float x1;
	private float x2;
	private float y;
	private float height;
	private double sx, sy, sw, sh, dx, dy, dw, dh;
	private int AlienType;

	public TankDestroy(float x1, float x2, float y, float height) {
		this.x1 = x1;
		this.x2 = x2;
		this.y = y;
		this.height = height;
		sx = 12;
		sy = 3;
		sw = 240;
		sh = 240;
		dx = this.x1;
		dy = this.y;
		dw = (this.x2 - this.x1) * 1;
		dh = this.height;
	}

	/**
	 * Gets the type of alien from the alien grid
	 * 
	 * @return this.AlienType, the alien type, either 1, 2, or 3
	 */
	public int getAlienType() {
		return this.AlienType;
	}

	/**
	 * Uses a GraphicsContext to "draw" the explosion on the canvas using a
	 * spritesheet after the alien is shot by the alien bullet.
	 * 
	 * @param gc, the GraphicsContext used to draw the animation using a spritesheet
	 *            on the canvas
	 */
	public void handle(GraphicsContext gc) {
		if (tic >= 4) {
			sx = 0;
			return;
		}
		Image picture = new Image(getClass().getResourceAsStream("explodePlayer.png"));
		tic++;
		gc.drawImage(picture, sx, sy, sw, sh, dx, dy, dw, dh);
		sx += 240;
	}
}
