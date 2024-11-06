public class OffByN implements CharacterComparator {
    private int OffByN;
    /** The OffByN constructor. */
    public OffByN(int N) {
        this.OffByN = N;
    }
    @Override
    public boolean equalChars(char x, char y) {
        int delta = x - y;
        if (delta == this.OffByN || delta == -this.OffByN) {
            return true;
        }
        return false;
    }
}
