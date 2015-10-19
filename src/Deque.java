import java.util.Iterator;

public class Deque<Item> implements Iterable<Item> {

    private static final int INIT_SIZE = 4;
    private static final int BIGGER_SIZE = 2;
    private static final int SMALLER_SIZE = 4;
    Item[] items = (Item[]) new Object[INIT_SIZE];
    private int currentArraySize = INIT_SIZE;

    private int first;
    private int last;

   public Deque(){
       this.first = currentArraySize / 2;
       this.last = (currentArraySize / 2) -1;
       // construct an empty deque
   }

    private int checkResize(){
        if(first == 0 || last == (currentArraySize - 1)){
            int newSize = currentArraySize * BIGGER_SIZE;
            Item[] newItems = (Item[]) new Object[newSize];
            int j = (newSize/2) - (currentArraySize / 2);
            this.first = j;
            this.last = j + size()+1;
            for(int i = 0; i < currentArraySize; i++) {
                newItems[j++] = this.items[i];
            }
            this.items = newItems;
            this.currentArraySize = newSize;
            return 1;
        } else if (size() + 1 * SMALLER_SIZE < currentArraySize){
            int newSize = currentArraySize / BIGGER_SIZE;
            Item[] newItems = (Item[]) new Object[newSize];
            int j = (newSize/2) - (size() / 2);
            for(int i = first; i <= this.last; i++){
                newItems[j++] = items[i];
            }
            this.first = (newSize/2) - (size() / 2);
            this.last = j - 1;
            this.items = newItems;
            this.currentArraySize = newSize;
            return -1;
        }
        else { return 0;}
    }

   public boolean isEmpty(){
       // is the deque empty?
       return first > last;
   }

   public int size(){
       // return the number of items on the deque
       return last - first + 1;
   }

   public void addFirst(Item item){
       if(item == null) throw new java.lang.NullPointerException();
       // add the item to the front
       checkResize();
       this.items[first-1] = item;
       first -= 1;
   }

   public void addLast(Item item){
       if(item == null) throw new java.lang.NullPointerException();
       // add the item to the end
       checkResize();
       this.items[last+1] = item;
       last += 1;
   }

   public Item removeFirst(){
       // remove and return the item from the front
       if(this.isEmpty()) throw new java.util.NoSuchElementException();
       Item returnItem = items[first];
       items[first++] = null;
       checkResize();
       return returnItem;
   }

   public Item removeLast(){
       // remove and return the item from the end
       if(this.isEmpty()) throw new java.util.NoSuchElementException();
       Item returnItem = items[last];
       items[last--] = null;
       checkResize();
       return returnItem;
   }


    protected String printItems(){
        String output = "";
        for (int i = 0; i < currentArraySize; i ++){
            output += items[i] + "\n";

        }
        return output;
    }

    private Item getItem(int i){
        if(i < first || i > last) return null;
        return items[i];
    }

   public Iterator<Item> iterator(){
       // return an iterator over items in order from front to end
       return new DequeIterator2(first, last);
   }

    private class DequeIterator2 implements Iterator<Item> {
        private int first;
        private int last;
        private int idx;
        
        public DequeIterator2(int frst, int lst){
            this.first = frst;
            this.last = lst;
            this.idx = frst;
        }
        
        public boolean hasNext() {
            return idx <= last;
        }

        public Item next() {
            return getItem(idx++);
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
        
        public boolean hasNext(){
            return idx <=  last;
        }
        
        public Item next() {
            return items[idx++];
        }
        
    }

   public static void main(String[] args){   // unit testing
       Deque<Integer> testDeck = new Deque<Integer>();
       System.out.println("Array: " + testDeck.printItems());
       System.out.println("first:" + testDeck.first);
       System.out.println("last:" + testDeck.last);
       testDeck.addFirst(new Integer(10));
       System.out.println("Array: " + testDeck.printItems());
       System.out.println("first:" + testDeck.first);
       System.out.println("last:" + testDeck.last);
       testDeck.addFirst(new Integer(4));
       System.out.println("Array: " + testDeck.printItems());
       System.out.println("first:" + testDeck.first);
       System.out.println("last:" + testDeck.last);
       testDeck.addLast(new Integer(-3));
       System.out.println("Array: " + testDeck.printItems());
       System.out.println("Testing the iterator:");
       for(Integer nt : testDeck){
           System.out.println(nt);
       }
       
       System.out.println("first:" + testDeck.first);
       System.out.println("last:" + testDeck.last);
       System.out.println("Array: " + testDeck.printItems());
       System.out.println(testDeck.removeLast() + " is -3");
       System.out.println("first:" + testDeck.first);
       System.out.println("last:" + testDeck.last);
       System.out.println("Array: " + testDeck.printItems());
       System.out.println(testDeck.removeLast() + " is 10");
       System.out.println("first:" + testDeck.first);
       System.out.println("last:" + testDeck.last);
       System.out.println("Array: " + testDeck.printItems());
       System.out.println(testDeck.removeLast() + " is 4");
       System.out.println("first:" + testDeck.first);
       System.out.println("last:" + testDeck.last);
       System.out.println("Array: " + testDeck.printItems());

       System.out.println("IsEmpty = " + testDeck.isEmpty());

       for(Integer nt : testDeck){
           System.out.println(nt);
       }

   }
}
