import static org.junit.Assert.*;

import org.junit.Test;

public class PointTest {

	@Test
	public void testSlopeToPos() {
		Point p1 = new Point(0, 0);
		Point p2 = new Point(10, 10);
		assertEquals("Slope should be 1.0", 1.0, p1.slopeTo(p2),0.0000000001);
	}

	@Test
	public void testSlopeToNeg() {
		Point p1 = new Point(-10, 10);
		Point p2 = new Point(0, 0);
		assertEquals("Slope should be -1.0", -1.0, p1.slopeTo(p2),0.0000000001);
	}

	@Test
	public void testSlopeToSame() {
		Point p1 = new Point(0, 0);
		Point p2 = new Point(0, 0);
		assertEquals("Slope should be Double.NEGATIVE_INFINITY", (new Double(
				Double.NEGATIVE_INFINITY)).toString(),
				(new Double((p1.slopeTo(p2)))).toString());
	}

	@Test
	public void testSlopeToHoriz() {
		Point p1 = new Point(10, 0);
		Point p2 = new Point(0, 0);

		assertEquals("Slope should be 0.0", +0.0, p1.slopeTo(p2),0.0000000001);
	}

	@Test
	public void testSlopeToVert() {
		Point p1 = new Point(0, 10);
		Point p2 = new Point(0, 0);
		assertEquals("Slope should be Double.POSITIVE_INFINITY", (new Double(
				Double.POSITIVE_INFINITY)).toString(),
				(new Double((p1.slopeTo(p2)))).toString());
	}

	@Test
	public void testCompareToEqual() {
		Point p1 = new Point(0, 0);
		Point p2 = new Point(0, 0);
		assertSame("Compare should be 0", 0, p1.compareTo(p2));
	}

	@Test
	public void testCompareToLessOnY() {
		Point p1 = new Point(-1, 0);
		Point p2 = new Point(0, 0);
		assertSame("Compare should be -1", -1, p1.compareTo(p2));
	}

	@Test
	public void testCompareToLessOnX() {
		Point p1 = new Point(0, -1);
		Point p2 = new Point(0, 0);
		assertSame("Compare should be -1", -1, p1.compareTo(p2));
	}

	@Test
	public void testCompareToGreaterOnY() {
		Point p1 = new Point(1, 0);
		Point p2 = new Point(0, 0);
		assertSame("Compare should be 1", 1, p1.compareTo(p2));
	}

	@Test
	public void testCompareToGreaterOnX() {
		Point p1 = new Point(0, 1);
		Point p2 = new Point(0, 0);
		assertSame("Compare should be 1", 1, p1.compareTo(p2));
	}

	@Test
	public void testSlopeOrder() {
		fail("Not yet implemented");
	}

}
