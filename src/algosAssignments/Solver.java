package algosAssignments;
import java.util.Comparator;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

public class Solver {

	private Board board;
	private SearchNode solution = null;

	private abstract class SearchNode implements Comparator<SearchNode>,
			Comparable<SearchNode> {

		public abstract int compare(SearchNode o1, SearchNode o2);

		public int compareTo(SearchNode o) {
			return compare(this, o);
		}

		private Board element;
		private int moves;
		private SearchNode previousElement;

		public SearchNode(Board element, int moves, SearchNode previousE) {
			this.element = element;
			this.moves = moves;
			this.previousElement = previousE;
		}

		public Board getElement() {
			return element;
		}

		public int getMoves() {
			return moves;
		}

		public SearchNode getPrevious() {
			return previousElement;
		}

	}

	private class SNManhattan extends SearchNode {
		public SNManhattan(Board element, int moves, SearchNode previousE) {
			super(element, moves, previousE);
		}

		@Override
		public int compare(SearchNode o1, SearchNode o2) {
			return Integer.compare(o1.getElement().manhattan() + o1.getMoves(),
					o2.getElement().manhattan() + o2.getMoves());
		}

	}

	private class SNHamming extends SearchNode {
		public SNHamming(Board element, int moves, SearchNode previousE) {
			super(element, moves, previousE);
		}

		@Override
		public int compare(SearchNode o1, SearchNode o2) {
			return Integer.compare(o1.getElement().hamming() + o1.getMoves(),
					o2.getElement().hamming() + o2.getMoves());
		}

	}

	public Solver(Board initial) {
		// find a solution to the initial board (using the A* algorithm)
		if (initial == null) {
			throw new java.lang.NullPointerException();
		}
		this.board = initial;
		if (!board.isGoal()) {
			findSolution();
		} else {
			this.solution = new SNManhattan(initial, 0, null);
		}

	}

	private void findSolution() {
		int j = 0;
		MinPQ<SearchNode> heapThis = new MinPQ<SearchNode>();
		MinPQ<SearchNode> heapTwin = new MinPQ<SearchNode>();
		SearchNode newsnThis = new SNManhattan(board, 0, null);
		SearchNode newsnTwin = new SNManhattan(board.twin(), 0, null);
		heapThis.insert(newsnThis);
		heapTwin.insert(newsnTwin);
		boolean done = (newsnThis.getElement().isGoal());
		boolean unsolveable = newsnThis.getElement().isGoal();
		int cnt = 0;
		//int maxRuns = 10000;
		while (!done && !unsolveable /*&& cnt < maxRuns*/) {
			cnt++;
			done = findNextSet(heapThis);
			unsolveable = findNextSet(heapTwin);
		}
		if (unsolveable) {
			this.solution = null;
		}
	}

	private boolean findNextSet(MinPQ<SearchNode> heap) {
		boolean retVal = false;
		SearchNode min = heap.delMin();
		Board forbid = (min.getPrevious() != null ? min.getPrevious()
				.getElement() : null);
		Iterable<Board> neighbors = min.getElement().neighbors();
		for (Board bd : neighbors) {
			if (!bd.equals(forbid)) {
				SearchNode sn = new SNManhattan(bd, min.getMoves() + 1, min);
				heap.insert(sn);
				if (bd.isGoal()) {
					this.solution = sn;
					retVal = true;
				}
			}
		}
		return retVal;
	}

	public boolean isSolvable() {
		return (solution != null);
		// is the initial board solvable?

	}

	public int moves() {
		// min number of moves to solve initial board; -1 if unsolvable
		if (!isSolvable()) {
			return -1;
		} else {
			return solution.getMoves();
		}
	}

	public Iterable<Board> solution() {

		// sequence of boards in a shortest solution; null if unsolvable
		if (!isSolvable()) {
			return null;
		} else {
			SearchNode sn = solution;
			Stack<Board> sol = new Stack<Board>();
			sol.push(sn.getElement());
			while (sn.getPrevious() != null) {
				sol.push(sn.getPrevious().getElement());
				sn = sn.getPrevious();
			}
			return sol;
		}
	}

	public static void main(String[] args) {
		// solve a slider puzzle (given below)
		// create initial board from file
		In in = new In(args[0]);
		int N = in.readInt();
		int[][] blocks = new int[N][N];
		for (int i = 0; i < N; i++)
			for (int j = 0; j < N; j++)
				blocks[i][j] = in.readInt();
		Board initial = new Board(blocks);
		StdOut.printf("Hamming distance is %d\n", initial.hamming());
		// solve the puzzle
		Solver solver = new Solver(initial);

		// print solution to standard output
		if (!solver.isSolvable())
			StdOut.println("No solution possible");
		else {
			StdOut.println("Minimum number of moves = " + solver.moves());
			for (Board board : solver.solution())
				StdOut.println(board);
		}
	}
}