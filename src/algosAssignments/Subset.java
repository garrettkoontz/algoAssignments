package algosAssignments;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Subset {

	public static void main(String[] args) {
		RandomizedQueue<String> que = new RandomizedQueue<String>();
		String[] input = StdIn.readAllStrings();
		for (int i = 0; i < input.length; i++) {
			que.enqueue(input[i]);
		}
		input = null;
		for(int i = 0; i < (Integer.parseInt(args[0])); i++){
			StdOut.println(que.dequeue());
		}

		// TODO Auto-generated method stub

	}

}
