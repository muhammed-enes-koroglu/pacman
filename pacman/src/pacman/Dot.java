package pacman;

/**
 * Each instance of this class represents a dot (a piece of food for Pac-Man) in a Pac-Man maze.
 * 
 * @immutable
 * 
 * @invar | getSquare() != null
 * @invar | getSquare().isPassable() == true
 */
public class Dot {
	
	/** @invar | square != null 
	 *  @invar | square.isPassable() == true
	 *  @representationObject
	 */
	private final Square square;
	
	/** @basic */
	public Square getSquare() { return square; }
	
	/**
	 * Constructor of Dot objects
	 * 
	 * @throws IllegalArgumentException | square == null
 	 * @throws IllegalArgumentException | square.isPassable() == false
	 * 
	 * @post | getSquare().equals(square)
	 */
	public Dot(Square square) { 
		if (square == null)
			throw new IllegalArgumentException("given square is null");
		if (square.isPassable() == false)
			throw new IllegalArgumentException("given square is not passable");
		
		this.square = square;

	}

}
