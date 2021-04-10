package pacman;

public abstract class FoodItem {
	
	protected Square square;
	protected final int size;
	
	/**
	 * @basic
	 */
	public Square getSquare() { return square; }
		
	public abstract int getSize();
	
	public FoodItem(Square square, int size) {
		if (square == null)
			throw new IllegalArgumentException("`square` is null");
		this.square = square;
		this.size = size;
	}
	
}
