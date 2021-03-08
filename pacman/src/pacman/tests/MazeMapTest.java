package pacman.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import pacman.MazeMap;
import pacman.Square;

class MazeMapTest {

	@Test
	void test() {
		
		boolean[] passable = {false, false, true, false,
							  false, false, true, true,
							  false, false, false, true};
		int mapCols = 4;
		int mapRows = 3;
		MazeMap map = new MazeMap(mapCols, mapRows, passable);
		assert(map.getWidth() == mapCols);
		assert(map.getHeight() == mapRows);
		

		for (int index = 0; index < passable.length; index++) {
			int row = index / mapCols;
			int col = index % mapCols;			
			assert(map.isPassable(row, col) == passable[index]);
		}
		
	}

}
