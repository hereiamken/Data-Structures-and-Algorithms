import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

    private Node first = null;
    private Node last = null;
    private int size;

    private class Node {
        Item item;
        Node next;
        Node previous;
    }

    // construct an empty deque
    public Deque() {
        this.first = null;
        this.last = null;
        this.size = 0;
    }

    // is the deque empty?
    public boolean isEmpty() {
        return first == null;
    }

    // return the number of items on the deque
    public int size() {
        return size;
    }

    // add the item to the front
    public void addFirst(Item item) {
        validate(item);

        Node add = new Node();
        add.next = first;
        add.item = item;

        if (first == null && last == null) {
            last = add;
        }
        else {
            first.previous = add;
        }

        first = add;
        size++;
    }

    // add the item to the back
    public void addLast(Item item) {
        validate(item);

        Node add = new Node();
        add.previous = last;
        add.item = item;

        if (first == null && last == null) {
            first = add;
        }
        else {
            last.next = add;
        }

        last = add;
        size++;
    }

    // remove and return the item from the front
    public Item removeFirst() {
        validateNotEmpty();

        Item item = first.item;

        if (first.next == null) {
            last = null;
        }
        else {
            first.next.previous = null;
        }

        first = first.next;
        size--;
        return item;
    }

    // remove and return the item from the back
    public Item removeLast() {
        validateNotEmpty();

        Item item = last.item;

        if (last.previous == null) {
            first = null;
        }
        else {
            last.previous.next = null;
        }

        last = last.previous;
        size--;
        return item;
    }


    public Iterator<Item> iterator() {
        return new DequeIterator();
    }

    private class DequeIterator implements Iterator<Item> {
        private Node current = first;

        public boolean hasNext() {
            return current != null;
        }

        public Item next() {
            if (current == null) {
                throw new NoSuchElementException();
            }

            Item next = current.item;
            current = current.next;
            return next;
        }
    }

    private void validate(Item item) {
        if (item == null) throw new IllegalArgumentException("The item can't be null");
    }

    private void validateNotEmpty() {
        if (isEmpty()) throw new NoSuchElementException("The deque is empty");
    }

    // unit testing (required)
    public static void main(String[] args) {
        Deque<String> deque = new Deque<>();
        deque.addFirst("to chew bubblegum");
        deque.addLast("and");
        deque.addFirst("I'm here");
        deque.addLast("kick ass");
        for (String s : deque) {
            System.out.print(s + " ");
        }
        deque.removeFirst();
        deque.removeLast();
        deque.addLast("I'm all out of bubblegum");
        deque.removeFirst();
        System.out.println();
        for (String s : deque) {
            System.out.print(s + " ");
        }
    }
}
