package synthesizer;
import java.util.Iterator;

/**
 * This class and all of its methods public.
 * This class extend AbstractBoundedQueue<T>.
 */
public class ArrayRingBuffer<T> extends AbstractBoundedQueue<T>  {
    /* Index for the next dequeue or peek. */
    private int first;
    /* Index for the next enqueue. */
    private int last;
    /* Array for storing the buffer data. */
    private T[] rb;

    /**
     * Create a new ArrayRingBuffer with the given capacity.
     * first, last, and fillCount should all be set to 0.
     * this.capacity should be set appropriately. Note that the local variable
     * here shadows the field we inherit from AbstractBoundedQueue,
     * so you'll need to use this.capacity to set the capacity.
     */
    public ArrayRingBuffer(int capacity) {
        first = 0;
        last = 0;
        fillCount = 0;
        this.capacity = capacity;

        rb = (T[]) new Object[capacity];
    }

    /**
     * Adds x to the end of the ring buffer. If there is no room, then
     * throw new RuntimeException("Ring buffer overflow").Exceptions
     * covered Monday.
     * Enqueue the item.
     * Don't forget to increase fillCount and update last.
     */
    @Override
    public void enqueue(T x) {
        if (isFull()) {
            throw new RuntimeException("Ring buffer overflow");
        }
        rb[last] = x;
        last = (last + 1) % capacity;
        fillCount += 1;
    }

    /**
     * Dequeue oldest item in the ring buffer. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow"). Exceptions
     * covered Monday.
     */
    @Override
    public T dequeue() {
        if (isEmpty()) {
            throw new RuntimeException("Ring buffer underflow");
        }
        T x = rb[first];
        rb[first] = null;
        first = (first + 1) % capacity;
        fillCount -= 1;
        return x;
    }

    /**
     * Return oldest item, but don't remove it.
     * None of your instance variables should change.
     */
    @Override
    public T peek() {
        if (isEmpty()) {
            throw new RuntimeException("Ring buffer underflow");
        }
        return rb[first];
    }
    @Override
    public Iterator<T> iterator() {
        return new KeyIterator();
    }
    public class KeyIterator implements Iterator<T> {
        private int ptr;
        public KeyIterator() {
            ptr = first;
        }
        public boolean hasNext() {
            return ptr != last;
        }
        public T next() {
            T returnItem = rb[ptr];
            ptr = (ptr + 1) % capacity;
            return returnItem;
        }
    }

}

