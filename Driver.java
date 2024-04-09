import java.util.Scanner;
import java.io.File;

public class Driver {
    public static void main(String[] args) {
        organiseFile();
    }

    // Organise input file into a list of vocab objects
    // Organise the list of vocab objects into a list of vocab lists
    public static void organiseFile() {

        VocabList vocabList = new VocabList();

        try {
            // Read input File
            File inputFile = new File("A3_input_file.txt");
            Scanner sc = new Scanner(inputFile);

            // Reading each "bloc of vocabulary" of the input file
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                
                // if the line starts with #, it is a topic name and it is the beginning of the
                // block of words
                if (line.startsWith("#")) {
                    // Create a new Vocab object with its topic name and its list of words
                    String toppicName = line.substring(1);
                    WordList WordList = new WordList();
                  
                    while (sc.hasNextLine()) {// Reading each word of the block of words
                        String word = sc.nextLine();
                         
                        // if the line is empty, it is the end of the block of words
                        if (word.equals(""))
                            break;

                        WordList.addWord(word);
                    }
                    Vocab vocab = new Vocab(toppicName, WordList);
                    vocabList.addVocab(vocab);
                }

            }
            sc.close();
        }

        catch (

        Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}