package pacman.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import pacman.MazeMap;
import pacman.PacMan;
import pacman.Square;

class PacManTest {

	@Test
	void test() {
		boolean[] passable = {false, false, true,  false,
				  			  false, false, true,  true,
				  			  false, false, false, true};
		int mapColumns = 4, mapRows = 3;
		MazeMap map = new MazeMap(mapColumns, mapRows, passable);
		
		Square s1 = Square.of(map, 1,2);
		Square s2 = Square.of(map, 0,2);
		int lives = 3;
		PacMan pacman = new PacMan(lives, s1);
		assert(pacman.getSquare().equals(s1));
		assert(pacman.getNbLives() == lives);
		pacman.setSquare(s2);
		assert(pacman.getSquare().equals(s2));
		pacman.die();
		assert(pacman.getNbLives() == lives - 1);
		
		System.out.println("sdfasdf");
	}

}
