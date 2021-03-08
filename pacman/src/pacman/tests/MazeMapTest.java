package pacman.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import pacman.MazeMap;

class MazeMapTest {

	@Test
	void test() {
		
		boolean[] passable = {false, false, true, false,
							  false, false, true, true,
							  false, false, false, true};
		MazeMap map = new MazeMap(4, 3, passable);
		assert(map.getWidth() == 4);
		assert(map.getHeight() == 3);
		assert(map.isPassable(0, 0) == false);
		assert(map.isPassable(1, 0) == false);
		assert(map.isPassable(2, 0) == false);
		assert(map.isPassable(0, 2) == true);
		assert(map.isPassable(1, 2) == true);
		
		
	}

}
