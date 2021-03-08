package pacman;

/**
 * Each instance of this class represents a dot (a piece of food for Pac-Man) in a Pac-Man maze.
 * 
 * @immutable
 * 
 * @invar | getSquare() != null
 */
public class Dot {
	
	/** @invar | square != null */
	private Square square;
	
	/** @basic */
	public Square getSquare() { return square; }
	
	/**
	 * Constructor of Dot objects
	 * 
	 * @throws IllegalArgumentException | square == null
	 * 
	 * @post | getSquare().equals(square)
	 */
	public Dot(Square square) { 
		if (square == null)
			throw new IllegalArgumentException("given square is null");
		this.square = square;

	}

}
