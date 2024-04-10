package tests;
import model.Alien;
import model.AlienType1;
import model.AlienType2;
import model.AlienType3;
import model.Bullet;
import model.AlienCollection;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
//@author: MohammadHossein Rezaei
public class AllienTest {
	
	@Test
	void testAlienConstructorAndGetters() {
		Alien alien = new AlienType1(100, 150);
		assertEquals(100, alien.getX());
		assertEquals(150, alien.getY());
		assertNotNull(alien.getImage());
	}

	@Test
	void testMoveUp() {
		Alien alien = new AlienType1(100, 150);
		alien.moveUp();
		assertEquals(149, alien.getY());
	}

	@Test
	void testMoveDown() {
		Alien alien = new AlienType1(100, 150);
		alien.moveDown();
		assertEquals(151, alien.getY());
	}

	@Test
	void testMoveLeft() {
		Alien alien = new AlienType1(100, 150);
		alien.moveLeft();
		assertEquals(99, alien.getX());
	}

	@Test
	void testMoveRight() {
		Alien alien = new AlienType1(100, 150);
		alien.moveRight();
		assertEquals(101, alien.getX());
	}

	@Test
	void testShoot() {
		Alien alien = new AlienType1(100, 150);
		Bullet bullet = alien.shoot();
		assertEquals(100, bullet.getX());
		assertEquals(150, bullet.getY());
		assertNotNull(bullet.getImage());
	}

	@Test
	void testAddAndRemoveAlien() {
		AlienCollection collection = new AlienCollection();
		Alien alien = new AlienType2(100, 100);
		collection.addAlien(alien);
		assertTrue(collection.doesContain(alien));
		collection.removeAlien(alien);
		assertFalse(collection.doesContain(alien));
		collection.getAliens();
	}

	@Test
	void testIsEmpty() {
		AlienCollection collection = new AlienCollection();
		assertTrue(collection.isEmpty());
		collection.addAlien(new AlienType3(100, 100));
		assertFalse(collection.isEmpty());
	}

}
