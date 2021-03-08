package pacman;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Each instance of this class represents a position in a maze, specified by a row index and a column index.
 * The top row and the leftmost column have index 0.
 * 
 * @immutable
 * 
 * @invar | 0 <= getRowIndex() && getRowIndex() < getMazeMap().getHeight()
 * @invar | 0 <= getColumnIndex() && getColumnIndex() < getMazeMap().getWidth()
 * @invar | getMazeMap() != null
 */
public class Square {
	/**
	 * @invar | 0 <= rowIndex && rowIndex < mazeMap.getHeight()
	 * @invar | 0 <= columnIndex && columnIndex < mazeMap.getWidth()
	 * @invar | mazeMap != null
	 */
	private final int rowIndex;
	private final int columnIndex;
	/** @representationObject */
	private final MazeMap mazeMap;
	

	/** @basic */
	public MazeMap getMazeMap() { 
		return mazeMap;
	}

	/** @basic */
	public int getRowIndex() {
		return rowIndex;
	}

	/** @basic */
	public int getColumnIndex() { 
		return columnIndex;
	}
	
	/**
	 * @post | result == getMazeMap().isPassable(getRowIndex(), getColumnIndex())
	 */
	public boolean isPassable() { 
		return getMazeMap().isPassable(getRowIndex(), getColumnIndex());
	}
	
	/**
	 * Returns the Square object on the given map in the given position.
	 * 
	 * @inspects | mazeMap
	 * 
	 * @throws IllegalArgumentException if the given {@code mazeMap} object is null.
	 * 		| mazeMap == null
	 * @throws IllegalArgumentException if the given {@code rowIndex} is not within boundaries.
	 * 		|  !(0 <= rowIndex && rowIndex < mazeMap.getHeight())
	 * @throws IllegalArgumentException if the given {@code columnIndex} is not within boundaries.
	 * 		|  !(0 <= columnIndex && columnIndex < mazeMap.getWidth())
	 * 
	 * @post | mazeMap.equals(result.getMazeMap())
	 * @post | result.isPassable() == mazeMap.isPassable(rowIndex, columnIndex)
	 * @post | result.getRowIndex() == rowIndex
	 * @post | result.getColumnIndex() == columnIndex
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
			return of(this.mazeMap, Math.floorMod(this.rowIndex - 1, mazeMap.getHeight()),this.columnIndex);
		if (direction == Direction.DOWN)
			return of(this.mazeMap, Math.floorMod(this.rowIndex + 1, mazeMap.getHeight()),this.columnIndex);
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
		Set<Direction> passableDirections = new HashSet<>();
		for(int i = 0; i< directions.length; i++) {
			Direction dir = directions[i];
			if (dir != excludedDirection && canMove(dir))
				passableDirections.add(dir);
		}
		return passableDirections.toArray(new Direction[passableDirections.size()]);
	}
	
	/**
	 * Returns whether the given square refers to the same {@code MazeMap} object and has the same row and column index as this square.  
	 * 
	 * @inspects | this
	 * @inspects | other
	 * @throws  IllegalArgumentException if {@code other} is null
	 * 		| other == null
	 */
	public boolean equals(Square other) {
		if (other == null)
			throw new IllegalArgumentException("the given Square other is null");
		return (this.mazeMap == other.mazeMap) && (this.rowIndex == other.rowIndex) && 
				(this.columnIndex == other.columnIndex);
	}
	
	public String toString() {
		return "Square: " + getRowIndex() + " " + getColumnIndex();
	}
}
