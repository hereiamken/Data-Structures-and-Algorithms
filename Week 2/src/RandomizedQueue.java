/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {

    private int size;
    private Item[] items = (Item[]) new Object[1];

    public Iterator<Item> iterator() {
        return new RandomizedQueueIterator();
    }

    private void resize(int n) {
        Item[] newArray = (Item[]) new Object[n];
        for (int i = 0; i < size; i++) {
            newArray[i] = items[i];
        }
        items = newArray;
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return size == 0;
    }

    // return the number of items on the randomized queue
    public int size() {
        return size;
    }

    // add the item
    public void enqueue(Item item) {
        validate(item);

        //resizing double item size
        if (size == items.length) {
            resize(items.length * 2);
        }
        items[size++] = item;
    }

    // remove and return a random item
    public Item dequeue() {
        validateNotEmpty();

        // resizing items
        if (size == items.length / 4) {
            resize(items.length / 2);
        }
        int random = StdRandom.uniform(size);
        Item randomItem = items[random];

        // Order in queue does not matter, so replace removed element with last element in array
        if (random < size - 1) {
            items[random] = items[size - 1];
            //preventing loitering
            items[size - 1] = null;
        }
        else {
            items[random] = null;
        }
        size--;
        return randomItem;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        validateNotEmpty();

        return items[randomIndex()];
    }

    private int randomIndex() {
        return StdRandom.uniform(size());
    }

    private void validate(Item item) {
        if (item == null) throw new IllegalArgumentException();
    }

    private void validateNotEmpty() {
        if (isEmpty()) throw new NoSuchElementException();
    }

    private class RandomizedQueueIterator implements Iterator<Item> {

        private int current = 0;
        private Item[] itemsRandom = (Item[]) new Object[size];

        public RandomizedQueueIterator() {
            for (int i = 0; i < size; i++) {
                itemsRandom[i] = items[i];
            }
            StdRandom.shuffle(itemsRandom);
        }

        public Item next() {
            if (current >= size) {
                throw new NoSuchElementException();
            }
            return itemsRandom[current++];
        }

        public boolean hasNext() {
            return current < size;
        }
    }

    public static void main(String[] args) {
        RandomizedQueue<String> queue = new RandomizedQueue<>();
        System.out.println("Adding 1-5");
        queue.enqueue("1");
        queue.enqueue("2");
        queue.enqueue("3");
        queue.enqueue("4");
        queue.enqueue("5");
        for (String s : queue) {
            System.out.print(s + " ");
        }
        System.out.printf("\nRandom item: %s\n", queue.sample());
        System.out.println("Removing 2 items");
        queue.dequeue();
        queue.dequeue();
        for (String s : queue) {
            System.out.print(s + " ");
        }
        System.out.printf("\nSize: %d", queue.size());
    }
}
