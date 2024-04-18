package model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.ArrayList;

//@author: MohammadHossein Rezaei
/**
 * The AlienCollection class represents a collection of aliens in a game. It
 * provides methods to fill the collection with aliens, move the aliens, check
 * for collisions, and perform other operations on the collection.
 */
public class AlienCollection {
	// Following will be set by parameters of the constructor
	private GraphicsContext gameGC;
	private int gridWidth;
	private int gridHeight;
	private int numAlienInRow;
	private int numTotalRows;
	private int moveMargin;

	// Following need to be calculated based on the above parameters
	private float alienWidth;
	private float alienHeight;
	private float horizontalMargin;
	private float verticalMargin;

	private int movingDirection = 1; // 1 for right, -1 for left

	// save all aliens
	private ArrayList<Alien> aliens = new ArrayList<>();

	/**
	 * Constructs a new AlienCollection object with the specified parameters.
	 *
	 * @param gameGC        the GraphicsContext object for drawing the aliens
	 * @param gridWidth     the width of the game grid
	 * @param moveMargin    the margin for moving the aliens
	 * @param gridHeight    the height of the game grid
	 * @param numAlienInRow the number of aliens in each row
	 * @param numTotalRows  the total number of rows of aliens
	 */
	public AlienCollection(GraphicsContext gameGC, int gridWidth, int moveMargin, int gridHeight, int numAlienInRow,
			int numTotalRows) {
		this.gameGC = gameGC;
		this.gridWidth = gridWidth;
		this.gridHeight = gridHeight;
		this.numAlienInRow = numAlienInRow;
		this.numTotalRows = numTotalRows;
		this.moveMargin = moveMargin;

		// horizontalMargin needs to be 30% of the alien width
		this.alienWidth = (float) ((gridWidth - moveMargin) / (0.3 * numAlienInRow + 0.3 + numAlienInRow));
		this.horizontalMargin = 0.3f * alienWidth;

		// verticalMargin needs to be 40% of the alien height
		this.alienHeight = (float) (gridHeight / (0.4 * numTotalRows + 0.4 + numTotalRows));
		this.verticalMargin = 0.4f * alienHeight;
	}

	/**
	 * Fills the AlienCollection with aliens in the specified number of rows.
	 *
	 * @param numRow the number of rows of aliens to fill
	 */
	public void fillWithAliens(int numRow) {
		/**
		 * This method will fill the gameGrid with aliens in the following way: Starting
		 * from the top row; it will add numRow rows of aliens with one alien type in
		 * each row.
		 */

		Alien nalien;
		for (int i = 0; i < numRow; i++) {
			for (int j = 0; j < numAlienInRow; j++) {
				if (i % 3 == 0) {
					nalien = new AlienType1(j * (alienWidth + horizontalMargin) + horizontalMargin + moveMargin / 2,
							i * (alienHeight + verticalMargin) + verticalMargin,
							j * (alienWidth + horizontalMargin) + horizontalMargin + alienWidth + moveMargin / 2,
							i * (alienHeight + verticalMargin) + verticalMargin + alienHeight);
				} else if (i % 3 == 1) {
					nalien = new AlienType2(j * (alienWidth + horizontalMargin) + horizontalMargin + moveMargin / 2,
							i * (alienHeight + verticalMargin) + verticalMargin,
							j * (alienWidth + horizontalMargin) + horizontalMargin + alienWidth + moveMargin / 2,
							i * (alienHeight + verticalMargin) + verticalMargin + alienHeight);
				} else {
					nalien = new AlienType3(j * (alienWidth + horizontalMargin) + horizontalMargin + moveMargin / 2,
							i * (alienHeight + verticalMargin) + verticalMargin,
							j * (alienWidth + horizontalMargin) + horizontalMargin + alienWidth + moveMargin / 2,
							i * (alienHeight + verticalMargin) + verticalMargin + alienHeight);
				}
				// gameGC.drawImage(nalien.getImage(), nalien.getX1(), nalien.getY1(),
				// alienWidth, alienHeight);
				nalien.draw(gameGC, alienWidth, alienHeight);
				this.aliens.add(nalien);
			}
		}
		this.movingDirection = 1;
	}

	/**
	 * Moves all aliens in the collection by the specified distance.
	 *
	 * @param distance the distance to move the aliens
	 */
	public void moveAliens(float distance) {
		/**
		 * This method will move all aliens in the collection based on the
		 * movingDirection. If the aliens reach the right or left border of the
		 * gameGrid, they will move down and change the movingDirection.
		 */

		// check if we need to move down
		boolean moveDown = false;
		for (Alien alien : aliens) {
			if (alien.getX1() + distance * movingDirection < 0
					|| alien.getX2() + distance * movingDirection > gridWidth) {
				moveDown = true;
				// System.out.println(alien.getX1() + " " + alien.getX2());
				// System.out.println("Distance: " + distance);
				break;
			}
		}

		// move all aliens
		if (moveDown) {
			// find the distance of the right most alien to the right border
			// or the left most alien to the left border

			float rightMost = 0;
			float leftMost = gridWidth;
			if (movingDirection == 1) {
				for (Alien alien : aliens) {
					if (alien.getX2() > rightMost) {
						rightMost = alien.getX2();
					}
				}
			} else {
				for (Alien alien : aliens) {
					if (alien.getX1() < leftMost) {
						leftMost = alien.getX1();
					}
				}
			}

			for (Alien alien : aliens) {
				// alien.moveDown(alienHeight + verticalMargin);
				// aliens move down by a smaller height
				alien.moveDown((alienHeight + verticalMargin) * 0.2);
				if (movingDirection == 1) {
					alien.moveRight(gridWidth - rightMost);
					alien.moveLeft(distance - (gridWidth - rightMost));
				} else {
					alien.moveLeft(leftMost);
					alien.moveRight(distance - leftMost);
				}
			}
			// for (Alien alien : aliens) {
			// alien.moveDown(alienHeight + verticalMargin);
			// if (movingDirection == 1) {
			// alien.moveRight(gridWidth - alien.getX2());
			// alien.moveLeft(distance - (gridWidth - alien.getX2()));
			// } else {
			// alien.moveLeft(alien.getX1());
			// alien.moveRight(distance - alien.getX1());
			// }
			// }
			movingDirection *= -1;
			// System.out.println("Moving down: " + movingDirection);
		} else {
			for (Alien alien : aliens) {
				alien.moveRight(distance * movingDirection);
			}
			// System.out.println("Moving Direction: " + movingDirection);
		}

		// update the drawings
		// gameGC.clearRect(0, 0, gridWidth, gridHeight);
		// gameGC.setStroke(Color.BLACK);
		// gameGC.setFill(Color.BLACK);
		// gameGC.strokeRect(0, 0, 800, 580);
		// gameGC.fillRect(0, 0, 800, 580);
		// for (Alien alien : aliens) {
		// // gameGC.drawImage(alien.getImage(), alien.getX1(), alien.getY1(),
		// alienWidth, alienHeight);
		// alien.draw(gameGC, alienWidth, alienHeight);
		// }
	}

	/**
	 * Checks if the given coordinates are inside any of the aliens in the
	 * collection. If so, removes the alien from the collection and returns true.
	 * Otherwise, returns false.
	 *
	 * @param x1 the starting x-coordinate
	 * @param x2 the ending x-coordinate
	 * @param y  the y-coordinate
	 * @return true if the coordinates are inside an alien, false otherwise
	 */
	public Boolean doesHit(float x1, float x2, float y) {
		/**
		 * This method will check if the given x1, x2, and y are inside any of the
		 * aliens in the collection. If so, it will return true and remove the alien
		 * from the collection. Otherwise, it will return false.
		 */
		// iterate backwards so the lower aliens are hit first
		for (int i=aliens.size()-1;i>=0;i--) {
			Alien alien = aliens.get(i);
			if (alien.getX1() < x2 && alien.getX2() > x1 && alien.getY1() < y && alien.getY2()+15 > y) {
				aliens.remove(alien);
				return true;
			}
		}
		return false;
	}

	/**
	 * Checks if the collection is empty.
	 *
	 * @return true if the collection is empty, false otherwise
	 */
	public Boolean isEmpty() {
		/**
		 * This method will return true if the collection is empty. Otherwise, it will
		 * return false.
		 */
		return aliens.isEmpty();
	}

	/**
	 * Clears the collection.
	 */
	public void clear() {
		/**
		 * This method will clear the collection.
		 */
		aliens.clear();
	}

	/**
	 * Returns the number of aliens in the collection.
	 *
	 * @return the number of aliens
	 */
	public int getNumAliens() {
		/**
		 * This method will return the number of aliens in the collection.
		 */
		return aliens.size();
	}

	/**
	 * Draws all the aliens in the collection.
	 */
	public void draw() {
		for (Alien alien : aliens) {
			alien.draw(gameGC, alienWidth, alienHeight);
		}
	}

}
