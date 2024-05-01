import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class ImpledgeTest {
    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("C:\\Users\\pvvsk\\Downloads\\Input_02.txt");
        Scanner sc = new Scanner(file);

        List<String> words = new ArrayList<>();

        while(sc.hasNext()) {
            words.add(sc.next());
        }

        sc.close();

        int n = words.size();

        Collections.sort(words, Comparator.comparingInt(String::length));

        Map<String, Integer> wordFreq = new HashMap<>();
        for (String word : words) {
            wordFreq.put(word, wordFreq.getOrDefault(word, 0) + 1);
        }

        int compoundWordCount = 0;
        for (int i = n - 1; i >= 0; i--) {
            String currentWord = words.get(i);
            int prefixCount = countPrefixes(currentWord, wordFreq);
            if (prefixCount == 0) {
                compoundWordCount++;
                if (compoundWordCount == 1) {
                    System.out.println("Longest Compound Word: " + currentWord);
                } else if (compoundWordCount == 2) {
                    System.out.println("Second Longest Compound Word: " + currentWord);
                }
            }
        }

        System.out.println("Time taken to process: " + System.currentTimeMillis() + " milli seconds");
    }

    public static int countPrefixes(String word, Map<String, Integer> wordFreq) {
        int pCount = 0;
        StringBuilder prefix = new StringBuilder();
        for (int j = word.length() - 1; j >= 0; j--) {
            prefix.insert(0, word.charAt(j));
            if (wordFreq.containsKey(prefix.toString())) {
                if (!prefix.toString().equals(word)) {
                    prefix.setLength(0); 
                    pCount = 0;
                }
            } else {
                pCount++;
            }
        }
        return pCount;
    }
}
