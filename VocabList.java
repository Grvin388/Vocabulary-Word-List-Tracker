// Purpose: This class is used to store the list of vocab objects. This is a double linked list
public class VocabList {
    // Node class
    static class Node {
        private Vocab vocab;
        private Node next;
        private Node prev;
        
        public Vocab getVocab() {
            return vocab;
        }
        
        public Node getNext() {
            return next;
        }
        
        public Node getPrev() {
            return prev;
        }

        public Node(Vocab vocab) {
            this.vocab = vocab;
            this.next = null;
            this.prev = null;
        }
    }

    // Attributes
     private Node head;
     private int size = 0;
    // Getter for size
    public int getSize() {
        return size;
    }
       // Getter for head
       public Node getHead() {
        return head;
    }

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
        size++;
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
        size--;
    }


    // add vocab in front of a specific topic
    public void addVocabBefore(String topicName, Vocab vocab) {
        Node newNode = new Node(vocab);
        // if the list is empty
        if (head == null) {
            head = newNode;
        } else { // if the list is not empty
            Node current = head;
            while (current.next != null) {
                if (current.vocab.getTopicName().equals(topicName)) {
                    newNode.next = current;
                    newNode.prev = current.prev;
                    current.prev = newNode;
                    current.prev.next = newNode;
                    return;
                }
                current = current.next;
            }
            current.next = newNode;
            newNode.prev = current;
        }
        size++;
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
