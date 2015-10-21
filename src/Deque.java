import java.util.Iterator;

public class Deque<Item> implements Iterable<Item> {

	private static final int INIT_SIZE = 2;
	private static final int BIGGER_SIZE = 2;
	private static final int SMALLER_SIZE = 4;
	private Item[] items = (Item[]) new Object[INIT_SIZE];
	private int currentArraySize = INIT_SIZE;

	private int first;
	private int last;

	public Deque() {
		this.first = 0;
		this.last = 1;
		// construct an empty deque
	}

	// resize the underlying array
	private void resize(int max) {
		int N = size();
		assert max >= N;
		Item[] temp = (Item[]) new Object[max];
		int mid = max / 4;
		for (int i = 0; i < N; i++) {
			int idx = (first + 1 + i) % items.length;
			temp[i + mid] = items[idx];
		}
		items = temp;
		first = mid-1;
		last = mid + N;
	}

	private int checkResize() {
		int N = size();
		if (N == items.length || (N > 0 && (last == items.length || first < 0))) {
			resize(2 * items.length);
			return 1;
		} else if (N > 0 && N == items.length / 4) {
			resize(items.length / 2);
			return -1;
		} else
			return 0;

		// if (first == 0 || last == (currentArraySize - 1)) {
		// int newSize = currentArraySize * BIGGER_SIZE;
		// Item[] newItems = (Item[]) new Object[newSize];
		// int j = (newSize / 2) - (currentArraySize / 2);
		// this.first = j;
		// this.last = j + size() + 1;
		// for (int i = 0; i < currentArraySize; i++) {
		// newItems[j++] = this.items[i];
		// }
		// this.items = newItems;
		// this.currentArraySize = newSize;
		// return 1;
		// } else if (size() + 2 * SMALLER_SIZE < currentArraySize) {
		// int newSize = currentArraySize / BIGGER_SIZE;
		// Item[] newItems = (Item[]) new Object[newSize];
		// int j = (newSize / 2) - (size() / 2);
		// for (int i = first; i <= this.last; i++) {
		// newItems[j++] = items[i];
		// }
		// this.first = (newSize / 2) - (size() / 2);
		// this.last = j - 1;
		// this.items = newItems;
		// this.currentArraySize = newSize;
		// return -1;
		// } else {
		// return 0;
		// }
	}

	public boolean isEmpty() {
		// is the deque empty?
		return first > last - 2;
	}

	public int size() {
		// return the number of items on the deque
		return last - first - 1;
	}

	public void addFirst(Item item) {
		if (item == null)
			throw new java.lang.NullPointerException();
		// add the item to the front
		if(first < 0) resize(2*items.length);
		this.items[first] = item;
		first -= 1;
	}

	public void addLast(Item item) {
		if (item == null)
			throw new java.lang.NullPointerException();
		// add the item to the end
		if(last == items.length) resize(2*items.length);
		this.items[last] = item;
		last += 1;
	}

	public Item removeFirst() {
		// remove and return the item from the front
		if (this.isEmpty())
			throw new java.util.NoSuchElementException();
		Item returnItem = items[++first];
		items[first] = null;
		if (size() > 0 && size() == items.length/4) resize(items.length/2); 
		return returnItem;
	}

	public Item removeLast() {
		// remove and return the item from the end
		if (this.isEmpty())
			throw new java.util.NoSuchElementException();
		Item returnItem = items[--last];
		items[last] = null;
		if (size() > 0 && size() == items.length/4) resize(items.length/2); 
		return returnItem;
	}

	private String printItems() {
		String output = "";
		for (int i = 0; i < currentArraySize; i++) {
			output += items[i] + "\n";

		}
		return output;
	}

	private Item getItem(int i) {
		if (i < first || i > last)
			return null;
		return items[i];
	}

	public Iterator<Item> iterator() {
		// return an iterator over items in order from front to end
		return new DequeIterator2(first + 1, last - 1);
	}

	private class DequeIterator2 implements Iterator<Item> {
		private int first;
		private int last;
		private int idx;

		public DequeIterator2(int frst, int lst) {
			this.first = frst;
			this.last = lst;
			this.idx = frst;
		}

		public boolean hasNext() {
			return idx <= last;
		}

		public Item next() {
			if (this.hasNext()) {
				return getItem(idx++);
			} else
				throw new java.util.NoSuchElementException();
		}

		public void remove() {
			throw new java.lang.UnsupportedOperationException();
		}
	}

	private class DequeIterator<Item> implements Iterator<Item> {
		private int first;
		private int last;
		private Item[] items;
		private int idx;

		public DequeIterator(Item[] inItems, int first, int last) {
			this.first = first;
			this.last = last;
			this.items = inItems;
			this.idx = first;
		}

		public boolean hasNext() {
			return idx <= last;
		}

		public Item next() {
			return items[idx++];
		}

	}

	public static void main(String[] args) { // unit testing
		// Deque<Integer> testDeck = new Deque<Integer>();
		// System.out.println("Array: " + testDeck.printItems());
		// System.out.println("first:" + testDeck.first);
		// System.out.println("last:" + testDeck.last);
		// testDeck.addFirst(new Integer(10));
		// System.out.println("Array: " + testDeck.printItems());
		// System.out.println("first:" + testDeck.first);
		// System.out.println("last:" + testDeck.last);
		// testDeck.addFirst(new Integer(4));
		// System.out.println("Array: " + testDeck.printItems());
		// System.out.println("first:" + testDeck.first);
		// System.out.println("last:" + testDeck.last);
		// testDeck.addLast(new Integer(-3));
		// System.out.println("Array: " + testDeck.printItems());
		// System.out.println("Testing the iterator:");
		// for (Integer nt : testDeck) {
		// System.out.println(nt);
		// }
		//
		// System.out.println("first:" + testDeck.first);
		// System.out.println("last:" + testDeck.last);
		// System.out.println("Array: " + testDeck.printItems());
		// System.out.println(testDeck.removeLast() + " is -3");
		// System.out.println("first:" + testDeck.first);
		// System.out.println("last:" + testDeck.last);
		// System.out.println("Array: " + testDeck.printItems());
		// System.out.println(testDeck.removeLast() + " is 10");
		// System.out.println("first:" + testDeck.first);
		// System.out.println("last:" + testDeck.last);
		// System.out.println("Array: " + testDeck.printItems());
		// System.out.println(testDeck.removeLast() + " is 4");
		// System.out.println("first:" + testDeck.first);
		// System.out.println("last:" + testDeck.last);
		// System.out.println("Array: " + testDeck.printItems());
		//
		// System.out.println("IsEmpty = " + testDeck.isEmpty());
		//
		// for (Integer nt : testDeck) {
		// System.out.println(nt);
		// }

	}
}
