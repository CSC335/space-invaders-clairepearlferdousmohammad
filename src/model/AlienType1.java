package model;
//@author: MohammadHossein Rezaei
public class AlienType1 extends Alien {
    public AlienType1(float xPos1, float yPos1, float xPos2, float yPos2) {
        super(xPos1, xPos2, yPos1, yPos2, "alien1_part1.png", "alien1_part2.png");
    }

	@Override
	public int getTypeNum() {
		return 1;
	}
}
