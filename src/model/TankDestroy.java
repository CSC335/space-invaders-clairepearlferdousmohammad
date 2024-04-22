package model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

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
		dw = (this.x2-this.x1)*1;
		dh = this.height;
	}

	public int getAlienType() {
		return this.AlienType;
	}

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
