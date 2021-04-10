package pacman;

/**
 * Each instance of this class represents a dot, located at a fixed position in a Pac-Man maze.
 * A dot serves as the food for Pac-Man.
 * 
 * @invar | getSquare() != null
 * 
 * @immutable
 */
public class Dot extends FoodItem{
	
	/**
	 * @invar | square != null
	 */
//	private Square square;
//	private final int size;
	
	/**
	 * @basic
	 */
//	public Square getSquare() { return square; }
	
	/**
	 * @basic
	 * @post | result == 1
	 */
	@Override
	public int getSize() { return 1; }
	
	/**
	 * @throws IllegalArgumentException | square == null
	 * 
	 * @post | getSquare() == square
	 */
	public Dot(Square square) {
		super(square, 1);
	}

}
