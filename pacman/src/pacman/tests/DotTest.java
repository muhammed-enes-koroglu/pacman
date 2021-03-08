package pacman.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import pacman.Dot;
import pacman.MazeMap;
import pacman.Square;

class DotTest {

	@Test
	void test() {
		boolean[] passable = {false, false, true,  false,
							  false, false, true,  true,
							  false, false, false, true};
		int mapColumns = 4, mapRows = 3;
		MazeMap map = new MazeMap(mapColumns, mapRows, passable);
		
		Square s1 = Square.of(map, 0, 2);
		
		Dot dot = new Dot(s1);
		assert(dot.getSquare().equals(s1));
		
	}

}
