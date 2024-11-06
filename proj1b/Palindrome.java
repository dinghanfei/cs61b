public class Palindrome {
    /**
     * @return a Deque
     * where the characters appear in the same order as in the String.
     */
    public Deque<Character> wordToDeque(String word) {
        int len = word.length();
        Deque<Character> wordDeque = new LinkedListDeque<Character>();
        for (int i = 0; i < len; i++) {
            char ch = word.charAt(i);
            wordDeque.addLast(ch);
        }
        return wordDeque;
    }

    /** Private helper method.
     * Recursive dependency on removeFirst() and removeLast()
     * which reduce the size of the problem.
     */
    private boolean isPalindrome(Deque<Character> wordDeque) {
        /** basic situation */
        if (wordDeque.size() <= 1) {
            return true;
        }
        char ch1 = wordDeque.removeFirst();
        char ch2 = wordDeque.removeLast();
        if (ch1 != ch2) {
            return false;
        }
        return isPalindrome(wordDeque);
    }
    /**
     * @param word the given word
     * @return true if the given word is a palindrome
     */
    public boolean isPalindrome(String word) {
        /** Any word of length 1 or 0 is a palindrome.*/
        if (word.length() == 0 || word.length() == 1) {
            return true;
        }
        Deque<Character> wordDeque = wordToDeque(word);
        return isPalindrome(wordDeque);

        /* A very simple implementation
        Deque<Character> wordDeque = wordToDeque(word);
        int front = 0;
        int back = word.length() - 1;
        while (front < back) {
            char ch1 = wordDeque.get(front);
            char ch2 = wordDeque.get(back);
            if (ch1 != ch2) {
                return false;
            }
            front ++;
            back --;
        }
        return true;
        */
    }
    private boolean isPalindrome(Deque<Character> wordDeque, CharacterComparator cc) {
        /** basic situation */
        if (wordDeque.size() <= 1) {
            return true;
        }
        char ch1 = wordDeque.removeFirst();
        char ch2 = wordDeque.removeLast();
        if (!(cc.equalChars(ch1, ch2))) {
            return false;
        }
        return isPalindrome(wordDeque, cc);
    }
    /**
     * Generalized Palindrome.
     * @param word the given word
     * @param cc  is an interface-type variable
     *            that can refer to any object
     *            that implements the CharacterComparator interface
     * @return return true if the word is a palindrome
     */
    public boolean isPalindrome(String word, CharacterComparator cc) {
        if (word.length() == 0 || word.length() == 1) {
            return true;
        }
        Deque<Character> wordDeque = wordToDeque(word);
        return isPalindrome(wordDeque, cc);
    }
}
