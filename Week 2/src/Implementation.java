/* *****************************************************************************
 *  Name:              Alan Turing
 *  Coursera User ID:  123456
 *  Last modified:     1/1/2019
 **************************************************************************** */

import java.util.Iterator;
import java.util.ListIterator;
import java.util.Spliterator;
import java.util.function.Consumer;

public class Implementation {
    public class LinkedStackOfStrings {
        private Node first = null;

        private class Node {
            String item;
            Node next;
        }

        public boolean isEmpty() {
            return first == null;
        }

        public void push(String item) {
            Node oldFirst = first;
            first = new Node();
            first.item = item;
            first.next = oldFirst;
        }

        public String pop() {
            String item = first.item;
            first = first.next;
            return item;
        }
    }

    public class Stack<Item> implements Iterable<Item> {
        private Node first = null;

        @Override
        public Iterator<Item> iterator() {
            return new ListIterator<Item>() {

                @Override
                public boolean hasNext() {
                    return false;
                }

                @Override
                public Item next() {
                    return null;
                }

                @Override
                public boolean hasPrevious() {
                    return false;
                }

                @Override
                public Item previous() {
                    return null;
                }

                @Override
                public int nextIndex() {
                    return 0;
                }

                @Override
                public int previousIndex() {
                    return 0;
                }

                @Override
                public void remove() {

                }

                @Override
                public void set(Item item) {

                }

                @Override
                public void add(Item item) {

                }
            };
        }

        @Override
        public void forEach(Consumer<? super Item> action) {
            Iterable.super.forEach(action);
        }

        @Override
        public Spliterator<Item> spliterator() {
            return Iterable.super.spliterator();
        }

        private class Node {
            Item item;
            Node next;
        }

        public boolean isEmpty() {
            return first == null;
        }

        public void push(Item item) {
            Node oldFirst = first;
            first = new Node();
            first.item = item;
            first.next = oldFirst;
        }

        public Item pop() {
            Item item = first.item;
            first = first.next;
            return item;
        }
    }
}
