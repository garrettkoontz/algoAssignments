package algosAssignments;
import java.util.ArrayList;
import java.util.List;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

	private WeightedQuickUnionUF percoSet;
	private int bottom;
	private int range;
	private boolean[] openSites;
	private int top;

	public Percolation(int N) {
		if (N <= 0) {
			throw new java.lang.IllegalArgumentException(
					"N is less than or equal to 0");
		}
		this.range = N;
		this.bottom = 0;
		this.top = N * N + 1;
		this.percoSet = new WeightedQuickUnionUF(top + 1);
		this.openSites = new boolean[top + 1];
		this.openSites[bottom] = true;
		this.openSites[top] = true;
	} // create N-by-N grid, with all sites blocked

	private int convert(int i, int j) {
		return (i - 1) * range + j;
	}

	public void open(int i, int j) {
		if (validate(i, j)) {
			int thisInt = convert(i, j);
			if (!this.openSites[thisInt]) {
				for (Tuple<Integer> pair : getNeighbors(i, j)) {
					int potentialNeighbor = convert(pair._1, pair._2);
					if (isOpen(potentialNeighbor))
						percoSet.union(thisInt, potentialNeighbor);
				}
				this.openSites[thisInt] = true;
				if(isFull(i,j)){
					percoSet.union(thisInt, top);
				}
			}
		} else {
			throw new java.lang.IndexOutOfBoundsException(
					"index is out of bounds");
		}
		// open site (row i, column j) if it is not open already
	}

	public boolean isOpen(int i, int j) {
		if (validate(i, j)) {
			return isOpen(convert(i, j));

		} else {
			throw new java.lang.IndexOutOfBoundsException(
					"index is out of bounds");
		}
		// is site (row i, column j) open?
	}

	private boolean isOpen(int convert) {
		if (convert >= bottom && convert <= top) {
			return this.openSites[convert];

		} else {
			throw new java.lang.IndexOutOfBoundsException(
					"index is out of bounds");
		}

	}

	public boolean isFull(int i, int j) {
		if (validate(i, j)) {
			return this.percoSet.connected(bottom, convert(i, j));
		} else {
			throw new java.lang.IndexOutOfBoundsException(
					"index is out of bounds");
		}
	}

	public boolean percolates() {
		for (int i = convert(range,1); i <= convert(range,range); i++){
			if (this.percoSet.connected(bottom, i)) return true;
		}
		return false;
		// does the system percolate?
	}

	private List<Tuple<Integer>> getNeighbors(int i, int j) {
		if (validate(i, j)) {
			List<Tuple<Integer>> output = new ArrayList<Tuple<Integer>>();
			int areaSwitch = edge(i, j);
			switch (areaSwitch) {
			case 0:
				output.add(new Tuple<Integer>(i + 1, j));
				output.add(new Tuple<Integer>(i - 1, j));
				output.add(new Tuple<Integer>(i, j - 1));
				output.add(new Tuple<Integer>(i, j + 1));
				break;
			case 1:
				output.add(new Tuple<Integer>(i + 1, j));
				output.add(new Tuple<Integer>(i, j - 1));
				output.add(new Tuple<Integer>(i, j + 1));
				output.add(new Tuple<Integer>(1, 0));
				break;
			case 2:
				output.add(new Tuple<Integer>(i - 1, j));
				output.add(new Tuple<Integer>(i, j - 1));
				output.add(new Tuple<Integer>(i, j + 1));
//				if (isFull(i - 1, j) || isFull(i, j - 1) || isFull(i, j + 1)) {
//					output.add(new Tuple<Integer>(range + 1, 2));
//				} else
//					output.add(new Tuple<Integer>(range + 1, 1));
				break;
			case 3:
				output.add(new Tuple<Integer>(i + 1, j));
				output.add(new Tuple<Integer>(i - 1, j));
				output.add(new Tuple<Integer>(i, j + 1));
				break;
			case 4:
				output.add(new Tuple<Integer>(i + 1, j));
				output.add(new Tuple<Integer>(i - 1, j));
				output.add(new Tuple<Integer>(i, j - 1));
				break;

			case 13:
				output.add(new Tuple<Integer>(i + 1, j));
				output.add(new Tuple<Integer>(i, j + 1));
				output.add(new Tuple<Integer>(1, 0));
				break;
			case 23:
				output.add(new Tuple<Integer>(i - 1, j));
				output.add(new Tuple<Integer>(i, j + 1));
//				if (isFull(i - 1, j) || isFull(i, j + 1)) {
//					output.add(new Tuple<Integer>(range + 1, 2));
//				} else
//					output.add(new Tuple<Integer>(range + 1, 1));
				break;

			case 14:
				output.add(new Tuple<Integer>(i + 1, j));
				output.add(new Tuple<Integer>(i, j - 1));
				output.add(new Tuple<Integer>(1, 0));
				break;
			case 24:
				output.add(new Tuple<Integer>(i - 1, j));
				output.add(new Tuple<Integer>(i, j - 1));
//				if (isFull(i - 1, j) || isFull(i, j - 1)) {
//					output.add(new Tuple<Integer>(range + 1, 2));
//				} else
//					output.add(new Tuple<Integer>(range + 1, 1));
				break;
			case 1234:
				output.add(new Tuple<Integer>(1, 0));
				output.add(new Tuple<Integer>(range + 1, 1));
			default:
				break;
			}
			return output;
		} else
			return new ArrayList<Tuple<Integer>>();

	}

	private class Tuple<T extends Object> {
		public T _1;
		public T _2;

		public Tuple(T i, T j) {
			_1 = i;
			_2 = j;
		}

		@Override
		public String toString() {
			return "Tuple[" + _1 + ", " + _2 + "]";
		}
	}

	private int edge(int i, int j) {
		String outString = "0";
		if (i == 1)
			outString += 1;
		if (i == range)
			outString += 2;
		if (j == 1)
			outString += 3;
		if (j == range)
			outString += 4;
		return Integer.parseInt(outString);
	}

	private boolean validate(int i, int j) {
		if (i <= 0 || j <= 0 || i > range || j > range)
			return false;
		return true;
	}

	private boolean validate(int c) {
		if (c <= bottom || c >= top)
			return false;
		return true;
	}

	
	public static void main(String[] args) {
		Percolation perco = new Percolation(10);
		perco.open(1, 1);
		perco.open(2, 1);
		perco.open(2, 2);
		perco.open(2, 3);
		perco.open(10, 10);
		StdOut.println(perco.percolates());
		perco.open(3, 1);
		perco.open(4, 1);
		perco.open(5, 1);
		perco.open(6, 1);
		perco.open(7, 1);
		perco.open(10, 1);
		perco.open(8, 1);
		perco.open(9, 1);

		perco.open(5, 5);
		StdOut.println(perco.isOpen(5, 5));
		StdOut.println(perco.isOpen(5, 6));
		StdOut.println(perco.percolates());

		try {
			Percolation perco2 = new Percolation(-1);
		} catch (java.lang.IllegalArgumentException e) {
			StdOut.println("caught constructor exception");
		}

		try {
			Percolation perco2 = new Percolation(3);
			perco2.open(10, 1);
			StdOut.println("Didn't catch index exception");
		} catch (java.lang.IndexOutOfBoundsException e) {
			StdOut.println("caught index exception");
		}

		try {
			Percolation perco2 = new Percolation(3);
			perco2.open(0, 1);
			StdOut.println("Didn't catch index exception");
		} catch (java.lang.IndexOutOfBoundsException e) {
			StdOut.println("caught index exception");
		}

		Percolation perco1 = new Percolation(1);
		perco1.open(1, 1);
		StdOut.println(perco1.percolates());

		// test client (optional)
	}
}
