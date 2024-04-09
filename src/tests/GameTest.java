package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import model.Game;

class GameTest {

	@Test
	void testNewGame() {
		Game game = new Game();
		assertEquals(game.getScore(), 0);
		assertEquals(game.getNumLives(), 3);
		assertFalse(game.getGameOver());

	}
	
	@Test
	void testGame1() {
		Game game = new Game();
		game.setScore(100);
		game.decrementLives();
		assertEquals(game.getScore(), 100);
		assertEquals(game.getNumLives(), 2);
		game.setGameOver(true);
		assertTrue(game.getGameOver());

	}

}
