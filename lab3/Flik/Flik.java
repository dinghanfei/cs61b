/** An Integer tester created by Flik Enterprises. */
public class Flik {
    public static boolean isSameNumber(Integer a, Integer b) {
         // return a == b;
         return a.equals(b);
    }
    /**
     * In this method, the meaning of a == b depends on the boxing property of Integer:
     * 1. Comparing references.
     * a == b compares references (i.e., their addresses in memory) of two Integer objects.
     * If a and b are two different Integer objects, even if they have the same value, a == b will return false.
     * 2. Autoboxing.
     * For Integer types, Java uses caching when the value is in the range -128 to 127,
     * so a and b may point to the same object.
     * This means that a == b may return true when comparing equal Integer values in this range.
     * 3. Out of Cache.
     * If the values of a and b are outside the range of -128 to 127,
     * even if they have the same value, a == b will return false because they will each create new Integer objects.
     */

}
