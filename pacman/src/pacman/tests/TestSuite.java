package pacman.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TestSuite {

	@Test
	void test() {
		PacManTest pt = new PacManTest();
		GhostTest gt = new GhostTest();
		MazeMapTest mmt = new MazeMapTest();
		SquareTest st = new SquareTest();
		DotTest dt = new DotTest();
		
		pt.test();
		gt.test();
		mmt.test();
		st.test();
		dt.test();
	}

}
