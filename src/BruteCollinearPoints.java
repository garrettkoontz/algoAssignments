import java.util.HashSet;
import java.util.Set;

public class BruteCollinearPoints {
	
	private Point[] pts;
	private int numSegments;
	private LineSegment[] segments;
	
	public BruteCollinearPoints(Point[] points) {
		if(points == null){
			throw new java.lang.NullPointerException();
		}
		for(int i=0; i < points.length; i++){
			if(points[i] == null){
				throw new java.lang.NullPointerException();
			}
			for(int j = i + 1; j < points.length; j++){
				if(points[i].equals(points[j])){
					throw new java.lang.IllegalArgumentException();
				}
			}
		}
		this.pts = points;
		this.findSegments();
		// finds all line segments containing 4 points
	}

	private void findSegments() {
		int numSegs = 0;
		Set<LineSegment> segs = new HashSet<LineSegment>();
		
	}

	public int numberOfSegments() {
		// the number of line segments
	}

	public LineSegment[] segments() {
		// the line segments
	}

}
