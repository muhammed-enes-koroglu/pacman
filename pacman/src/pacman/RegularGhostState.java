package pacman;

import java.util.Random;

import org.junit.jupiter.api.Order;

public class RegularGhostState extends GhostState{

	@Override
	public boolean isVulnerable() { return false; }
	
	public RegularGhostState() {}

	@Override
	public GhostState move(Ghost ghost, Random random) {
		ghost.reallyMove(random);
		return this;
	}

	@Override
	public GhostState hitBy(Ghost ghost, PacMan pacMan) {
		pacMan.die();
		return this;
	}
	
	
}
