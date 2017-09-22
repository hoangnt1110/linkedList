import java.util.Iterator;
import java.lang.Integer;
import java.util.NoSuchElementException;

public class LinkedList implements Iterable {
    private Node first;
    private Node last;
    private int N;

    public LinkedList() {
        first = null;
        last = null;
        N = 0;
    }

    public void add(Integer item) {
        if (item == null) { throw new NullPointerException("The first argument for addLast() is null."); }
        if (!isEmpty()) {
            Node prev = last;
            last = new Node(item, null);
            prev.next = last;
        }
        else {
            last = new Node(item, null);
            first = last;
        }
        N++;
    }

    public void removeLast() {
        remove(last.data);
    }

    public boolean remove(Integer item) {
        if (isEmpty()) { throw new IllegalStateException("Cannot remove() from and empty list."); }
        boolean result = false;
        Node prev = first;
        Node curr = first;
        while (curr.next != null || curr == last) {
            if (curr.data.equals(item)) {
                // remove the last remaining element
                if (N == 1) { first = null; last = null; }
                // remove first element
                else if (curr.equals(first)) { first = first.next; }
                // remove last element
                else if (curr.equals(last)) { last = prev; last.next = null; }
                // remove element
                else { prev.next = curr.next; }
                N--;
                result = true;
                break;
            }
            prev = curr;
            curr = prev.next;
        }
        return result;
    }

    public boolean removeAllGreaterThan(Integer item) {
        if (isEmpty()) { throw new IllegalStateException("Cannot remove() from and empty list."); }
        boolean result = false;
        Node prev = first;
        Node curr = first;
        while (curr != null && (curr.next != null || curr == last)) {
            if (curr.data > item) {
                // remove the last remaining element
                if (N == 1) { first = null; last = null; }
                // remove first element
                else if (curr.equals(first)) { first = first.next; }
                // remove last element
                else if (curr.equals(last)) { last = prev; last.next = null; }
                // remove element
                else { prev.next = curr.next; }
                N--;
                result = true;
                break;
            }
            prev = curr;
            curr = prev.next;
        }
        return result;
    }

    public boolean isEmpty() {
        return N == 0;
    }

    private class Node {
        private Integer data;
        private Node next;

        public Node(Integer data, Node next) {
            this.data = data;
            this.next = next;
        }
    }

    public Iterator<Integer> iterator() { return new LinkedListIterator(); }

    private class LinkedListIterator implements Iterator<Integer> {
        private Node current = first;

        public Integer next() {
            if (!hasNext()) { throw new NoSuchElementException(); }
            Integer item = current.data;
            current = current.next;
            return item;
        }

        public boolean hasNext() { return current != null; }

        public void remove() { throw new UnsupportedOperationException(); }
    }

    @Override public String toString() {
        StringBuilder s = new StringBuilder();
        for (Object item : this)
            s.append(item + " ");
        return s.toString();
    }

    public static void main(String[] args) {
        LinkedList list = new LinkedList();
        list.add(1);
        list.add(2);
        list.add(3);
        list.removeLast();
        System.out.println("remove last:" + list);
        list.removeAllGreaterThan(1);
        System.out.println("remove all:" + list);
    }

}