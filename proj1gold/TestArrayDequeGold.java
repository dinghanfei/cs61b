import static org.junit.Assert.*;
import org.junit.Test;

public class TestArrayDequeGold {
    @Test
    public void test() {
        StudentArrayDeque<Integer> buggyDeque = new StudentArrayDeque<>();
        ArrayDequeSolution<Integer> correctDeque = new ArrayDequeSolution<>();
        StringBuilder log = new StringBuilder();

        for (int i = 0; i < 100; i++) {
            /**
             * When the deque is empty, only integers can be added.
             * Use only the correct deque implementation(ArrayDequeSolution)
             * to determine whether the deque is empty or not.
             */
            if (correctDeque.size() == 0) {
                /**
                 * @source cs61b proj1gold StudentArrayDequeLauncher
                 */
                double numberBetweenZeroAndOne = StdRandom.uniform();
                if (numberBetweenZeroAndOne < 0.5) {
                    log.append("addFirst(" + i + ")\n");
                    buggyDeque.addFirst(i);
                    correctDeque.addFirst(i);
                } else {
                    log.append("addLast(" + i + ")\n");
                    buggyDeque.addLast(i);
                    correctDeque.addLast(i);
                }
            } else {
                /**
                 * Generating Random Numbers：0，1，2，3.
                 */
                int num = StdRandom.uniform(4);
                /**
                 * Initialise the integers removed from both queues to 0.
                 * This also assumes that the add operation passes the test.
                 * emmmmm...
                 */
                Integer buggyDequeRemove = 0;
                Integer correctDequeRemove = 0;
                if (num == 0) {
                    log.append("addFirst(" + i + ")\n");
                    buggyDeque.addFirst(i);
                    correctDeque.addFirst(i);
                } else if (num == 1) {
                    log.append("addLast(" + i + ")\n");
                    buggyDeque.addLast(i);
                    correctDeque.addLast(i);
                } else if (num == 2) {
                    log.append("removeFirst()\n");
                    buggyDequeRemove = buggyDeque.removeFirst();
                    correctDequeRemove = correctDeque.removeFirst();
                } else {
                    log.append("removeLast()\n");
                    buggyDequeRemove = buggyDeque.removeLast();
                    correctDequeRemove = correctDeque.removeLast();
                }
                /**
                 * assertEquals(message, expected, actual)
                 */
                assertEquals(log.toString(), correctDequeRemove, buggyDequeRemove);
            }
        }
    }
}
