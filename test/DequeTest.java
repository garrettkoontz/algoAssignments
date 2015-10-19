
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Assert.*;

public class DequeTest{

    @Test
    public void constructor() {
        try {
            Deque deck = new Deque();
        } catch (Exception e){
            fail("Caught an exception: %s", e.getLocalizedMessage());
        }
    }    

    @Test
    public void isEmptyNewDeck(){
        Deque<Integer> deck = new Deque();
        assertTrue("New deque is empty: ",deck.isEmpty());
    }

    @Test
    public void isEmptyAddedDeck() {
        Deque<Integer> deck = new Deque();
        deck.addFirst(new Integer(10));
        assertFalse("Deque with 10 is not empty",deck.isEmpty());
    }

    @Test
    public void isEmptyAddedFirstAndRemovedDeck(){
        Deque<Integer> deck = new Deque();
        deck.addFirst(new Integer(10));
        deck.removeFirst();
        assertTrue("Deque add first and delete deck is empty: ",deck.isEmpty());
    }

    @Test
    public void isEmptyAddedLastAndRemovedDeck(){
        Deque<Integer> deck = new Deque();
        deck.addLast(new Integer(10));
        deck.removeLast();
        assertTrue("Deque add last and delete deck is empty: ",deck.isEmpty());
    }

    @Test
    public void isEmptyAddedFirstAndRemovedLastDeck(){
        Deque<Integer> deck = new Deque();
        deck.addFirst(new Integer(10));
        deck.removeLast();
        assertTrue("Deque add first and delete last deck is empty: ",deck.isEmpty());
    }

    @Test
    public void isEmptyAddedLastAndRemovedFirstDeck(){
        Deque<Integer> deck = new Deque();
        deck.addLast(new Integer(10));
        deck.removeFirst();
        assertTrue("Deque add last and delete first deck is empty: ",deck.isEmpty());
    }

    @Test
    public void isEmptyAddedLastAndRemovedDeck(){
        Deque<Integer> deck = new Deque();
        deck.addLast(new Integer(10));
        deck.addLast(new Integer(4));
        deck.removeLast();
        assertFalse("Deque add, add and delete deck is not empty: ",deck.isEmpty());
    }

    @Test
    public void addFirstNull() {
        Deque<Integer> deck = new Deque();
        try {
            deck.addFirst(null);
            fail("Did not throw exception");
        } catch (NullPointerException npe) {
            assertTrue("Caught the exception",true);
        } catch (Exception e){
            fail("Caught wrong exception: %s",e.getLocalizedMessage());
        }
    }

}
