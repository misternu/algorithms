import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;

public class Deque<Item> implements Iterable<Item> {
    private Node<Item> first, last;
    private int N = 0;

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
        return N;
    }

    // add the item to the front
    public void addFirst(Item item) {
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
        N++;
    }

    // add the item to the back
    public void addLast(Item item) {
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
        N++;
    }

    // remove and return the item from the front
    public Item removeFirst() {
        Item item = first.item;
        Node<Item> newFirst = first.next;
        newFirst.prev = null;
        first.next = null;
        first = newFirst;
        return item;
    }

    // remove and return the item from the back
    public Item removeLast() {
        Item item = last.item;
        Node<Item> newLast = last.prev;
        newLast.next = null;
        last.prev = null;
        last = newLast;
        return item;
    }

    private class DequeIterator implements Iterator<Item> {
        private Node<Item> current = first;
        public boolean hasNext() { return current != null; }
        public void remove() { /* not supported */ }
        public Item next() {
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
        Deque<String> d = new Deque<String>();
        d.addFirst("Foo");
        d.addLast("Bar");
        d.addLast("Baz");

        Iterator<String> i = d.iterator();
        while (i.hasNext()) {
            System.out.println(i.next());
        }

        d = new Deque<String>();
        d.addLast("Foo");
        d.addFirst("Bar");
        d.addFirst("Baz");

        i = d.iterator();
        while (i.hasNext()) {
            System.out.println(i.next());
        }
    }

}
