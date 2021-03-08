package pacman;

/**
 * Each instance of this class represents the player-controlled Pac-Man character in a Pac-Man maze.
 * 
 * @mutable
 * 
 * @invar | getSquare() != null
 * @invar | getNbLives() >= 0
 */
public class PacMan {
	
	/**
	 * @invar | square != null
	 * @invar | nbLives >= 0
	 */
	private int nbLives;
	/** @representationObject */
	private Square square;
	
	/** @basic */	
	public Square getSquare() { 
		return square;
	}
	
	/** @basic */
	public int getNbLives() { 
		return nbLives;
	}

	/**
	 * @throws IllegalArgumentException if the given {@code nbLives} not greater than zero
	 * 		| nbLives <= 0
	 * @throws IllegalArgumentException if the given {@code square} is null
	 * 		| square == null
	 * 
	 * @post | getSquare().equals(square)
	 * @post | getNbLives() == nbLives
	 */
	public PacMan(int nbLives, Square square) {
		if (nbLives <= 0)
			throw new IllegalArgumentException("Given number of lives must be greater than zero");
		if (square == null)
			throw new IllegalArgumentException("given square is null");

		this.nbLives = nbLives;
		this.square = square;
	}
	
	/**
	 * @mutates | this
	 * 
	 * @throws IllegalArgumentException | square == null
	 * 
	 * @post | getSquare().equals(square)
	 */
	public void setSquare(Square square) { 
		if (square == null)
			throw new IllegalArgumentException("given square is null");
		
		this.square = square;
	}
	
	/**
	 * Decreases this Pac-Man character's number of lives by one.
	 * 
	 * @inspects | this
	 * @mutates | this
	 * 
	 * @throws Exception | getNbLives() == 0
	 * @post | getNbLives() == old(getNbLives()) - 1
	 * 
	 */
	public void die() { 
		if (getNbLives() == 0)
			throw new IllegalArgumentException("Number of lives is already zero");
		nbLives--;
	}

}
