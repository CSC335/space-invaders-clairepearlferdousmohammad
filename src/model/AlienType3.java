package model;

/**
 * Represents an instance of type 3 alien within a Space Invaders game.
 * 
 * @author MohammadHossein Rezaei
 */
public class AlienType3 extends Alien {
	public AlienType3(float xPos1, float yPos1, float xPos2, float yPos2) {
		super(xPos1, xPos2, yPos1, yPos2, "alien3_part1.png", "alien3_part2.png");
	}

	/**
	 * returns the value of the type of alien, three in this case
	 * 
	 * @return the number of the alien type
	 */
	@Override
	public int getTypeNum() {
		return 3;
	}

}
