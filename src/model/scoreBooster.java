package model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class scoreBooster {

	GraphicsContext gc;
	Image image;
	float x1Position;
	float x2Position;
	float y1Position;
	float y2Position;
	float height;
	float width;
	public Boolean isActivated;
	float respawningCounter;

	public scoreBooster(float y1Pos) {
		this.y1Position = y1Pos;
		this.height = 40;
		this.width = 50;
		this.isActivated = false;
		this.respawningCounter = 0;
		this.image = new Image(getClass().getResourceAsStream("booster.png"));
	}

	public void activate() {
		this.isActivated = true;
		this.x1Position = (float) (Math.random() * 530);
	}

	public void deactivate() {
		this.isActivated = false;
		this.respawningCounter = 0;
	}

	public Image getImage() {
		respawningCounter++;
		if (respawningCounter > 60) {
			this.deactivate();
		}

		if (respawningCounter % 4 < 2) {
			return null;
		} else {
			return this.image;
		}

	}

	public void draw(GraphicsContext gc) {
		if (isActivated) {
			if (this.getImage() != null) {
				gc.drawImage(this.image, this.x1Position, this.y1Position, this.width, this.height);
			}
		}
	}

	public Boolean doesHit(PlayerTank playerTank) {
		if (playerTank.getX1() < this.x1Position + this.width && playerTank.getX2() > this.x1Position
				&& playerTank.getY1() < this.y1Position + this.height && playerTank.getY2() > this.y1Position) {
			this.deactivate();
			return true;
		} else {
			return false;
		}
	}

}
