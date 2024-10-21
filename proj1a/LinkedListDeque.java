public class LinkedListDeque<T> {
    private class Node {
        private T item;
        private Node prev;
        private Node next;

        public Node(Node prev, T item, Node next) {
            this.prev = prev;
            this.item = item;
            this.next = next;
        }
    }

    private Node sentinel;
    private int size;

    /** Constructor for deque. */
    public LinkedListDeque() {
        sentinel = new Node(null, null, null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
        size = 0;
    }
//    /** Creates a deep copy of LinkedListDeque other. */
//    public LinkedListDeque(LinkedListDeque other) {
//        sentinel = new Node(null, null, null);
//        sentinel.prev = sentinel;
//        sentinel.next = sentinel;
//        size = 0;
//
//        for (int i = 0; i < other.size; i++) {
//            addLast((T) other.get(i));
//        }
//    }
    public void addFirst(T item) {
        size += 1;
        Node newNode = new Node(sentinel, item, sentinel.next);
        sentinel.next.prev = newNode;
        sentinel.next = newNode;
    }
    public void addLast(T item) {
        size += 1;
        Node newNode = new Node(sentinel.prev, item, sentinel);
        sentinel.prev.next = newNode;
        sentinel.prev = newNode;
    }
    /**
     * Checks if the deque is empty.
     *
     * @return true if the deque has no elements, false otherwise.
     */
    public boolean isEmpty() {
        return size == 0;
    }
    /**
     * Returns the number of items currently in the deque.
     *
     * @return the size of the deque.
     */
    public int size() {
        return size;
    }
    public void printDeque() {
        Node p = sentinel.next;
        while (p != sentinel) {
            System.out.print(p.item + " ");
            p = p.next;
        }
        System.out.println();
    }
    /**
     * Removes and returns the first item from the deque.
     * If the deque is empty, returns null.
     *
     * @return the item removed from the front of the deque, or null if the deque is empty.
     */
    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        size--;
        T item = sentinel.next.item;
        sentinel.next = sentinel.next.next;
        sentinel.next.prev = sentinel;
        return item;
    }
    /**
     * Removes and returns the last item from the deque.
     * If the deque is empty, returns null.
     *
     * @return the item removed from the end of the deque, or null if the deque is empty.
     */
    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        size--;
        T item = sentinel.prev.item;
        sentinel.prev = sentinel.prev.prev;
        sentinel.prev.next = sentinel;
        return item;
    }
    /**
     * Gets the item at the specified index in the deque.
     * Index 0 refers to the front of the deque, index 1 refers to the next item, and so on.
     * If the index is out of bounds, returns null.
     * This method does not alter the deque.
     *
     * @param index the index of the item to retrieve
     * @return the item at the specified index, or null if the index is out of bounds.
     */
    public T get(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        Node p = sentinel.next;
        for (int i = 0; i < index; i++) {
            p = p.next;
        }
        return p.item;
    }
    /**
     * Same as get, but uses recursion.
     * @param index the index of the item to retrieve
     * @return the item at the specified index, or null if the index is out of bounds.
     */
    private T getRecursive(Node p, int index) {
        if (index == 0) {
            return p.item;
        } else {
            return getRecursive(p.next, index - 1);
        }
    }
    public T getRecursive(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        Node p = sentinel.next;
        return getRecursive(p, index);
    }
}
