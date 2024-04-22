package model;
// @author: MohammadHossein Rezaei
public class AlienType3 extends Alien {
    public AlienType3(float xPos1, float yPos1, float xPos2, float yPos2) {
        super(xPos1, xPos2, yPos1, yPos2, "alien3_part1.png", "alien3_part2.png");
    }

	@Override
	public int getTypeNum() {
		return 3;
	}

}
