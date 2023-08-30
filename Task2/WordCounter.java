import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class WordCounter {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = "";

        System.out.println("Welcome to Word Counter!");
        System.out.print("Enter 'text' to input text or 'file' to provide a file: ");
        String choice = scanner.nextLine().trim().toLowerCase();

        if (choice.equals("text")) {
            System.out.print("Enter the text: ");
            input = scanner.nextLine();
        } else if (choice.equals("file")) {
            System.out.print("Enter the file path: ");
            String filePath = scanner.nextLine();
            try {
                input = readFile(filePath);
            } catch (IOException e) {
                System.out.println("Error reading the file.");
                scanner.close();
                return;
            }
        } else {
            System.out.println("Invalid choice.");
            scanner.close();
            return;
        }

        String[] words = input.split("[\\s\\p{Punct}]+");
        int totalWords = words.length;

        System.out.println("Total words: " + totalWords);

        Map<String, Integer> wordFrequency = new HashMap<>();
        for (String word : words) {
            wordFrequency.put(word, wordFrequency.getOrDefault(word, 0) + 1);
        }

        System.out.println("Number of unique words: " + wordFrequency.size());

        for (Map.Entry<String, Integer> entry : wordFrequency.entrySet()) {
            System.out.println("'" + entry.getKey() + "': " + entry.getValue() + " occurrences");
        }

        scanner.close();
    }

    private static String readFile(String filePath) throws IOException {
        StringBuilder content = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }
        }
        return content.toString();
    }
}
