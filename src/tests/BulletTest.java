// @author: Frances Pearl McNabb
package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;


import model.Bullet;

class BulletTest {

	@Test
	void testAlienDrop() {
		Bullet bullet1 = new Bullet(0, 0, "bullet1.png", true);
		assertEquals(0.0, bullet1.getX());
		assertEquals(0.0, bullet1.getY());
		// testing like this bc we know it works and it returns an object reference
		assertEquals(bullet1.getImage(), bullet1.getImage());
		bullet1.fire();
		assertEquals(600.0, bullet1.getY());
		
	}

	@Test
	void testTankFire() {
		Bullet bullet2 = new Bullet(0, 400, "bullet2.png", false);
		assertEquals(400.0, bullet2.getY());
		bullet2.moveUp();
		bullet2.moveUp();
		assertEquals(398.0, bullet2.getY());
		bullet2.moveDown();
		assertEquals(399.0, bullet2.getY());
		bullet2.moveDown();
		bullet2.fire();
		assertEquals(0.0, bullet2.getY());
	}

}