import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class HashTable {
    int tableSize;
    final static int DEFAULT_TABLE_SIZE = 35731;
    private MyLinkedList[] table;
    HashTable() {
        this(DEFAULT_TABLE_SIZE);
    }
    HashTable(int tableSize) {
        this.tableSize = tableSize;
        table = new MyLinkedList[tableSize];
        for (int i = 0;i < tableSize;i++) {
            table[i] = new MyLinkedList<String>();
        }
    }
    private static int hash(String word) {
        //jenkinsHash
        int hashValue = 0;

        for (int i = 0; i < word.length(); ++i) {
            hashValue += word.charAt(i);
            hashValue += (hashValue << 10);
            hashValue ^= (hashValue >>> 6);
        }

        hashValue += (hashValue << 3);
        hashValue ^= (hashValue >>> 11);
        hashValue += (hashValue << 15);
        return Math.abs(hashValue);
        //[331123, 231398, 81217, 18954, 3316, 430, 69, 4]
        // djb2Hash
        // int hash = 5381;
        // for (int i = 0; i < word.length(); i++) {
        //     hash = ((hash << 5) + hash) + word.charAt(i);
        // }
        // return hash & 0x7FFFFFFF; 
        // big file
        //[330787, 231932, 81083, 18959, 3230, 471, 43, 6]

        //return word.hashCode() & 0x7FFFFFFF;
        //[330778, 231788, 81371, 18835, 3240, 442, 54, 3]

        
    }
    public void add(String element) {
        element = element.toLowerCase();
        int hashValue = hash(element) % tableSize;
        table[hashValue].addToTail(element);
    }
    public void remove(String element) {
        element = element.toLowerCase();
        int hashValue = hash(element) % tableSize;
        table[hashValue].delete(element);
    }
    public boolean contains(String element) {
        element = element.toLowerCase();
        int hashValue = hash(element) % tableSize;
        return table[hashValue].contains(element);
    }
    public ArrayList<Integer> dist() {
        ArrayList<Integer> counterTable = new ArrayList<Integer>(tableSize);
        for (MyLinkedList<String> list: table) {
            counterTable.add(list.getLength());
            if (list.getLength() == 7) {
                System.out.println(list);
            }
        }
        return counterTable;
    }

}