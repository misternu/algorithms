// import edu.princeton.cs.algs4.StdIn;
// import edu.princeton.cs.algs4.StdOut;
import java.util.NoSuchElementException;

import java.util.Iterator;

public class Deque<Item> implements Iterable<Item> {
    private Node<Item> first, last;
    private int n = 0;

    private class Node<Item>
    {
        Item item;
        Node<Item> next;
        Node<Item> prev;
    }

    // construct an empty deque
    // public Deque()

    // is the deque empty?
    public boolean isEmpty() {
        return first == null; 
    }

    // return the number of items on the deque
    public int size() {
        return n;
    }

    // add the item to the front
    public void addFirst(Item item) {
        if (item == null) { throw new IllegalArgumentException(); }
        Node<Item> newFirst = new Node<Item>();
        newFirst.item = item;
        newFirst.next = first;
        if (first != null) {
            first.prev = newFirst;
        }
        first = newFirst;
        if (last == null) {
            last = first;
        }
        n++;
    }

    // add the item to the back
    public void addLast(Item item) {
        if (item == null) { throw new IllegalArgumentException(); }
        Node<Item> oldLast = last;
        last = new Node<Item>();
        last.item = item;
        last.next = null;
        last.prev = oldLast;
        if (isEmpty()) {
            first = last;
        } else {
            oldLast.next = last; 
        }
        n++;
    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (isEmpty()) { throw new NoSuchElementException(); }
        Item item = first.item;
        if (first == last) {
            first = null;
            last = null;
        } else {
            Node<Item> newFirst = first.next;
            newFirst.prev = null;
            first.next = null;
            first = newFirst;
        }
        n--;
        return item;
    }

    // remove and return the item from the back
    public Item removeLast() {
        if (isEmpty()) { throw new NoSuchElementException(); }
        Item item = last.item;
        if (first == last) {
            first = null;
            last = null;
        } else {
            Node<Item> newLast = last.prev;
            newLast.next = null;
            last.prev = null;
            last = newLast;
        }
        n--;
        return item;
    }

    private class DequeIterator implements Iterator<Item> {
        private Node<Item> current = first;
        public boolean hasNext() { return current != null; }
        public void remove() { throw new UnsupportedOperationException(); }
        public Item next() {
            if (!hasNext()) { throw new NoSuchElementException(); }
            Item item = current.item;
            current = current.next;
            return item;
        }
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
        return new DequeIterator();
    }

    // unit testing (required)
    public static void main(String[] args) {
        // Deque<String> d = new Deque<String>();
        // d.addFirst("Foo");
        // d.addLast("Bar");
        // d.addLast("Baz");

        // Iterator<String> i = d.iterator();
        // while (i.hasNext()) {
        //     System.out.println(i.next());
        // }

        // String first = d.removeFirst();
        // String last = d.removeLast();
        // System.out.println(first);
        // System.out.println(last);

        Deque<Integer> deque = new Deque<>();
        deque.isEmpty();
        deque.isEmpty();
        deque.addFirst(3);
        deque.removeLast();
    }

}
