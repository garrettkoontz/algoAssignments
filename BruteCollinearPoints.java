import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class BruteCollinearPoints {

	private Point[] pts;
	private int numSegments;
	private LineSegment[] segments;

	public BruteCollinearPoints(Point[] points) {
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
			for (int j = i + 1; j < pts.length; j++) {
				for (int k = j + 1; k < pts.length; k++) {
					for (int m = k + 1; m < pts.length; m++) {
						if(pts[i].slopeTo(pts[j]) == pts[i].slopeTo(pts[k]) && pts[i].slopeTo(pts[j]) == pts[i].slopeTo(pts[m])){
							segs[cnt++] = (new LineSegment(pts[i],pts[m]));
						}
					}
				}
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
