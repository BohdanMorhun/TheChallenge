;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Stream;

 public class ConcatenatedWords {

     public static void main(String[] args) {

         ConcatenatedWords concatenatedWords = new ConcatenatedWords("words.txt");
     }

     private String theLongestConcatenatedWord = "";
     private String secondLongestConcatenatedWord = "";
     private int totalCount = 0;
     private TreeSet<String> allWords = new TreeSet<>();

     public ConcatenatedWords(String fileName) {
        readAllWordsFromTheFile(fileName);
        allWords.forEach(word -> {
            if (isConcatenatedWord(word, word.length())) {
                if (word.length() > theLongestConcatenatedWord.length()) {
                    secondLongestConcatenatedWord = theLongestConcatenatedWord;
                    theLongestConcatenatedWord = word;
                }
                totalCount++;
            }
        });
        printResultsToConsole();
    }

    private Boolean isConcatenatedWord(String word, int length) {
        int wordLength = word.length();
        if (wordLength == 0)
            return true;
        for (int i = 1; i <= wordLength; ++i) {
            if (i == length)
                return false;
            String str = word.substring(0, i);
            if (allWords.contains(str)) {
                if (isConcatenatedWord(word.substring(i), length))
                    return true;
            }
        }
        return false;
    }

    private void readAllWordsFromTheFile(String fileName) {
        try (Stream<String> stream = Files.lines(Paths.get(fileName))) {
            stream.forEach(word -> allWords.add(word));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void printResultsToConsole() {
        System.out.println("\n" + "The longest concatenated word = " + theLongestConcatenatedWord + "; length: "
                + theLongestConcatenatedWord.length() + " letters");
        System.out.println("The 2nd longest concatenated words = " + secondLongestConcatenatedWord + "; length: "
                + secondLongestConcatenatedWord.length() + " letters");
        System.out.println("The total count of concatenated words in the file = " + totalCount);
    }
 }



