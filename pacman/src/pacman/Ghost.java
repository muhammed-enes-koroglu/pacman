package pacman;

import java.util.Random;

/**
 * Each instance of this class represents a ghost in a Pac-Man maze.
 * 
 * @mutable
 * 
 * @invar | getSquare() != null
 * @invar | getSquare().isPassable() == true
 * @invar | getDirection() != null
 */
public class Ghost {
	
	/**
	 * @invar | square != null
	 * @invar | square.isPassable() == true
	 * @invar | direction != null
	 */
	/** @representationObject */
	private Square square;
	/** @representationObject */
	private Direction direction;
	
	/** @basic */
	public Square getSquare() { return square; }
	
	/**
	 * Returns the direction in which this ghost will preferably move next.
	 * 
	 * @basic
	 */
	public Direction getDirection() { return direction; }
	
	/**
	 * @throws IllegalArgumentException | square == null
	 * @throws IllegalArgumentException | square.isPassable() == false
	 * @throws IllegalArgumentException | direction == null
	 * 
	 * @post | getSquare().equals(square)
	 * @post | getDirection() == direction
	 */
	public Ghost(Square square, Direction direction) { 
		if (square == null)
			throw new IllegalArgumentException("given square may not be null");
		if (square.isPassable() == false)
			throw new IllegalArgumentException("given square is not passable");
		if (direction == null)
			throw new IllegalArgumentException("given direction may not be null");
		
		this.square = square;
		this.direction = direction;
	}
	
	/**
	 * @throws IllegalArgumentException | square == null
	 * @throws IllegalArgumentException | square.isPassable() == false
	 * 
	 * @post | getSquare().equals(square)
	 */
	public void setSquare(Square square) { 
		if (square == null)
			throw new IllegalArgumentException("given square may not be null");
		if (square.isPassable() == false)
			throw new IllegalArgumentException("given square is not passable");
		
		this.square = square;
	}
	
	/**
	 * @throws IllegalArgumentException | direction == null
	 * 
	 * @post | getDirection() == direction
	 */
	public void setDirection(Direction direction) { 
		if (direction == null)
			throw new IllegalArgumentException("given direction may not be null");
		this.direction = direction;
	}
	
	private static int MOVE_FORWARD_PREFERENCE = 10;
	
	// No formal document required
	public Direction chooseNextMoveDirection(Random random) {
		int moveForwardPreference = getSquare().canMove(getDirection()) ? MOVE_FORWARD_PREFERENCE : 0;
		Direction[] passableDirections = getSquare().getPassableDirectionsExcept(getDirection().getOpposite());
		if (passableDirections.length == 0)
			return getDirection().getOpposite();
		int result = random.nextInt(moveForwardPreference + passableDirections.length);
		if (result < moveForwardPreference)
			return getDirection();
		return passableDirections[result - moveForwardPreference];
	}
	
	// No formal document required
	public void move(Random random) {
		setDirection(chooseNextMoveDirection(random));
		setSquare(getSquare().getNeighbor(getDirection()));
	}
}
