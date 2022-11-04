import edu.princeton.cs.algs4.StdRandom;
import java.util.NoSuchElementException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private Node<Item> first, last;
    private int n = 0;

    private class Node<Item>
    {
        Item item;
        Node<Item> next;
        Node<Item> prev;
    }

    // construct an empty randomized queue
    // public RandomizedQueue()

    // is the randomized queue empty?
    public boolean isEmpty() {
        return first == null;
    }

    // return the number of items on the randomized queue
    public int size() {
        return n;
    }

    // add the item
    public void enqueue(Item item) {
        if (item == null) { throw new IllegalArgumentException(); }
        Node<Item> oldlast = last;
        last = new Node<Item>();
        last.item = item;
        last.next = first;
        if (isEmpty()) {
            first = last;
            first.next = first;
        } else {
            oldlast.next = last;
        }
        n++;
    }

    // remove and return a random item
    public Item dequeue() {
        if (isEmpty()) { throw new NoSuchElementException(); }
        rotate(StdRandom.uniformInt(n));
        Item item = first.item;
        if (first == last) {
            first = null;
            last = null;
        } else {
            first = first.next;
            last.next = first;
        }
        n--;
        return item;
    }

    private void rotate(int m) {
        for (int i = 0; i < m; i++) {
            last = last.next;
        }
        first = last.next;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        if (isEmpty()) { throw new NoSuchElementException(); }
        rotate(StdRandom.uniformInt(n));
        return first.item;
    }

    private class RandomizedQueueIterator implements Iterator<Item> {
        private Node<Item> current;
        private Item[] s;
        private int j;
        public RandomizedQueueIterator() {
            current = first;
            s = (Item[]) new Object[n];
            int[] permutation = StdRandom.permutation(n);
            for (int i = 0; i < n; i++) {
                s[permutation[i]] = current.item;
                current = current.next;
            }
        }
        public boolean hasNext() { return j < s.length; }
        public void remove() { throw new UnsupportedOperationException(); }
        public Item next() {
            if (!hasNext()) { throw new NoSuchElementException(); }
            Item item = s[j];
            j++;
            return item;
        }
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new RandomizedQueueIterator();
    }

    // unit testing (required)
    public static void main(String[] args) {
        RandomizedQueue<String> d = new RandomizedQueue<String>();
        d.enqueue("Foo");
        d.enqueue("Bar");
        d.enqueue("Baz");

        Iterator<String> i = d.iterator();
        while (i.hasNext()) {
            System.out.println(i.next());
        }
    }

}
