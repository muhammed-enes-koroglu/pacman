package pacman;

import java.util.Arrays;

/**
 * Each instance of this class represents a position in a maze, specified by a row index and a column index.
 * The top row and the leftmost column have index 0.
 * 
 * @immutable
 */
public class Square {
	/**
	 * @invar | 0 <= rowIndex && rowIndex < maze.getHeight()
	 * @invar | 0 <= columnIndex && columnIndex < maze.getWidth()
	 * @invar | maze != null
	 */
	private int rowIndex;
	private int columnIndex;
	/** @representationObject */
	private MazeMap mazeMap;
	
	/**
	 * @basic
	 */
	public MazeMap getMazeMap() { 
		return mazeMap;
	}
	
	/**
	 * @basic
	 */	
	public int getRowIndex() {
		return rowIndex;
	}
	
	/**
	 * @basic
	 */
	public int getColumnIndex() { 
		return columnIndex;
	}
	
	public boolean isPassable() { 
		return mazeMap.isPassable(rowIndex, columnIndex);
	}
	
	/**
	 * Serves as a constructor.
	 * 
	 * @throws IllegalArgumentException if the given {@code mazeMap} object is null.
	 * 		| mazeMap == null
	 * @throws IllegalArgumentException if the given {@code rowIndex} is not within boundaries.
	 * 		|  !(0 <= rowIndex && rowIndex < mazeMap.getHeight())
	 * @throws IllegalArgumentException if the given {@code columnIndex} is not within boundaries.
	 * 		|  !(0 <= columnIndex && columnIndex < mazeMap.getWidth())
	 * 
	 * @post | equals(mazeMap, getMazeMap())
	 * @post | isPassable() == mazeMap.isPassable(rowIndex, columnIndex)
	 * @post | getRowIndex() == rowIndex
	 * @post | getColumnIndex() == columnIndex
	 */
	public static Square of(MazeMap mazeMap, int rowIndex, int columnIndex) {
		if (mazeMap == null)
			throw new IllegalArgumentException("given mazeMap is null");
		if (!(0 <= rowIndex && rowIndex < mazeMap.getHeight()))
			throw new IllegalArgumentException("given rowIndex is out of bounds");
		if (!(0 <= columnIndex && columnIndex < mazeMap.getWidth()))
			throw new IllegalArgumentException("given columnIndex is out of bounds");
		
		return new Square(mazeMap, rowIndex, columnIndex);
		
	}
	
	private Square(MazeMap mazeMap, int rowIndex, int columnIndex) {
		this.mazeMap = mazeMap;
		this.rowIndex = rowIndex;
		this.columnIndex = columnIndex;
	}
	
	/**
	 * Returns this square's neighbor in the given direction.
	 * If this square has no neigbor in the given direction, return 
	 * the square that is furthest away in the opposite direction.
	 */
	// No formal documentation required
	public Square getNeighbor(Direction direction) {
		// Implementation hint: use method java.lang.Math.floorMod.
		if (direction == Direction.RIGHT)
			return of(this.mazeMap, this.rowIndex, Math.floorMod(this.columnIndex + 1, mazeMap.getWidth()));
		if (direction == Direction.LEFT)
			return of(this.mazeMap, this.rowIndex, Math.floorMod(this.columnIndex - 1, mazeMap.getWidth()));
		if (direction == Direction.UP)
			return of(this.mazeMap, Math.floorMod(this.rowIndex + 1, mazeMap.getHeight()),this.columnIndex);
		if (direction == Direction.DOWN)
			return of(this.mazeMap, Math.floorMod(this.rowIndex - 1, mazeMap.getHeight()),this.columnIndex);
		else 
			throw new IllegalArgumentException("the given direction is null");
	}

	/**
	 * Returns whether this square's neighbor in the given direction is passable.
	 */
	// No formal documentation required
	public boolean canMove(Direction direction) {
		if (direction == null)
			throw new IllegalArgumentException("the given direction is null");

		return getNeighbor(direction).isPassable();
	}

	/**
	 * Returns the directions that are different from the given excluded direction and such that 
	 * the neighbor in that direction is passable.
	 * The returned array shall have no null elements and shall have no duplicates.
	 */
	// No formal documentation required
	public Direction[] getPassableDirectionsExcept(Direction excludedDirection) {
		if (excludedDirection == null)
			throw new IllegalArgumentException("the given direction is null");

		Direction[] directions = {Direction.RIGHT, Direction.LEFT, Direction.UP, Direction.DOWN};
		Direction[] passableDirections = {};
		for(Direction dir : directions) {
			if (dir != excludedDirection && canMove(dir))
				passableDirections = Arrays.copyOf(passableDirections, passableDirections.length + 1);
				passableDirections[passableDirections.length - 1] = dir;
		}
		return passableDirections;
	}
	
	/**
	 * Returns whether the given square refers to the same {@code MazeMap} object and has the same row and column index as this square.  
	 * 
	 * @inspects this
	 * @throws  IllegalArgumentException if {@code other} is null
	 * 		| other == null
	 */
	public boolean equals(Square other) {
		if (other == null)
			throw new IllegalArgumentException("the given Square other is null");
		return (this.mazeMap == other.mazeMap) && (this.rowIndex == other.rowIndex) && 
				(this.columnIndex == other.columnIndex);
	}
	
}
