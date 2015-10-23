import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {

	private double[] vals;
	private int size;
	private int times;

	public PercolationStats(int N, int T) {
		if (N <= 0 || T <= 0) {
			throw new java.lang.IllegalArgumentException("Invalid parameter");
		}
		this.size = N;
		this.times = T;
		vals = new double[this.times];
		double sizeSquare = ((double) this.size * this.size);
		for (int i = 0; i < this.times; i++) {
			PercolationResult percRes = new PercolationResult(this.size);
			vals[i] = (double) percRes.countOpen / sizeSquare;
		}
		// perform T independent experiments on an N-by-N grid
	}

	public double mean() {
		return StdStats.mean(vals);
		// sample mean of percolation threshold
	}

	public double stddev() {
		return StdStats.stddev(vals);
		// sample standard deviation of percolation threshold
	}

	public double confidenceLo() {
		return mean() - (1.96 * stddev()) / java.lang.Math.sqrt(times);
		// low endpoint of 95% confidence interval
	}

	public double confidenceHi() {
		return mean() + (1.96 * stddev()) / java.lang.Math.sqrt(times);
		// high endpoint of 95% confidence interval
	}

	public static void main(String[] args) {
		if (args.length < 2) {
			StdOut.println("Please enter two arguments");
		} else {
			int times = Integer.parseInt(args[0]);
			int size = Integer.parseInt(args[1]);
			PercolationStats percStat = new PercolationStats(size, times);
			StdOut.printf("mean                    = %f\n", percStat.mean());
			StdOut.printf("stddev                  = %f\n", percStat.stddev());
			StdOut.print("95% confidence interval = ");
			StdOut.printf("%f", percStat.confidenceLo());
			StdOut.print(", ");
			StdOut.printf("%f", percStat.confidenceHi());
			// percStat.confidenceLo(), percStat.confidenceHi());
		}

		// test client (described below)
	}

	private class PercolationResult {
		private int countOpen;
		private int range;

		public PercolationResult(int N) {
			this.range = N;
			this.countOpen = this.runExperiment(new Percolation(this.range));
		}

		private int runExperiment(Percolation perc) {
			int open = 0;
			while (!perc.percolates()) {
				int i = StdRandom.uniform(1, this.range + 1);
				int j = StdRandom.uniform(1, this.range + 1);
				if (!perc.isOpen(i, j)) {
					perc.open(i, j);
					open++;
				}
			}
			return open;

		}
	}
}