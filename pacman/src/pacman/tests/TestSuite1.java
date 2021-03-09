package pacman.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import pacman.Direction;
import pacman.Dot;
import pacman.Ghost;
import pacman.MazeMap;
import pacman.PacMan;
import pacman.Square;

class TestSuite1 {

	@Test
	void test() {
		
		dotTest();
		ghostTest();
		mazeMapTest();
		pacmanTest();
		squareTest();
	}
	

	void dotTest() {
		boolean[] passable = {false, false, true,  false,
				  			  false, false, true,  true,
				  			  false, false, false, true};
		int mapColumns = 4, mapRows = 3;
		MazeMap map = new MazeMap(mapColumns, mapRows, passable);
		
		Square s1 = Square.of(map, 0, 2);
		
		Dot dot = new Dot(s1);
		assert(dot.getSquare().equals(s1));
	}

	void ghostTest() {
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
	
	void mazeMapTest() {

		
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
	
	void pacmanTest() {

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
				
	}
	
	void squareTest() {
		boolean[] passable = {false, false, true,  false,
				  false, false, true,  true,
				  false, false, false, true};
		int mapColumns = 4, mapRows = 3;
		MazeMap map = new MazeMap(mapColumns, mapRows, passable);
		
		// Tests for the constructor and the getters
		for (int index = 0; index < passable.length; index++) {
			int row = index / mapColumns;
			int col = index % mapColumns;			
			
			Square s1 = Square.of(map, row, col);
			assert(s1.getMazeMap().equals(map));
			assert(s1.isPassable() == map.isPassable(row, col));
			assert(s1.getRowIndex() == row);
			assert(s1.getColumnIndex() == col);
		}
		
		// Tests on equals()
		Square s1 = Square.of(map, 1, 1);
		Square s2 = Square.of(map, 1, 0);
		Square s3 = Square.of(map, 1, 3);
		
		assertFalse(s1.equals(s2));
		assert(s1.equals(s1));
		assert(s1.equals(Square.of(map, 1, 1)));
		MazeMap map2 = new MazeMap(mapColumns, mapRows, passable);
		assertFalse(s1.equals(Square.of(map2, 1, 2)));
		
		// Tests for getNeighbor() and canMove()
		s1 = Square.of(map, 1, 1);
		s2 = Square.of(map, 1, 0);
		s3 = Square.of(map, 1, 3);
		assert(s1.getNeighbor(Direction.LEFT).equals(s2));
		assert(s2.getNeighbor(Direction.LEFT).equals(s3));
		assert(s1.canMove(Direction.LEFT) == s2.isPassable());
		assert(s2.canMove(Direction.LEFT) == s3.isPassable());
		
		s1 = Square.of(map, 1, 1);
		s2 = Square.of(map, 0, 1);
		s3 = Square.of(map, 2, 1);
		assert(s1.getNeighbor(Direction.UP).equals(s2));
		assert(s2.getNeighbor(Direction.UP).equals(s3));
		assert(s1.canMove(Direction.UP) == s2.isPassable());
		assert(s2.canMove(Direction.UP) == s3.isPassable());
		
		s1 = Square.of(map, 1, 2);
		s2 = Square.of(map, 1, 3);
		s3 = Square.of(map, 1, 0);
		assert(s1.getNeighbor(Direction.RIGHT).equals(s2));
		assert(s2.getNeighbor(Direction.RIGHT).equals(s3));
		assert(s1.canMove(Direction.RIGHT) == s2.isPassable());
		assert(s2.canMove(Direction.RIGHT) == s3.isPassable());
		
		s1 = Square.of(map, 1, 2);
		s2 = Square.of(map, 2, 2);
		s3 = Square.of(map, 0, 2);
		assert(s1.getNeighbor(Direction.DOWN).equals(s2));
		assert(s2.getNeighbor(Direction.DOWN).equals(s3));
		assert(s1.canMove(Direction.DOWN) == s2.isPassable());
		assert(s2.canMove(Direction.DOWN) == s3.isPassable());
		
		
		// Tests for getPassableDirectionsExcept()
		s1 = Square.of(map, 1, 1);
		s2 = Square.of(map, 1, 3);
		Direction[] arr = {Direction.UP, Direction.RIGHT};
		Set<Direction> directions = new HashSet<>(Arrays.asList(arr));
		
		Direction[] passableDirs = s1.getPassableDirectionsExcept(Direction.LEFT);
		assert(new HashSet<>(Arrays.asList(arr)).equals(directions));
		
		arr = new Direction[] {Direction.RIGHT};
		directions = new HashSet<>(Arrays.asList(arr));
		passableDirs = s1.getPassableDirectionsExcept(Direction.UP);
		assert(new HashSet<>(Arrays.asList(arr)).equals(directions));
		
		arr = new Direction[] {};
		directions = new HashSet<>(Arrays.asList(arr));
		passableDirs = s2.getPassableDirectionsExcept(Direction.DOWN);
		assert(new HashSet<>(Arrays.asList(arr)).equals(directions));
		
	}
}
