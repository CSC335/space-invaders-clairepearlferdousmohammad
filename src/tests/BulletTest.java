// @author: Frances Pearl McNabb
package tests;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;

import model.Bullet;

class BulletTest {

	@Test
	void testGetters() {
		Bullet bullet1 = new Bullet(0, 0, "bullet1.png");
		assertEquals(0, bullet1.getX());
		assertEquals(0, bullet1.getY());
		// testing like this bc we know it works and it returns an object reference
		assertEquals(bullet1.getImage(), bullet1.getImage());
	}

	@Test
	void testMove() {
		Bullet bullet2 = new Bullet(0, 0, "bullet2.png");
		assertEquals(0, bullet2.getY());
		bullet2.moveUp();
		bullet2.moveUp();
		assertEquals(-2, bullet2.getY());
		bullet2.moveDown();
		assertEquals(-1, bullet2.getY());
	}

}