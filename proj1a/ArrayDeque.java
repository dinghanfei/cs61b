public class ArrayDeque<T> {
    private T[] items;
    private int size;
    private int nextFirst;
    private int nextLast;
    private int length;

    /** Creates an empty array deque. */
    public ArrayDeque() {
        items = (T[]) new Object[8];
        size = 0;
        nextFirst = 4;
        nextLast = 5;
        length = 8;
    }
    /** Creates a deep copy of 'other'. */
    public ArrayDeque(ArrayDeque other) {
        items = (T[]) new Object[8];
        size = 0;
        length = 8;
        nextFirst = length - 1;
        nextLast = 0;
        for (int i = 0; i < other.size; i++) {
            addLast((T) other.get(i));
        }
    }
    /**
     * Decreases/Increases the specified index by one, wrapping around if necessary.
     *
     * @param index the current index
     * @param length the length of the array
     * @return the updated index
     */
    private int minusOne(int index, int length) {
        index = index % length;
        if (index == 0) {
            return length - 1;
        }
        return index - 1;
    }
    private int plusOne(int index, int length) {
        index = index % length;
        if (index == length - 1) {
            return 0;
        }
        return index + 1;
    }
    /**
     * Resizes the underlying array to the specified capacity.
     *
     * @param capacity the new capacity of the array
     */
    private void resize(int capacity) {
        T[] newItems = (T[]) new Object[capacity];
        int p = plusOne(nextFirst, length);
        for (int i = 0; i < size; i++) {
            newItems[i] = items[p];
            p = plusOne(p, length);
        }
        items = newItems;
        length = capacity;
        nextFirst = capacity - 1;
        nextLast = size;
    }
    private void grow() {
        resize(length * 2);
    }
    private void shrink() {
        resize(length / 2);
    }
    /** Checks if resizing is needed based on the current size. */
    private void check(int size, int length) {
        if (size > 0.75 * length) {
            grow();
        }
        if (size < 0.25 * length && length > 16) {
            shrink();
        }
    }
    /** Adds an item of type T to the front of the deque. */
    public void addFirst(T item) {
        check(size, length);
        size += 1;
        items[nextFirst] = item;
        nextFirst = minusOne(nextFirst, length);
    }
    /** Adds an item of type T to the back of the deque. */
    public void addLast(T item) {
        check(size, length);
        size += 1;
        items[nextLast] = item;
        nextLast = plusOne(nextLast, length);
    }
    /** Returns true if deque is empty, false otherwise. */
    public boolean isEmpty() {
        return size == 0;
    }
    /** Returns the number of items in the deque. */
    public int size() {
        return size;
    }

    /** Prints the items in the deque from first to last, separated by a space.
     * Once all the items have been printed, print out a new line.
     */
    public void printDeque() {
        int p = plusOne(nextFirst, length);
        for (int i = 0; i < size; i++) {
            System.out.print(items[p] + " ");
            p = plusOne(p, length);
        }
        System.out.println();
    }

    /**  Removes and returns the item at the front of the deque.
     *  If no such item exists, returns null.
     */
    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        nextFirst = plusOne(nextFirst, length);
        T item = items[nextFirst];
        items[nextFirst] = null;
        size--;
        check(size, length);
        return item;
    }
    /** Removes and returns the item at the back of the deque.
     * If no such item exists, returns null.
     */
    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        nextLast = minusOne(nextLast, length);
        T item = items[nextLast];
        items[nextLast] = null;
        size--;
        check(size, length);
        return item;
    }
    /** Gets the item at the given index,
     * where 0 is the front, 1 is the next item, and so forth.
     * If no such item exists, returns null.
     * Must not alter the deque!
     */
    public T get(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        index = (index + nextFirst + 1) % length;
        return items[index];
    }

}
