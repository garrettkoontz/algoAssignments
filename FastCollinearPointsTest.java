import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Test;


public class FastCollinearPointsTest {

	@Test
	public void testBruteCollinearPointsNone() {
		Point[] testArray = new Point[4];
		testArray[0] = new Point(0,0);
		testArray[1] = new Point(1,0);
		testArray[2] = new Point(0,1);
		testArray[3] = new Point(-1,-1);
		FastCollinearPoints bcp = new FastCollinearPoints(testArray);
		assertSame("Should have no segments",0, bcp.numberOfSegments());
		assertArrayEquals("Should have no line segments", new LineSegment[0], bcp.segments());
	}
	
	@Test
	public void testBruteCollinearPointsOne() {
		Point[] testArray = new Point[6];
		testArray[0] = new Point(0,0);
		testArray[1] = new Point(1,0);
		testArray[2] = new Point(0,1);
		testArray[3] = new Point(-1,-1);
		testArray[4] = new Point(1,1);
		testArray[5] = new Point(-2,-2);
		LineSegment[] lsa = new LineSegment[1];
		LineSegment ls = new LineSegment(testArray[5],testArray[4]);
		lsa[0] = ls;
		FastCollinearPoints bcp = new FastCollinearPoints(testArray);
		assertSame("Should have one segments",1, bcp.numberOfSegments());
		assertEquals("Should have one line segments",lsa[0].toString() , bcp.segments()[0].toString());
	}
	
	
	@Test
	public void testBruteCollinearPointsOneMoreThan4() {
		
		Point[] testArray = new Point[5000];
		for(int i = 0; i < 5000; i++){
			testArray[i] = new Point(i,i);
		}
		LineSegment[] lsa = new LineSegment[1];
		LineSegment ls = new LineSegment(testArray[0],testArray[4999]);
		lsa[0] = ls;
		FastCollinearPoints bcp = new FastCollinearPoints(testArray);
		assertSame("Should have one segments",1, bcp.numberOfSegments());
		assertEquals("Should have one line segments",lsa[0].toString() , bcp.segments()[0].toString());
	}
	
	@Test
	public void testBruteCollinearPointsEdge() {
		Point[] testArray = new Point[4];
		testArray[0] = new Point(0,0);
		testArray[1] = new Point(-1,-1);
		testArray[2] = new Point(1,1);
		testArray[3] = new Point(-2,-2);
		LineSegment[] lsa = new LineSegment[1];
		LineSegment ls = new LineSegment(testArray[3],testArray[2]);
		lsa[0] = ls;
		FastCollinearPoints bcp = new FastCollinearPoints(testArray);
		assertSame("Should have one segments",1, bcp.numberOfSegments());
		assertEquals("Should have one line segments",lsa[0].toString() , bcp.segments()[0].toString());
	}
	
	@Test
	public void testBruteCollinearPointsTwo() {
		Point[] testArray = new Point[8];
		testArray[0] = new Point(0,0);
		testArray[1] = new Point(1,0);
		testArray[2] = new Point(0,1);
		testArray[3] = new Point(-1,-1);
		testArray[4] = new Point(1,1);
		testArray[5] = new Point(-2,-2);
		testArray[6] = new Point(2,0);
		testArray[7] = new Point(-1,0);
		LineSegment[] lsa = new LineSegment[2];
		LineSegment ls1 = new LineSegment(testArray[5],testArray[4]);
		LineSegment ls2 = new LineSegment(testArray[7],testArray[6]);
		lsa[1] = ls1;
		lsa[0] = ls2;
		FastCollinearPoints bcp = new FastCollinearPoints(testArray);
		assertSame("Should have two segments",2, bcp.numberOfSegments());
		assertEquals("Should have two line segments",lsa[0].toString() , bcp.segments()[0].toString());
		assertEquals("Should have two line segments",lsa[1].toString() , bcp.segments()[1].toString());
	}
	
	//TODO test error throwing.

}
