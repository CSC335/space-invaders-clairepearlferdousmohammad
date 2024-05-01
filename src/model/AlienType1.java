package model;

/**
 * Represents an instance of type 1 alien within a Space Invaders game.
 * 
 * @author MohammadHossein Rezaei
 */
public class AlienType1 extends Alien {
	public AlienType1(float xPos1, float yPos1, float xPos2, float yPos2) {
		super(xPos1, xPos2, yPos1, yPos2, "alien1_part1.png", "alien1_part2.png");
	}

	/**
	 * returns the value of the type of alien, one in this case
	 * 
	 * @return the number of the alien type
	 */
	@Override
	public int getTypeNum() {
		return 1;
	}
}
