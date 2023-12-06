import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Dictionary {

    private HashTable container;
    Dictionary() {
        //666511
        container = new HashTable(666511);
    }
    Dictionary(int tableSize) {
        container = new HashTable(tableSize);
    }
    Dictionary(String word) {
        this();
        container.add(word);
    }
    Dictionary(File file) throws FileNotFoundException {
        this();
        
        try {
            long begin = System.currentTimeMillis();
            Scanner reader = new Scanner(file);
            while (reader.hasNext()) {
                String word = reader.next();
                container.add(word);
            }
            System.out.println(System.currentTimeMillis()-begin);
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException("File not Found");
        }
        
    }
    public void addWord(String word) throws WordAlreadyExistsException{
        if (container.contains(word)) {
            throw new WordAlreadyExistsException();
        }
        container.add(word);
    }
    public void removeWord(String word) throws WordNotFoundException{
        if (!container.contains(word)) {
            throw new WordNotFoundException();
        }
        container.remove(word);
    }
    public String[] findSimilar(String word) {
        String letters = "abcdefghijklmnopqrstuvwxyz\0";
        String wordToSearch;
        MyLinkedList<String> similarWords = new MyLinkedList<>();

        StringBuilder wordBuilder = new StringBuilder(word.toLowerCase());
        for (int j = 0;j < letters.length();j++) {
            for (int i = 0;i < word.length();i++) {
                wordToSearch = wordBuilder.insert(i, letters.charAt(j)).toString();
                if (container.contains(wordToSearch) && !wordToSearch.equals(word)) {
                    similarWords.addToTail(wordToSearch);
                }
                wordBuilder.deleteCharAt(i);
            }
            for (int i = 0;i < word.length();i++) {
                wordToSearch = wordBuilder.replace(i, i+1,String.valueOf(letters.charAt(j))).toString();
                if (container.contains(wordToSearch) && !wordToSearch.equals(word)) {
                    similarWords.addToTail(wordToSearch);
                }
                wordBuilder.replace(i, i+1, String.valueOf(word.charAt(i)));
            }
            // wordToSearch = letters.charAt(j) + word;
            // if (container.contains(wordToSearch)&& !wordToSearch.equals(word)) {
            //         similarWords.addToTail(wordToSearch);
            // }

            // wordToSearch = word + letters.charAt(j);
            // if (container.contains(wordToSearch)&& !wordToSearch.equals(word)) {
            //         similarWords.addToTail(wordToSearch);
            // }
        }
        
        return similarWords.toArray();
    }

    public boolean contains(String word) {
        return container.contains(word);
    }

    public ArrayList<Integer> dist() {
        return container.dist();
    }
    public int[] stat(ArrayList<Integer> counter, int maxi) {
        int[] meta = new int[maxi+1];
        for (Integer num: counter) {
            meta[num] += 1;
        }
        return meta;
    }
    
}



class WordAlreadyExistsException extends RuntimeException {
    WordAlreadyExistsException() {
        super("Word already exist in the dictionary");
    }
}

class WordNotFoundException extends RuntimeException {
    WordNotFoundException() {
        super("Word is not in the dictionary");
    }
}