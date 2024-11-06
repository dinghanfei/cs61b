/** A class for off-by-1 comparators. */
public class OffByOne implements CharacterComparator  {
    @Override
    public boolean equalChars(char x, char y) {
        int delta = x - y;
        if (delta == 1 || delta == -1) {
            return true;
        }
        return false;
    }
}
