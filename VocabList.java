// Purpose: This class is used to store the list of vocab objects. This is a double linked list
public class VocabList {
    // Node class
    static class Node {
        private Vocab vocab;
        private Node next;
        private Node prev;

        public Node(Vocab vocab) {
            this.vocab = vocab;
            this.next = null;
            this.prev = null;
        }
    }

    // Attributes
     private Node head;

    // Constructor
    public VocabList() {
        this.head = null;
    }

    // Add a vocab to the list
    public void addVocab(Vocab vocab) {
        Node newNode = new Node(vocab);
        // if the list is empty
        if (head == null) {
            head = newNode;
        } else { // if the list is not empty
            Node current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
            newNode.prev = current;
        }
    }

    // remove a vocab from the list
    public void removeVocab(String topicName) {
        // if the list is empty
        if (head == null) {
            return;
        }

        // if the vocab is the first element
        if (head.vocab.getTopicName().equals(topicName)) {
            head = head.next;
            head.prev = null;
            return;
        }
        // if the vocab is not the first element
        Node current = head;
        while (current.next != null) {
            if (current.next.vocab.getTopicName().equals(topicName)) {
                current.next = current.next.next;
                current.next.prev = current;
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
            result += current.vocab.toString() + "\n";
            current = current.next;
        }
        return result;
    }

}
