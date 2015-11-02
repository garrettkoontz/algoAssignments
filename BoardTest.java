import static org.junit.Assert.*;

import org.junit.Test;

public class BoardTest {

	@Test
	public void testBoard() {
		try {
			Board bd = new Board(new int[][] { { 2, 3 }, { 1, 0 } });
			assertTrue("No exception", true);
		} catch (Exception e) {
			fail("Caught Exception: " + e.getLocalizedMessage());
		}

	}

	@Test
	public void testDimension2() {
		try {
			Board bd = new Board(new int[][] { { 2, 3 }, { 1, 0 } });
			assertSame("Has dimension 2", 2, bd.dimension());
		} catch (Exception e) {
			fail("Caught Exception: " + e.getLocalizedMessage());
		}
	}

	@Test
	public void testDimension3() {
		try {
			Board bd = new Board(new int[][] { { 2, 3, 4 }, { 1, 0, 5 },
					{ 6, 7, 8 } });
			assertSame("Has dimension 3", 3, bd.dimension());
		} catch (Exception e) {
			fail("Caught Exception: " + e.getLocalizedMessage());
		}
	}

	@Test
	public void testHamming() {
		try {
			Board bd = new Board(new int[][] { { 2, 3 }, { 1, 0 } });
			assertSame("Hamming distance 3", 3, bd.hamming());
		} catch (Exception e) {
			fail("Caught Exception: " + e.getLocalizedMessage());
		}
	}

	@Test
	public void testManhattan() {
		try {
			Board bd = new Board(new int[][] { { 1, 2, 3 }, { 4, 0, 5 },
					{ 7, 8, 6 } });
			assertSame("Manhattan distance 2", 2, bd.manhattan());
		} catch (Exception e) {
			fail("Caught Exception: " + e.getLocalizedMessage());
		}
	}

	@Test
	public void testIsGoalTrue() {
		try {
			Board bd = new Board(new int[][] { { 1, 2, 3 }, { 4, 5, 6 },
					{ 7, 8, 0 } });
			assertTrue("is goal is true", bd.isGoal());
		} catch (Exception e) {
			fail("Caught Exception: " + e.getLocalizedMessage());
		}
	}
	
	@Test
	public void testIsGoalFalse() {
		try {
			Board bd = new Board(new int[][] { { 1, 2, 3 }, { 4, 5, 6 },
					{ 7, 0, 8} });
			assertFalse("is goal is true", bd.isGoal());
		} catch (Exception e) {
			fail("Caught Exception: " + e.getLocalizedMessage());
		}
	}

	@Test
	public void testTwin() {
		fail("Not yet implemented");
	}

	@Test
	public void testEqualsObjectDeepTrue() {
		try {
			Board bd1 = new Board(new int[][] { { 1, 2, 3 }, { 4, 5, 6 },
					{ 7, 0, 8} });
			Board bd2 = new Board(new int[][] { { 1, 2, 3 }, { 4, 5, 6 },
					{ 7, 0, 8} });
			assertTrue("Board1 equals board 2",bd1.equals(bd2));
		} catch (Exception e) {
			fail("Caught Exception: " + e.getLocalizedMessage());
		}
	}
	
	@Test
	public void testEqualsObjectTrivialTrue() {
		try {
			Board bd1 = new Board(new int[][] { { 1, 2, 3 }, { 4, 5, 6 },
					{ 7, 0, 8} });
			assertTrue("Board1 equals board 2",bd1.equals(bd1));
		} catch (Exception e) {
			fail("Caught Exception: " + e.getLocalizedMessage());
		}
	}
	
	@Test
	public void testEqualsObjectNullFalse() {
		try {
			Board bd1 = new Board(new int[][] { { 1, 2, 3 }, { 4, 5, 6 },
					{ 7, 0, 8} });
			assertFalse("Board1 equals board 2",bd1.equals(null));
		} catch (Exception e) {
			fail("Caught Exception: " + e.getLocalizedMessage());
		}
	}
	
	@Test
	public void testEqualsObjectDeepFalse() {
		try {
			Board bd1 = new Board(new int[][] { { 1, 2, 3 }, { 4, 5, 6 },
					{ 7, 0, 8} });
			Board bd2 = new Board(new int[][] { { 1, 2, 3 }, { 4, 5, 6 },
					{ 7, 8, 0} });
			assertFalse("Board1 equals board 2",bd1.equals(bd2));
		} catch (Exception e) {
			fail("Caught Exception: " + e.getLocalizedMessage());
		}
	}
	
	@Test
	public void testEqualsObjectFalseDiffObject() {
		try {
			Board bd1 = new Board(new int[][] { { 1, 2, 3 }, { 4, 5, 6 },
					{ 7, 0, 8} });
			assertFalse("Board1 equals board 2",bd1.equals("A String"));
		} catch (Exception e) {
			fail("Caught Exception: " + e.getLocalizedMessage());
		}
	}

	@Test
	public void testNeighbors() {
		fail("Not yet implemented");
	}

	@Test
	public void testToString() {
		fail("Not yet implemented");
	}

}
