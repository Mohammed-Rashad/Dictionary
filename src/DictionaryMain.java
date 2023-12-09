import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;


public class DictionaryMain {
    final static String DIRECTORY = "Dictionaries";
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("___ICS202 Project___");
        System.out.print("Enter File name> ");

        String fileName = DIRECTORY + "/"+input.next();
        try {
            Dictionary dictionary = new Dictionary(new File(fileName));
            System.out.println(dictionary.contains("painter"));

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
