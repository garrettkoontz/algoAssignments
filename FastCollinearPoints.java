import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
		this.pts = Arrays.copyOf(points, points.length);
		this.findSegments();
		// finds all line segments containing 4 points
	}

	private class SegmentsHolder {
		private List<Point> segPts;

		public SegmentsHolder(Point... points) {
			this.segPts = Arrays.asList(points);
		}

		public List<Point> getPoints() {
			return segPts;
		}

		public boolean equals(SegmentsHolder that) {
			if (this.segPts.containsAll(that.segPts)
					|| that.segPts.containsAll(this.segPts)) {
				return true;
			} else {
				return false;
			}
		}

	}

	private void findSegments() {
		Point[] localPts = Arrays.copyOf(pts, pts.length);
		List<LineSegment> segs = new ArrayList<LineSegment>();
		int cnt = 0;
		Set<SegmentsHolder> sis = new HashSet<SegmentsHolder>();
		while (localPts.length > 3) {
			Point thisI = localPts[0];
			Arrays.sort(localPts, thisI.slopeOrder());
			int length = 0;
			for (int j = 0; j < localPts.length - 1; j++) {
				boolean same = thisI.slopeTo(localPts[j]) == thisI
						.slopeTo(localPts[j + 1]);
				if ((length > 1 && !same)) {
					cnt += addToList(localPts, j, length, thisI, segs, cnt);
					length = 0;
				} else if (length > 0 && same && j == (localPts.length - 2)) {
					cnt += addToList(localPts, ++j, ++length, thisI, segs, cnt);
				} else if (same) {
					length++;
				} else {
					length = 0;
				}

			}
			localPts = Arrays.copyOfRange(localPts, 1, localPts.length);
		}
		numSegments = cnt;
		segments = segs.toArray(new LineSegment[0]);
	}

	private int addToList(Point[] localPts, int j, int length, Point thisI,
			List<LineSegment> segs, int cnt) {
		Point[] allEqual = Arrays.copyOf(
				Arrays.copyOfRange(localPts, j - length, j + 1), length + 2);
		allEqual[length + 1] = thisI;
		Arrays.sort(allEqual);
		SegmentsHolder thissh = new SegmentsHolder(allEqual);
		segs.add(new LineSegment(allEqual[0], allEqual[allEqual.length - 1]));
		return 1;

	}

	public int numberOfSegments() {
		return numSegments;
		// the number of line segments
	}

	public LineSegment[] segments() {
		return Arrays.copyOf(segments, segments.length);
		// the line segments
	}
}