package Day15;
public class SentenceFinder {
    public static String findSentenceWithWord(String[] sentences, String word) {
        for (String sentence : sentences) {
            if (sentence.contains(word)) {
                return sentence;
            }
        }
        return "Not Found";
    }

    public static void main(String[] args) {
        String[] sentences = {
                "Java is a popular programming language.",
                "Practice makes perfect."
        };

        String wordToFind = "makes";
        String result = findSentenceWithWord(sentences, wordToFind);
        System.out.println("Result: " + result);
    }
}
