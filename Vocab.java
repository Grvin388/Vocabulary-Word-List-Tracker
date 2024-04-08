// Purpose: object Vocab, that store the topic name and the list of words
public class Vocab {
    // Attributes
    private String topicName;
    private WordList wordList;

    // Constructor
    public Vocab(String topicName, WordList wordList) {
        this.topicName = topicName;
        this.wordList = wordList;
    }

    // Getters
    public String getTopicName() {
        return topicName;
    }

    public WordList getWordList() {
        return wordList;
    }

    // Setters
    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

    public void setWordList(WordList wordList) {
        this.wordList = wordList;
    }

    // toString
    public String toString() {
        return topicName + "\n" + wordList.toString();
    }
}
