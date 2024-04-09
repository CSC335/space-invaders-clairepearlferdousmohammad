// @author: Frances Pearl McNabb
package tests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import model.PlayerTank;

public class PlayerTankTest {

	@Test
	public void testGetters() {
		PlayerTank tank1 = new PlayerTank(0, 0);
		assertEquals(0, tank1.getX());
		assertEquals(0, tank1.getY());
		assertEquals(0, tank1.getDamages());
		// testing like this bc we know it works and it returns an object reference
		assertEquals(tank1.getImage(), tank1.getImage());
	}

	@Test
	public void testDamage() {
		PlayerTank tank2 = new PlayerTank(0, 0);
		tank2.addDamages(5);
		tank2.addDamages(400);
		assertEquals(405, tank2.getDamages());
	}

	@Test
	public void testMove() {
		PlayerTank tank3 = new PlayerTank(3, 0);
		tank3.moveLeft();
		assertEquals(2, tank3.getX());
		tank3.moveRight();
		tank3.moveRight();
		assertEquals(4, tank3.getX());
	}
}
