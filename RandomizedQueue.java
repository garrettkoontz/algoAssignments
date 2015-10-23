import java.util.Iterator;

import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item> {

	// private class Linked<I> {
	// private I object;
	// private Linked<I> next;
	// private Linked<I> previous;
	//
	// public Linked(I obj) {
	// this.object = obj;
	// this.next = null;
	// }
	//
	// public Linked(I obj, Linked<I> nxt) {
	// this.object = obj;
	// this.next = nxt;
	// }
	//
	// public I getObject() {
	// return object;
	// }
	//
	// public Linked<I> getNext() {
	// return next;
	// }
	//
	// public I removeNextObject() {
	// if (next != null) {
	// Linked<I> newNext = next.getNext();
	// I output = next.getObject();
	// // this.next = newNext.getNext();
	// this.next = newNext;
	// // thisNext.setNext(null);
	// return output;
	// } else
	// return null;
	//
	// }
	//
	// public void setNext(Linked<I> nxt) {
	// this.next = nxt;
	// }
	//
	// public boolean hasNext() {
	// return next != null;
	// }
	//
	// public String toString() {
	// return "Item: " + this.object.toString();
	//
	// }
	//
	// }

	private int size;
	private Item[] items;

	// private RandomizedQueue<Item>.Linked<Item> queue;

	public RandomizedQueue() {
		this.size = 0;
		this.items = (Item[]) new Object[2];
		// this.queue = new Linked<Item>(null, new Linked<Item>(null));
		// construct an empty randomized queue
	}

	// resize the underlying array
	private void resize(int max) {
		int N = size();
		assert max >= N;
		Item[] temp = (Item[]) new Object[max];
		for (int i = 0; i < N; i++) {
			int idx = i % items.length;
			temp[i] = items[idx];
		}
		items = temp;
	}

	private int checkResize() {
		int N = size();
		if (N == items.length) {
			resize(2 * items.length);
			return 1;
		} else if (N > 0 && N == items.length / 4) {
			resize(items.length / 2);
			return -1;
		} else
			return 0;
	}

	public boolean isEmpty() {
		return this.size == 0;
		// is the queue empty?
	}

	public int size() {
		return this.size;
		// return the number of items on the queue
	}

	public void enqueue(Item item) {
		if (item == null) {
			throw new java.lang.NullPointerException();
		}
		checkResize();
		this.items[size++] = item;
		// Linked<Item> newItem = new Linked<Item>(item, queue.getNext());
		// queue.setNext(newItem);
		// size++;
		// add the item
	}

	public Item dequeue() {
		if (!isEmpty()) {
			// Linked<Item> que = queue;
			// int max = StdRandom.uniform(size);
			// for (int i = 0; i < max; i++) {
			// que = que.getNext();
			// }
			// Item output = que.removeNextObject();
			int idx = StdRandom.uniform(size);
			Item ph = this.items[size - 1];
			Item output = this.items[idx];
			this.items[idx] = ph;
			this.items[--size] = null;
			checkResize();
			return output;
		} else {
			throw new java.util.NoSuchElementException();
		}

		// remove and return a random item
	}

	public Item sample() {
		if (!isEmpty()) {
			// Linked<Item> que = queue.getNext();
			// if (size != 1) {
			// int idx = StdRandom.uniform(0, size);
			// for (int i = 0; i < idx; i++) {
			// que = que.getNext();
			// }
			// }
			// return que.getObject();
			return this.items[StdRandom.uniform(size)];
		} else {
			throw new java.util.NoSuchElementException();
		}
		// return (but do not remove) a random item
	}

	// private Item[] toArray() {
	// Item[] output = (Item[]) (new Object[size]);
	// Linked<Item> que = queue;
	// for (int i = 0; i < size; i++) {
	// output[i] = que.getNext().getObject();
	// que = que.getNext();
	// }
	// return output;
	//
	// }

	public Iterator<Item> iterator() {
		return new RandomQueueIterator(this);
		// return an independent iterator over items in random order
	}

	private class RandomQueueIterator implements Iterator<Item> {

		private int[] order;
		private int idx = 0;
		private Item[] items;

		public RandomQueueIterator(RandomizedQueue queue) {
			if (queue.size > 0) {
				order = new int[queue.size];
				for (int i = 0; i < queue.size; i++) {
					order[i] = i;
				}
				StdRandom.shuffle(order);
				items = (Item[]) queue.items;
			} else {
				order = new int[0];
			}
		}

		@Override
		public boolean hasNext() {
			return order.length == 0 ? false : idx < order.length;
		}

		@Override
		public Item next() {
			if (this.hasNext()) {
				return items[order[idx++]];
			} else {
				throw new java.util.NoSuchElementException();
			}
		}

		@Override
		public void remove() {
			throw new java.lang.UnsupportedOperationException();
		}

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
