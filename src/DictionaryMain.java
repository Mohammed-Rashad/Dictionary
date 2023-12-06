import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Scanner;

import javax.sound.midi.Soundbank;
public class DictionaryMain {
    public static void main(String[] args) {
        StringBuilder wordBuilder = new StringBuilder("hhi");
        System.out.println(wordBuilder.replace(1,2, "o"));
        System.out.println(wordBuilder.replace(1,2, "h"));
        Scanner input = new Scanner(System.in);
        System.out.println("___ICS202 Project___");
        System.out.print("Enter File name> ");

        String fileName = input.next();
        try {
            Dictionary dictionary = new Dictionary(new File(fileName));
            System.out.println(dictionary.contains("painter"));

            ArrayList<Integer> counter = dictionary.dist();
            System.out.println(Arrays.toString(dictionary.stat(counter, Collections.max(counter))));
            Scanner reader = new Scanner(System.in);
            System.out.print("FindSimilarWord > ");
            String word = reader.nextLine();
            while (!word.equals("")) {
                System.out.println();
                System.out.println(Arrays.toString(dictionary.findSimilar(word.trim())));
                System.out.print("FindSimilarWord > ");
                word = reader.nextLine();
            }
            //mydictionary.txt
        }catch(FileNotFoundException e) {
            System.out.println("File not found");
            System.exit(0);
        }

        
    }

}
