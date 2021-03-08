package pacman;

import java.util.stream.IntStream;

/**
 * Each instance of this class represents a maze layout, specifying the width and height of the maze
 * and, for each position in the maze, whether it is passable or not.
 * 
 * @immutable
 */
public class MazeMap {
	
	/**
	 * @invar | nbColumns > 0
	 * @invar | nbRows > 0
	 * @invar | map != null
	 * @invar | map.length == nbRows * nbColumns
	 */
	private int nbColumns;
	private int nbRows;
	/** @representationObject */
	private boolean[] map;

	/**
	 * Returns the width (i.e. the number of columns) of this maze map.
	 * 
	 * @basic
	 */
	public int getWidth() { 
		return nbColumns;
	}
	
	/**
	 * Returns the height (i.e. the number of rows) of this maze map.
	 * 
	 * @basic
	 */
	public int getHeight() { 
		return nbRows;
	}
	
	/**
	 * Returns whether the square in this maze at row index {@code row} and column index {@code column} is passable.
	 * The square in the top-left corner of the maze has row index 0 and column index 0.
	 * 
	 * @throws IllegalArgumentException if the given {@code rowIndex} is not between zero and {@code nbRows}
	 * 		| !(0 < rowIndex && rowIndex <= getHeight())
	 * @throws IllegalArgumentException if the given {@code columnIndex} is not between zero and {@code nbColumns}
	 * 		| !(0 < columnIndex && columnIndex <= getWidth())
	 */
	public boolean isPassable(int rowIndex, int columnIndex) { 
		if (!(0 <= rowIndex && rowIndex <= nbRows)) 
			throw new IllegalArgumentException("Illegal argument: rowIndex");
		if (!(0 <= columnIndex && columnIndex <= nbColumns))
			throw new IllegalArgumentException("Illegal argument: columnIndex");
		
		return map[nbColumns * rowIndex + columnIndex];
	}
	
	/**
	 * Initializes this object so that it represents a maze layout with the given width, height, and
	 * passable positions. The passable positions are given in row-major order (i.e. the first {@code width} elements
	 * of {@code passable} specify the passability of the maze positions in the first row of the maze). 
	 * 
	 * @throws IllegalArgumentException if the given {@code width} is not greater than zero
	 * 		| !(width > 0)
	 * @throws IllegalArgumentException if the given {@code height} is not greater than zero
	 * 		| !(height > 0)
	 * @throws IllegalArgumentException if the given {@code passable} is null 
	 * 		| passable == null
	 * @throws IllegalArgumentException if the length of the given {@code passable} is not equal to width * height 
	 * 		| !(passable.length == width * height)
	 * 
	 * @post | getWidth() == width
	 * @post | getHeight() == height
	 * @post | IntStream.range(0,passable.length).allMatch(i -> passable[i] == isPassable(i / width, i % width))
	 */
	public MazeMap(int width, int height, boolean[] passable) {
		if (!(width > 0))
			throw new IllegalArgumentException("width must be greater than zero");
		if (!(height > 0))
			throw new IllegalArgumentException("height must be greater than zero");
		if (passable == null)
			throw new IllegalArgumentException("passable may not be null.");
		if (!(passable.length == width * height))
			throw new IllegalArgumentException("length of passable must be equal to height * width");
		
		nbRows = height;
		nbColumns = width;
		map = passable.clone();
	}
}
