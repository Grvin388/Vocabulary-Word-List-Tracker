import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;

public class Driver {
    public static void main(String[] args) {
        VocabList vocabList = organiseFile("A3_input_file.txt");
        mainMenu(vocabList);
    }

    // Organise input file into a list of vocab objects
    // Organise the list of vocab objects into a list of vocab lists
    public static VocabList organiseFile(String file) {

        VocabList vocabList = new VocabList();

        try {
            // Read input File
            File inputFile = new File(file);
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
                        if (word.equals("")) {
                            break;
                        }

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
        return vocabList;
    }

    public static void mainMenu(VocabList vocabList) {
        int choice = -1;
        Scanner sc = new Scanner(System.in);
        while (choice != 0) { // use a while loop to keep the menu running until the user chooses to exit
            System.out.println("Press enter to access the main menu");
            sc.nextLine();
            System.out.println("1 browse a topic");
            System.out.println("2 insert a new topic before another one");
            System.out.println("3 insert a new topic after another one");
            System.out.println("4 remove a topic");
            System.out.println("5 modify a topic");
            System.out.println("6 search topics for a word");
            System.out.println("7 load from a file"); // call organize file and then use this new list for the main menu
            System.out.println("8 show all words starting with a given letter");
            System.out.println("9 save to file");
            System.out.println("0 exit");
            System.out.println("-----------------------------");

            choice = sc.nextInt();
            sc.nextLine();
            switch (choice) {

                case 1:
                    browseTopic(vocabList);

                    break;

                case 2:
                    VocabList newVocabList = insertTopicBefore(vocabList);
                    vocabList = newVocabList;
                    break;

                case 3:
                    VocabList newVocabList1 = insertTopicAfter(vocabList);
                    vocabList = newVocabList1;
                    break;

                case 4:
                    VocabList newVocabList2 = removeTopic(vocabList);
                    vocabList = newVocabList2;
                    break;

                case 5:
                    VocabList newVocabList3 = modifyTopic(vocabList);
                    vocabList = newVocabList3;
                    break;

                case 6:
                    searchWord(vocabList);
                    break;
                
                  case 7:
                  loadFile();
                  break;
                  
                 /* case 8:
                 * showWords();
                 * break;
                 * 
                 * case 9:
                 * saveFile();
                 * break;
                 * 
                 * 
                 * 
                 * default:
                 * System.out.println("Invalid choice");
                 * mainMenu(vocabList);
                 * break;
                 */
            }
        }
        System.exit(0);
    }

    public static int printPickATopic(VocabList vocabList) {
        System.out.println("Pick a topic");
        System.out.println("-----------------------------");

        ArrayList<Vocab> arrList = vocabList.getVocabList();

        for (int i = 0; i < arrList.size(); i++) {
            System.out.println(i + 1 + ". " + arrList.get(i).getTopicName());
        }

        System.out.println("------------------------------");
        System.out.println("Enter Your Choice:");
        Scanner in = new Scanner(System.in);
        int choice = in.nextInt();
        // if (choice == 0)
        // mainMenu(vocabList);

        return choice;
    }

    private static void browseTopic(VocabList v) {
        int choice = printPickATopic(v);

        ArrayList<Vocab> vocabList = v.getVocabList();
        Vocab vocab = vocabList.get(choice - 1);
        String topicName = vocab.getTopicName();

        System.out.println("Topic: " + topicName);

        ArrayList<String> wordList = vocab.getWordList().getVocabListArray();
        for (int i = 0; i < wordList.size(); i++) {
            System.out.print(i + 1 + " : " + wordList.get(i) + " \t\t");
            if ((i + 1) % 4 == 0)
                System.out.println();

        }
        System.out.println("\n\n");

    }

    public static VocabList insertTopicBefore(VocabList vocabList) {
        int choice = printPickATopic(vocabList);

        ArrayList<Vocab> arrayListVocab = vocabList.getVocabList();

        String topicNameToInsertBefore = arrayListVocab.get(choice - 1).getTopicName();

        System.out.println("Enter the new topic name:");
        Scanner sc = new Scanner(System.in);
        String topicName = sc.nextLine();

        System.out.println("Enter a word - Press Enter to quit");
        WordList wordList = new WordList();
        String word = sc.nextLine();
        while (!word.equals("")) {
            wordList.addWord(word);
            word = sc.nextLine();
        }

        Vocab newVocab = new Vocab(topicName, wordList);

        vocabList.addVocabBefore(topicNameToInsertBefore, newVocab);

        /*
         * // print the new vocab list
         * ArrayList<Vocab> arr = vocabList.getVocabList();
         * for (int i = 0; i < arr.size(); i++) {
         * System.out.println(arr.get(i));
         * }
         */
        return vocabList;
    }

    public static VocabList insertTopicAfter(VocabList vocabList) {
        int choice = printPickATopic(vocabList);

        ArrayList<Vocab> arrayListVocab = vocabList.getVocabList();

        String topicNameToInsertAfter = arrayListVocab.get(choice - 1).getTopicName();

        System.out.println("Enter the new topic name:");
        Scanner in = new Scanner(System.in);
        String topicName = in.nextLine();

        System.out.println("Enter a word - Press Enter to quit");
        WordList wordList = new WordList();
        String word = in.nextLine();
        while (!word.equals("")) {
            wordList.addWord(word);
            word = in.nextLine();
        }

        Vocab newVocab = new Vocab(topicName, wordList);

        vocabList.addVocabAfter(topicNameToInsertAfter, newVocab);
        /*
         * // print the new vocab list
         * ArrayList<Vocab> arr = vocabList.getVocabList();
         * for (int i = 0; i < arr.size(); i++) {
         * System.out.println(arr.get(i));
         * }
         */
        return vocabList;
    }

    public static VocabList removeTopic(VocabList vocabList) {
        int choice = printPickATopic(vocabList);
        String topicNameToRemove = vocabList.getVocabList().get(choice - 1).getTopicName();
        vocabList.removeVocab(topicNameToRemove);
        /*
         * // print the new vocab list
         * ArrayList<Vocab> arr = vocabList.getVocabList();
         * for (int i = 0; i < arr.size(); i++) {
         * System.out.println(arr.get(i));
         * }
         */
        return vocabList;
    }

    public static VocabList modifyTopic(VocabList vocabList) {
        int choice = printPickATopic(vocabList);
        ArrayList<Vocab> arrayListVocab = vocabList.getVocabList();
        System.out.println("a add a word\n" +
                "r remove a word\n" +
                "c change a word\n" +
                "0 Exit");
        System.out.println("Enter Your Choice: ");
        Scanner sc = new Scanner(System.in);
        String letterChoice = sc.next();
        switch (letterChoice){
            case "a":
                System.out.println("Enter the word to add: ");
                String wordToAdd = sc.next();
                arrayListVocab.get(choice - 1).getWordList().addWord(wordToAdd);
                break;
            case "r":
                System.out.println("Enter the word to remove: ");
                String wordToRemove = sc.next();
                arrayListVocab.get(choice - 1).getWordList().removeWord(wordToRemove);
                break;
            case "c":
                System.out.println("Enter the word to change: ");
                String wordToChange = sc.next();
                System.out.println("Enter the new word: ");
                String newWord = sc.next();
                arrayListVocab.get(choice - 1).getWordList().changeWord(wordToChange, newWord);
                break;
            case "0":
                break;
            default:
                System.out.println("Invalid choice");
                break;


        }

        return vocabList;
    }

    public static void searchWord(VocabList vocabList) {
        int i = 0;
        boolean found = false;
        System.out.println("Enter a word to search for:");
        Scanner sc = new Scanner(System.in);
        String word = sc.nextLine();
        ArrayList<Vocab> arr = vocabList.getVocabList();
        for (i = 0; i < arr.size(); i++) {
            ArrayList<String> wordList = arr.get(i).getWordList().getVocabListArray();
            for (int j = 0; j < wordList.size(); j++) {
                if (wordList.get(j).equals(word)) {
                    System.out.println("Word found in topic: " + arr.get(i).getTopicName());
                    found = true;
                    break;
                }
            }
            if (found) {
                break;
            }

        }

        if (i == arr.size()) {
            System.out.println("Word not found");
        }

    }


    public static void loadFile() {
        System.out.println("Enter the name of the text file to load:");
        Scanner sc = new Scanner(System.in);
        String file = sc.nextLine();

        VocabList vocabList = organiseFile(file);
        System.out.println("The file " + file + " has been loaded");
        mainMenu(vocabList);


    }
}