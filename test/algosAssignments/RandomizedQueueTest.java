package algosAssignments;
import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Iterator;

import junit.framework.Assert;

import org.junit.Test;

import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueueTest {

	@Test
	public void testRandomizedQueue() {
		try {
			RandomizedQueue rq = new RandomizedQueue<Object>();
		} catch (Exception e) {
			fail("Caught unknown exception: " + e.getLocalizedMessage());
		}
	}

	@Test
	public void testIsEmptyTrue() {
		try {
			RandomizedQueue<Object> rq = new RandomizedQueue<Object>();
			assertTrue("RQ is empty", rq.isEmpty());
		} catch (Exception e) {
			fail("Caught unknown exception: " + e.getLocalizedMessage());
		}
	}

	@Test
	public void testIsEmptyAddItemFalse() {
		try {
			RandomizedQueue<String> rq = new RandomizedQueue<String>();
			String item = "string";
			rq.enqueue(item);
			assertFalse("RQ is not empty", rq.isEmpty());
		} catch (Exception e) {
			fail("Caught unknown exception: " + e.getLocalizedMessage());
		}
	}

	@Test
	public void testIsEmptyAddItemAndRemoveTrue() {
		try {
			RandomizedQueue<String> rq = new RandomizedQueue<String>();
			String item = "string";
			rq.enqueue(item);
			System.out.println(rq.dequeue());
			assertTrue("RQ is empty", rq.isEmpty());
		} catch (Exception e) {
			fail("Caught unknown exception: " + e.getLocalizedMessage());
		}
	}

	@Test
	public void testIsEmptyAdd2ItemsAndRemove1True() {
		try {
			RandomizedQueue<String> rq = new RandomizedQueue<String>();
			String item = "string";
			rq.enqueue(item);
			rq.enqueue("another");
			System.out.println(rq.dequeue());
			assertFalse("RQ is not empty", rq.isEmpty());
		} catch (Exception e) {
			fail("Caught unknown exception: " + e.getLocalizedMessage());
		}
	}

	@Test
	public void testSizeEmpty() {
		try {
			RandomizedQueue<Object> rq = new RandomizedQueue<Object>();
			assertSame("RQ is size 0", 0, rq.size());
		} catch (Exception e) {
			fail("Caught unknown exception: " + e.getLocalizedMessage());
		}
	}

	@Test
	public void testSizeOne() {
		try {
			RandomizedQueue<String> rq = new RandomizedQueue<String>();
			String item = "string";
			rq.enqueue(item);
			assertSame("RQ is size 1", 1, rq.size());
		} catch (Exception e) {
			fail("Caught unknown exception: " + e.getLocalizedMessage());
		}
	}

	@Test
	public void testSizeAddAndRemoveNotEmpty() {
		try {
			RandomizedQueue<String> rq = new RandomizedQueue<String>();
			String item = "string";
			rq.enqueue(item);
			rq.enqueue("another");
			System.out.println(rq.dequeue());
			assertSame("RQ is size 1", 1, rq.size());
		} catch (Exception e) {
			fail("Caught unknown exception: " + e.getLocalizedMessage());
		}
	}

	@Test
	public void testSizeAddAndRemoveEmpty() {
		try {
			RandomizedQueue<String> rq = new RandomizedQueue<String>();
			String item = "string";
			rq.enqueue(item);
			System.out.println(rq.dequeue());
			assertSame("RQ is size 0", 0, rq.size());
		} catch (Exception e) {
			fail("Caught unknown exception: " + e.getLocalizedMessage());
		}
	}

	@Test
	public void testEnqueue() {
		try {
			RandomizedQueue<String> rq = new RandomizedQueue<String>();
			String item = "string";
			rq.enqueue(item);
			assertSame("RQ is size 1", 1, rq.size());
		} catch (Exception e) {
			fail("Caught unknown exception: " + e.getLocalizedMessage());
		}
	}

	@Test
	public void testEnqueueNull() {
		try {
			RandomizedQueue<String> rq = new RandomizedQueue<String>();
			rq.enqueue(null);
			fail("Failed to throw correct exception");
		} catch (java.lang.NullPointerException e) {
			assertTrue("Caught null pointer exception", true);
		} catch (Exception e) {
			fail("Caught unknown exception: " + e.getLocalizedMessage());
		}
	}

	@Test
	public void testDequeue() {
		try {
			RandomizedQueue<String> rq = new RandomizedQueue<String>();
			rq.enqueue("string1");
			rq.enqueue("string2");
			rq.enqueue("string3");
			rq.enqueue("string4");
			assertTrue("RQ is in List",
					Arrays.asList("string1", "string2", "string3", "string4")
							.contains(rq.dequeue()));
			assertTrue("RQ is in List",
					Arrays.asList("string1", "string2", "string3", "string4")
							.contains(rq.dequeue()));
			assertTrue("RQ is in List",
					Arrays.asList("string1", "string2", "string3", "string4")
							.contains(rq.dequeue()));
			assertTrue("RQ is in List",
					Arrays.asList("string1", "string2", "string3", "string4")
							.contains(rq.dequeue()));
		} catch (Exception e) {
			fail("Caught unknown exception: " + e.getLocalizedMessage());
		}
	}

	@Test
	public void testDequeueException() {
		try {
			RandomizedQueue<String> rq = new RandomizedQueue<String>();
			rq.dequeue();
			fail("Failed to throw correct exception");
		} catch (java.util.NoSuchElementException e) {
			assertTrue("Caught correct exception.", true);
		} catch (Exception e) {
			fail("Caught unknown exception: " + e.getLocalizedMessage());
		}
	}

	@Test
	public void testSample() {
		try {
			RandomizedQueue<String> rq = new RandomizedQueue<String>();
			rq.enqueue("string1");
			rq.enqueue("string2");
			rq.enqueue("string3");
			rq.enqueue("string4");
			assertTrue("RQ is in List",
					Arrays.asList("string1", "string2", "string3", "string4")
							.contains(rq.sample()));
			assertTrue("RQ is in List",
					Arrays.asList("string1", "string2", "string3", "string4")
							.contains(rq.sample()));
			assertTrue("RQ is in List",
					Arrays.asList("string1", "string2", "string3", "string4")
							.contains(rq.sample()));
			assertTrue("RQ is in List",
					Arrays.asList("string1", "string2", "string3", "string4")
							.contains(rq.sample()));
			assertTrue("RQ is in List",
					Arrays.asList("string1", "string2", "string3", "string4")
							.contains(rq.sample()));
			assertTrue("RQ is in List",
					Arrays.asList("string1", "string2", "string3", "string4")
							.contains(rq.sample()));
			assertTrue("RQ is in List",
					Arrays.asList("string1", "string2", "string3", "string4")
							.contains(rq.sample()));
			assertTrue("RQ is in List",
					Arrays.asList("string1", "string2", "string3", "string4")
							.contains(rq.sample()));
		} catch (Exception e) {
			fail("Caught unknown exception: " + e.getLocalizedMessage());
		}
	}

	@Test
	public void TestSampleValues() {
		RandomizedQueue<String> rq = new RandomizedQueue<String>();
		rq.enqueue("LOHQRKPXVK");
		rq.dequeue();
		rq.enqueue("PDPHFBLUHD");
		rq.dequeue();
		rq.enqueue("DTQVXILQAL");
		assertEquals("Equals Value", "DTQVXILQAL", rq.sample());
	}

	@Test
	public void testSampleException() {
		try {
			RandomizedQueue<String> rq = new RandomizedQueue<String>();
			rq.sample();
			fail("Failed to throw correct exception");
		} catch (java.util.NoSuchElementException e) {
			assertTrue("Caught correct exception.", true);
		} catch (Exception e) {
			fail("Caught unknown exception: " + e.getLocalizedMessage());
		}
	}

	@Test
	public void testSampleRandom() {
		RandomizedQueue<Integer> rq = new RandomizedQueue<Integer>();
		rq.enqueue(0);
		rq.enqueue(1);
		rq.enqueue(2);
		rq.enqueue(3);
		rq.enqueue(4);
		rq.enqueue(5);
		rq.enqueue(6);
		rq.enqueue(7);
		rq.enqueue(8);
		rq.enqueue(9);
		// rq.enqueue(10);
		// rq.enqueue(11);
		// rq.enqueue(12);
		// rq.enqueue(13);
		// rq.enqueue(14);
		int[] calls = new int[15];
		Arrays.fill(calls, 0);
		for (int i = 0; i < 10000; i++) {
			int smp;
			try {
				if (rq != null) {
					smp = rq.sample();
					calls[smp]++;
				} else {
					System.out.println(i);
				}
			} catch (NullPointerException e) {
				System.out.println(i);
			}

		}
		System.out.println(Arrays.toString(calls));
	}

	@Test
	public void testSamplePerformance() {
		RandomizedQueue<Integer> rq = new RandomizedQueue<Integer>();
		int[] calls = new int[5];
		Arrays.fill(calls, 0);
		for (int i = 0; i < 2048000; i++) {
			int val = StdRandom.uniform(5);
			switch (val) {
			case 0:
				rq.isEmpty();

				break;
			case 1:
				rq.enqueue(i);
				break;
			case 2:
				try {
					rq.sample();
				} catch (Exception e) {

				}
				break;
			case 3:
				try {
					rq.dequeue();
				} catch (Exception e) {

				}
				break;
			case 4:
				rq.size();
				break;
			}
			calls[val]++;
//			if (i % 100000 == 0) {
//				System.out.println(i);
//				System.out.println(rq.size());
//				System.out.println(Arrays.toString(calls));
//			}
		}
		System.out.println(rq.size());
		System.out.println(Arrays.toString(calls));
	}

	@Test
	public void testIterator() {
		try {
			RandomizedQueue<String> rq = new RandomizedQueue<String>();
			rq.enqueue("string1");
			rq.enqueue("string2");
			rq.enqueue("string3");
			rq.enqueue("string4");
			Iterator<String> iter = rq.iterator();
			int count = 0;
			while (iter.hasNext()) {
				count++;
				assertTrue(
						"RQ is in List",
						Arrays.asList("string1", "string2", "string3",
								"string4").contains(iter.next()));
			}
			System.out.println(count);
		} catch (Exception e) {
			fail("Caught unknown exception: " + e.getLocalizedMessage());
		}
	}

	@Test
	public void testIteratorException() {
		try {
			RandomizedQueue<String> rq = new RandomizedQueue<String>();
			rq.enqueue("string1");
			rq.enqueue("string2");
			rq.enqueue("string3");
			rq.enqueue("string4");
			Iterator<String> iter = rq.iterator();
			int count = 0;
			while (iter.hasNext()) {
				assertTrue(
						"RQ is in List",
						Arrays.asList("string1", "string2", "string3",
								"string4").contains(iter.next()));
			}
			iter.next();
			fail("Failed to throw correct exception");
		} catch (java.util.NoSuchElementException e) {
			assertTrue("Caught valid exception: " + e.getLocalizedMessage(),
					true);
		} catch (Exception e) {
			fail("Caught unknown exception: " + e.getLocalizedMessage());
		}
		try {
			RandomizedQueue<String> rq = new RandomizedQueue<String>();
			rq.enqueue("string1");
			Iterator<String> iter = rq.iterator();
			iter.remove();
		} catch (java.lang.UnsupportedOperationException e) {
			assertTrue("Caught valid exception: " + e.getLocalizedMessage(),
					true);
		} catch (Exception e) {
			fail("Caught unknown exception: " + e.getLocalizedMessage());
		}
	}

	@Test
	public void testIteratorEmpty() {
		try {
			RandomizedQueue<String> rq = new RandomizedQueue<String>();
			Iterator<String> iter = rq.iterator();
			assertFalse("Iterator does not have any elements.", iter.hasNext());
		} catch (Exception e) {
			fail("Caught unknown exception: " + e.getLocalizedMessage());
		}
	}
}
