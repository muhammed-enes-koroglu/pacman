package pacman;

public class PowerPellet extends FoodItem {

	/** 
	 * @basic
	 * @post | result == 2
	 */
	@Override
	public int getSize() { return 2; }
	
	public PowerPellet(Square square) {
		super(square, 2);
	}

}
