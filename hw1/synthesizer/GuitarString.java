package synthesizer;
import java.util.HashSet;

public class GuitarString {
    /** Constants. Do not change. In case you're curious, the keyword final means
     * the values cannot be changed at runtime. We'll discuss this and other topics
     * in lecture on Friday. */
    private static final int SR = 44100;      // Sampling Rate
    private static final double DECAY = .996; // energy decay factor

    /* Buffer for storing sound data. */
    private BoundedQueue<Double> buffer;

    /* Set for random numbers */
    private HashSet<Double> generatedNumbers;

    /**
     * Create a guitar string of the given frequency.
     * Create a buffer with capacity = SR / frequency.
     * You'll need to cast the result of this divsion operation into an int.
     * For better accuracy, use the Math.round() function before casting.
     * Your buffer should be initially filled with zeros.
     */
    public GuitarString(double frequency) {
        int capacity = (int) Math.round(SR / frequency);
        buffer = new ArrayRingBuffer<>(capacity);
        for (int i = 0; i < capacity; i++) {
            buffer.enqueue(0.0);
        }
    }

    /**
     *  Pluck the guitar string by replacing the buffer with white noise.
     *  Dequeue everything in the buffer,
     *  and replace it with random numbers between -0.5 and 0.5.
     *  You can get such a number by using:
     *  double r = Math.random() - 0.5
     *  Make sure that your random numbers are different from each other.
     */
    public void pluck() {
        generatedNumbers = new HashSet<>();
        int capacity = buffer.capacity();
        for (int i = 0; i < capacity; i++) {
            double value = Math.random() - 0.5;
            while (generatedNumbers.contains(value)) {
                value = Math.random() - 0.5;
            }
            generatedNumbers.add(value);
            buffer.dequeue();
            buffer.enqueue(value);
        }
    }

    /* Advance the simulation one time step by performing one iteration of
     * the Karplus-Strong algorithm.
     * Dequeue the front sample and enqueue a new sample that is
     * the average of the two multiplied by the DECAY factor.
     * Do not call StdAudio.play().
     */
    public void tic() {
        double front = buffer.dequeue();
        double next = buffer.peek();
        double now = DECAY * 0.5 * (front + next);
        buffer.enqueue(now);
    }

    /* Return the double at the front of the buffer. */
    public double sample() {
        double front = buffer.peek();
        return front;
    }
}
