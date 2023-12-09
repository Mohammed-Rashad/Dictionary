public class Container {
    final static int TABLESIZE = 675;
    private AVLTree[] container;
    Container(int tableSize) {
        container = new AVLTree[tableSize];
        for(int i = 0;i < tableSize;i++) {
            container[i] = new AVLTree<String>();
        }
    }
    Container() {
        this(TABLESIZE);
    }
    public int hash(String word) {
        word = word.toLowerCase();
        int hashValue = 0;
        hashValue += word.charAt(0) - 'a';
        if (word.length() > 1) {
            hashValue += (word.charAt(1)-'a')*26;
        }
        return Math.abs(hashValue); // return abs value in case of numbers or other characters that might return negative values;
    }
    public void insert(String word) {
        int hash = hash(word);
        container[hash].insert(word);
    }
    public boolean contains(String word) {
        int hash = hash(word);
        return container[hash].contains(word);
    }
    public void remove(String word) {
        int hash = hash(word);
        container[hash].remove(word);
    }

}
