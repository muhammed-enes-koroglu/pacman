package pacman.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import pacman.Direction;
import pacman.Ghost;
import pacman.MazeMap;
import pacman.Square;

class GhostTest {

	@Test
	void test() {
		boolean[] passable = {false, false, true,  false,
							  false, false, true,  true,
							  false, false, false, true};
		int mapColumns = 4, mapRows = 3;
		MazeMap map = new MazeMap(mapColumns, mapRows, passable);

		Square s1 = Square.of(map, 1,2);
		Square s2 = Square.of(map, 0,2);
		
		Ghost ghost = new Ghost(s1, Direction.RIGHT);
		assert(ghost.getSquare().equals(s1));
		assert(ghost.getDirection() == Direction.RIGHT);
		ghost.setDirection(Direction.UP);
		assert(ghost.getDirection() == Direction.UP);
		ghost.setSquare(s2);
		assert(ghost.getSquare().equals(s2));
		}

}
