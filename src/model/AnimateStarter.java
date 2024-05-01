package model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 * @author Pearl McNabb and MohammadHossein Rezaei. This class represents an
 *         animator for when an alien is hit by a playerTank bullet and
 *         destroyed. The animation consists of an explosion spritesheet.
 */
public class AnimateStarter {
	private int tic = 0;
	private float x1;
	private float x2;
	private float y;
	private float height;
	private double sx, sy, sw, sh, dx, dy, dw, dh;
	private int AlienType;

	public AnimateStarter(float x1, float x2, float y, float height, int AlienType) {
		this.x1 = x1;
		this.x2 = x2;
		this.y = y;
		this.height = height;
		sx = 36 + 400;
		sy = 62;
		sw = 200;
		sh = 200;
		dx = this.x1;
		dy = this.y;
		dw = (this.x2 - this.x1) * 1;
		dh = this.height;
		this.AlienType = AlienType;
	}

	/**
	 * Gets the type of alien so it can be removed from the alien grid
	 * 
	 * @return this.AlienType, the alien type, either 1, 2, or 3
	 */
	public int getAlienType() {
		return this.AlienType;
	}

	/**
	 * Uses a GraphicsContext to "draw" the explosion on the canvas using a
	 * spritesheet after the alien is shot by the playerTank bullet.
	 * 
	 * @param gc, the GraphicsContext used to draw the animation using a spritesheet
	 *            on the canvas
	 */
	public void handle(GraphicsContext gc) {
		if (tic >= 3) {
			sx = 0;
			return;
		}
		Image picture = new Image(getClass().getResourceAsStream("alienExplode.png"));
		tic++;
		gc.drawImage(picture, sx, sy, sw, sh, dx, dy, dw, dh);
		sx += 200;
	}
}
