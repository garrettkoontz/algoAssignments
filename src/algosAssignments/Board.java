package algosAssignments;
import java.util.Arrays;

import edu.princeton.cs.algs4.Stack;

/**
 * 
 * @author garrett
 *
 */

public class Board {

	private int[][] blocks;
	//private int[][] goal;
	private int N;

	public Board(int[][] blocks) {
		// construct a board from an N-by-N array of blocks
		this.blocks = deepCopy(blocks);
		this.N = blocks.length;
		//this.constructGoal();
	}

//	private int[][] constructGoal() {
//		int[][] goal = new int[blocks.length][blocks.length];
//		int val = 1;
//		for (int i = 0; i < blocks.length; i++) {
//			for (int j = 0; j < blocks.length; j++) {
//				goal[i][j] = val++;
//			}
//		}
//		goal[N - 1][N - 1] = 0;
//		return goal;
//	}

	// (where blocks[i][j] = block in row i, column j)
	public int dimension() {
		return N;
		// board dimension N
	}

	public int hamming() {
		// number of blocks out of place
		int output = 0;
		for (int i = 0; i < blocks.length; i++) {
			for (int j = 0; j < blocks.length; j++) {
				if (this.blocks[i][j] != goalValAt(i, j) && this.blocks[i][j] != 0)
					output++;
			}
		}
		return output;
	}

	private int goalValAt(int i, int j) {
		if (!validate(i, j)) {
			return -1;
		}
		return (i * N + j + 1) % (N * N);
	}

	private boolean validate(int i, int j) {
		return !(i >= N || i < 0 || j >= N || j < 0);
	}

	public int manhattan() {
		// sum of Manhattan distances between blocks and goal
		int output = 0;
		for (int i = 0; i < blocks.length; i++) {
			for (int j = 0; j < blocks.length; j++) {
				int val = this.blocks[i][j];
				output += manDist(val, i, j);
			}
		}
		return output;
	}

	private int manDist(int val, int i, int j) {
		// j distance is val mod N; i distance is val divided by N
		if (validate(i, j) && val != 0) {
			int jDist = abs(j - ((val - 1) % N));
			int iDist = abs(i - ((val - 1) / N));
			return abs(j - ((val - 1) % N)) + abs(i - ((val - 1) / N));
		} else {
			return 0;
		}
	}

	private int abs(int x) {
		if (x < 0) {
			return -x;
		} else
			return x;
	}

	public boolean isGoal() {
		return hamming() == 0;
		// is this board the goal board?
	}

	private static int[][] deepCopy(int[][] array) {
		int[][] retArray = new int[array.length][];
		for (int i = 0; i < array.length; i++) {
			int[] subarray = new int[array.length];
			for (int j = 0; j < array.length; j++) {
				subarray[j] = array[i][j];				
			}
			retArray[i] = subarray;
		}
		return retArray;
	}

	public Board twin() {
		// a board that is obtained by exchanging any pair of blocks
		int[][] newBlocks = deepCopy(this.blocks);

		// Board newBoard = new Board();
		if (newBlocks[0][0] != 0) {
			if (newBlocks[0][1] != 0) {
				swap(newBlocks, 0, 0, 0, 1);
			} else {
				swap(newBlocks, 0, 0, 1, 0);
			}
		} else {
			swap(newBlocks, 0, 1, 1, 0);
		}

		return new Board(newBlocks);
	}

	private static void swap(int[][] array, int ix, int iy, int jx, int jy) {

		int tmp = array[ix][iy];
		array[ix][iy] = array[jx][jy];
		array[jx][jy] = tmp;
	}

	public boolean equals(Object y) {
		// does this board equal y?
		if (this == y)
			return true;
		if (y == null)
			return false;
		else if (y.getClass() == this.getClass()) {
			return Arrays.deepEquals(this.blocks, ((Board) y).blocks);
		}
		return false;

	}

	public Iterable<Board> neighbors() {
		int[] open = openSpace();
		int xOpen = open[0];
		int yOpen = open[1];
		Stack<Board> output = new Stack<Board>();
		if (validate(xOpen - 1, yOpen)) {
			int[][] newblocks = deepCopy(this.blocks);
			swap(newblocks, xOpen - 1, yOpen, xOpen, yOpen);
			Board bd = new Board(newblocks);
			output.push(bd);
		}
		if (validate(xOpen + 1, yOpen)) {
			int[][] newblocks = deepCopy(this.blocks);
			swap(newblocks, xOpen + 1, yOpen, xOpen, yOpen);
			Board bd = new Board(newblocks);
			output.push(bd);
		}
		if (validate(xOpen, yOpen - 1)) {
			int[][] newblocks = deepCopy(this.blocks);
			swap(newblocks, xOpen, yOpen - 1, xOpen, yOpen);
			output.push(new Board(newblocks));
		}
		if (validate(xOpen, yOpen + 1)) {
			int[][] newblocks = deepCopy(this.blocks);
			swap(newblocks, xOpen, yOpen + 1, xOpen, yOpen);
			output.push(new Board(newblocks));
		}
		return output;
		// all neighboring boards
	}

	private int[] openSpace() {
		for (int i = 0; i < blocks.length; i++) {
			for (int j = 0; j < blocks.length; j++) {
				if (blocks[i][j] == 0) {
					return new int[] { i, j };
				} else {
					continue;
				}
			}
		}
		return new int[] { N, N };
	}

	public String toString() {
		// string representation of this board (in the output format specified
		// below)
		StringBuilder s = new StringBuilder();
		s.append(N + "\n");
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				s.append(String.format("%2d ", blocks[i][j]));
			}
			s.append("\n");
		}
		return s.toString();
	}

	public static void main(String[] args) {
		// unit tests (not graded)
		Board bd = new Board(new int[][] { { 1, 2 }, { 3, 4 } });
		System.out.println(bd.dimension());
	}
}