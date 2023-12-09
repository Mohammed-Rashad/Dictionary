import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

public class Dictionary {
    private Container container;
    Dictionary() {
        container = new Container();
    }
    Dictionary(String word) {
        this();
        if (!container.contains(word)) {
            container.insert(word);
        }else {
            throw new WordAlreadyExistsException();
        }
    }
    Dictionary(File file) throws FileNotFoundException {
        this();
        try {
            //long begin = System.currentTimeMillis();
            Scanner reader = new Scanner(file);
            while (reader.hasNext()) {
                String word = reader.next();
                container.insert(word);
            }
            //System.out.println(System.currentTimeMillis()-begin);
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException("File not Found");
        }
        
    }
    public void addWord(String word) throws WordAlreadyExistsException{
        if (!container.contains(word)) {
            container.insert(word);
        } else {
            throw new WordAlreadyExistsException();
        }
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

}





