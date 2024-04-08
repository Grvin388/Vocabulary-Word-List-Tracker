// Purpose: This class is used to store the list of words. This is a single linked list
public class WordList {
    // Node class
    private static class Node {
        private String word;
        private Node next;

        private Node(String word) {
            this.word = word;
            this.next = null;
        }
    }

    // Attributes
    private Node head;

    // Constructor
    public WordList() {
        this.head = null;
    }

    // Add a word to the list
    public void addWord(String word) {
        Node newNode = new Node(word);
        // if the list is empty
        if (head == null) {
            head = newNode;
        } else { // if the list is not empty
            Node current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
    }

    // remove a word from the list
    public void removeWord(String word) {
        // if the list is empty
        if (head == null) {
            return;
        }

        // if the word is the first element
        if (head.word.equals(word)) {
            head = head.next;
            return;
        }
        // if the word is not the first element
        Node current = head;
        while (current.next != null) {
            if (current.next.word.equals(word)) {
                current.next = current.next.next;
                return;
            }
            current = current.next;
        }
    }

    // toString
    public String toString() {
        String result = "";
        Node current = head;
        while (current != null) {
            result += current.word + "\n";
            current = current.next;
        }
        return result;
    }
}
