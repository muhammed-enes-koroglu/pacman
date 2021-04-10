package pacman;

import java.util.Random;

public class VulnerableGhostState extends GhostState {

	private int tic;
	
	@Override
	public boolean isVulnerable() { return true; }
	
	public VulnerableGhostState() {
		tic = 0;
	}

	@Override
	public GhostState move(Ghost ghost, Random random) {
		tic++;
		if (tic % 2 == 0)
			ghost.reallyMove(random);
		if (tic < 12)
			return this;
		return new RegularGhostState();
	}
	
}
