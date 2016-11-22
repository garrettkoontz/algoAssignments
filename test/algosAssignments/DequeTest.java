package algosAssignments;
import static org.junit.Assert.*;

import org.junit.Test;

public class DequeTest {

	@Test
	public void testDeque() {
		try {
			Deque deck = new Deque();
		} catch (Exception e) {
			fail("Caught an exception: " + e.getLocalizedMessage());
		}
	}

	@Test
	public void isEmptyNewDeck() {
		Deque<Integer> deck = new Deque();
		assertTrue("New deque is empty: ", deck.isEmpty());
	}

	@Test
	public void isEmptyAddedDeck() {
		Deque<Integer> deck = new Deque();
		deck.addFirst(new Integer(10));
		assertFalse("Deque with 10 is not empty", deck.isEmpty());
	}

	@Test
	public void isEmptyAddedFirstAndRemovedDeck() {
		Deque<Integer> deck = new Deque();
		deck.addFirst(new Integer(10));
		deck.removeFirst();
		assertTrue("Deque add first and delete deck is empty: ", deck.isEmpty());
	}

	@Test
	public void isEmptyAddedLastAndRemovedDeck() {
		Deque<Integer> deck = new Deque();
		deck.addLast(new Integer(10));
		deck.removeLast();
		assertTrue("Deque add last and delete deck is empty: ", deck.isEmpty());
	}

	@Test
	public void isEmptyAddedFirstAndRemovedLastDeck() {
		Deque<Integer> deck = new Deque();
		deck.addFirst(new Integer(10));
		deck.removeLast();
		assertTrue("Deque add first and delete last deck is empty: ",
				deck.isEmpty());
	}

	@Test
	public void isEmptyAddedLastAndRemovedFirstDeck() {
		Deque<Integer> deck = new Deque();
		deck.addLast(new Integer(10));
		deck.removeFirst();
		assertTrue("Deque add last and delete first deck is empty: ",
				deck.isEmpty());
	}

	@Test
	public void isEmptyAddedLastTwiceAndRemovedDeck() {
		Deque<Integer> deck = new Deque();
		deck.addLast(new Integer(0));
		deck.addLast(new Integer(1));
		deck.addLast(new Integer(2));
		deck.addLast(new Integer(3));
		deck.addLast(new Integer(4));
		deck.addLast(new Integer(5));
		deck.addLast(new Integer(6));
		deck.addLast(new Integer(7));
		deck.addLast(new Integer(8));
		deck.removeLast();
		assertFalse("Deque add, add and delete deck is not empty: ",
				deck.isEmpty());
	}

	@Test
	public void addFirstNull() {
		Deque<Integer> deck = new Deque();
		try {
			deck.addFirst(null);
			fail("Did not throw exception");
		} catch (NullPointerException npe) {
			assertTrue("Caught the exception", true);
		} catch (Exception e) {
			fail("Caught wrong exception: " + e.getLocalizedMessage());
		}
	}

	@Test
	public void testIsEmpty() {
		fail("Not yet implemented");
	}

	@Test
	public void testSize() {
		fail("Not yet implemented");
	}

	@Test
	public void testAddFirst() {
		fail("Not yet implemented");
	}

	@Test
	public void testAddLast() {
		fail("Not yet implemented");
	}

	@Test
	public void testRemoveFirst() {
		fail("Not yet implemented");
	}

	@Test
	public void testRemoveLast() {
		fail("Not yet implemented");
	}

	@Test
	public void testIterator() {
		Deque<Integer> deck = new Deque();
		deck.addLast(new Integer(0));
		deck.addLast(new Integer(1));
		deck.addLast(new Integer(2));
		deck.addLast(new Integer(3));
		deck.addLast(new Integer(4));
		deck.addLast(new Integer(5));
		deck.addLast(new Integer(6));
		deck.addLast(new Integer(7));
		deck.addLast(new Integer(8));
		for (Integer i : deck) {
			assertTrue("i is in range", i < 9);
		}
	}

	@Test
	public void testIterator1() {
		Deque<Integer> deck = new Deque();
		deck.addLast(new Integer(0));
		deck.addLast(new Integer(1));
		deck.addLast(new Integer(2));
		deck.addLast(new Integer(3));
		deck.addLast(new Integer(4));
		deck.addLast(new Integer(5));
		deck.addLast(new Integer(6));
		deck.addLast(new Integer(7));
		deck.addLast(new Integer(8));
		for (Integer i : deck) {
			for (Integer j : deck) {
				assertTrue("i is in range", (i + j) < 17);
			}
		}
	}

	@Test
	public void testIterator2() {
		fail("Not yet implemented");
	}

	@Test
	public void testMain() {
		fail("Not yet implemented");
	}

}
