import java.util.Scanner;
import java.io.File;

public class Driver {
    public static void main(String[] args) {
        VocabList vocabList = organiseFile("A3_input_file.txt");
        printPickATopic(vocabList);
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
        System.out.println("1 browse a topic");
        System.out.println("2 insert a new topic before another one");
        System.out.println("3 insert a new topic after another one");
        System.out.println("4 remove a topic");
        System.out.println("5 modify a topic");
        System.out.println("6 search topics for a word");
        System.out.println("7 load from a file");  //call organize file and then use this new list for the main menu
        System.out.println("8 show all words starting with a given letter");
        System.out.println("9 save to file");
        System.out.println("0 exit");
        System.out.println("-----------------------------");

        Scanner in = new Scanner(System.in);
        int choice = in.nextInt();

        switch (choice) {

            case 1:
                browseTopic(vocabList);
                break;

            case 2:
                insertTopicBefore();
                break;

            case 3:
                insertTopicAfter();
                break;

            case 4:
                removeTopic();
                break;

            case 5:
                modifyTopic();
                break;

            case 6:
                searchWord();
                break;

            case 7:
                loadFile();
                break;

            case 8:
                showWords();
                break;

            case 9:
                saveFile();
                break;

            case 0:
                System.exit(0);
                break;

            default:
                System.out.println("Invalid choice");
                mainMenu();
                break;
        }
    }

    private static void browseTopic(VocabList vocabList) {
        int choice = printPickATopic(vocabList);
        
    }

    public static int printPickATopic(VocabList vocabList) {
        System.out.println("Pick a topic");
        System.out.println("-----------------------------");
        VocabList.Node current = vocabList.getHead();
        for (int i = 1; i <= vocabList.getSize(); i++) {
            if (vocabList.getSize() == 0) {
                System.out.println("No topics available");
            } else {

                System.out.println(i + "." + current.getVocab().getTopicName());
                current = current.getNext();
            }
            
        }

        System.out.println("------------------------------");
        System.out.println("Enter Your Choice:");
        Scanner in = new Scanner(System.in);
        int choice = in.nextInt();
       if(choice == 0)
           mainMenu(vocabList);
       else
        return choice;
    }

    public void insertTopiceBefore() {

    }

}