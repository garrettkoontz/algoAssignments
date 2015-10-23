import java.util.Arrays;

public class FastCollinearPoints {

	private Point[] pts;
	private int numSegments;
	private LineSegment[] segments;

	public FastCollinearPoints(Point[] points) {
		if (points == null) {
			throw new java.lang.NullPointerException();
		}
		for (int i = 0; i < points.length; i++) {
			if (points[i] == null) {
				throw new java.lang.NullPointerException();
			}
			for (int j = i + 1; j < points.length; j++) {
				if (points[i].equals(points[j])) {
					throw new java.lang.IllegalArgumentException();
				}
			}
		}
		this.pts = points;
		this.findSegments();
		// finds all line segments containing 4 points
	}

	private void findSegments() {
		Arrays.sort(pts);
		LineSegment[] segs = new LineSegment[pts.length - 3];
		int cnt = 0;
		for (int i = 0; i < pts.length; i++) {
			double[] slopes = new double[pts.length - i - 1];
			for (int j = i + 1; j < pts.length; j++) {
				slopes[j-i-1] = pts[i].slopeTo(pts[j]);
			}
		}
		numSegments = cnt;
		segments =  Arrays.copyOfRange(segs, 0, cnt);
		segs = null;
	}

	public int numberOfSegments() {
		return numSegments;
		// the number of line segments
	}

	public LineSegment[] segments() {
		return segments;
		// the line segments
	}
}