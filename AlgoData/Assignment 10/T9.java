import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class T9 {
    private class Node {
        public Node[] next;
        public boolean valid;

        public Node() {
            next = new Node[8]; // We need 8 branches for keys 2-9
            valid = false;
        }
    }

    private Node root;

    public T9() {
        root = new Node();
    }

    public void insertWord(String word) {
        insertWord(root, word.toLowerCase(), 0);
    }

    private void insertWord(Node node, String word, int index) {
        if (index == word.length()) {
            node.valid = true;
            return;
        }

        char c = word.charAt(index);
        if (c == 'w' || c == 'q') {
            // Skip 'w' and 'q' as specified
            return;
        }
        
        int code = charToCode(c);
        if (code == -1) {
            // Handle invalid character, skip it
            insertWord(node, word, index + 1);
        } else {
            if (node.next[code] == null) {
                node.next[code] = new Node();
            }

            insertWord(node.next[code], word, index + 1);
        }
    }

    public String encode(String word) {
        StringBuilder encodedSequence = new StringBuilder();
        word = word.toLowerCase();

        for (char c : word.toCharArray()) {
            if (c == 'w' || c == 'q') {
                // Skip 'w' and 'q' as specified
                continue;
            } 

            if (c == ' ') {
                encodedSequence.append("0"); // Treat space as '0'
            } else {
                int code = charToCode(c);
                if (code != -1) {
                    encodedSequence.append(code + 2); // Offset to match key codes
                }
            }
        }

        return encodedSequence.toString();
    }

    public List<String> decode(String sequence) {
        List<String> results = new ArrayList<>();
        decodeFromNode(root, sequence, "", results);
        return results;
    }

    public String findWord(String sequence) {
        List<String> words = decode(sequence);
        if (!words.isEmpty()) {
            return words.get(0); // Return the first decoded word
        } else {
            return "No word found for the sequence '" + sequence + "'.";
        }
    }

    private void decodeFromNode(Node node, String sequence, String currentWord, List<String> results) {
        if (sequence.isEmpty()) {
            if (node.valid) {
                results.add(currentWord);
            }
            return;
        }

        char key = sequence.charAt(0);
        int index = keyToIndex(key);

        if (index == -1) {
            return; // Invalid key
        }

        if (key == '0') {
            // Handle '0' as space character
            if (node.next[7] != null) {
                decodeFromNode(node.next[7], sequence.substring(1), currentWord + " ", results);
            }
        } else {
            for (int i = 1; i <= 3; i++) {
                int nextIndex = index * 3 + i - 2; // Adjust the index
                if (nextIndex >= 0 && node.next[nextIndex] != null) {
                    decodeFromNode(node.next[nextIndex], sequence.substring(1), currentWord + codeToChar(nextIndex), results);
                }
            }
        }
    }

    private static int charToCode(char c) {
        switch (c) {
            case 'a': return 0;
            case 'b': return 1;
            case 'c': return 2;
            case 'd': return 3;
            case 'e': return 4;
            case 'f': return 5;
            case 'g': return 6;
            case 'h': return 7;
            case 'i': return 8;
            case 'j': return 9;
            case 'k': return 10;
            case 'l': return 11;
            case 'm': return 12;
            case 'n': return 13;
            case 'o': return 14;
            case 'p': return 15;
            case 'r': return 16;
            case 's': return 17;
            case 't': return 18;
            case 'u': return 19;
            case 'v': return 20;
            case 'x': return 21;
            case 'y': return 22;
            case 'z': return 23;
            case 'å': return 24;
            case 'ä': return 25;
            case 'ö': return 26;
            default: return -1; // Invalid character
        }
    }

    private static char codeToChar(int code) {
        char[] codeToCharMapping = "abcdefghijklmnopqrstuvwxyzåäö".toCharArray();
        if (code >= 0 && code < codeToCharMapping.length) {
            return codeToCharMapping[code];
        } else {
            return ' '; // Invalid code
        }
    }

    private static int keyToIndex(char key) {
        switch (key) {
            case '2': return 0;
            case '3': return 1;
            case '4': return 2;
            case '5': return 3;
            case '6': return 4;
            case '7': return 5;
            case '8': return 6;
            case '9': return 7;
            default: return -1; // Invalid key
        }
    }

    public static void main(String[] args) {
        T9 t9 = new T9();

        try (BufferedReader reader = new BufferedReader(new FileReader("kelly.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Remove any leading/trailing whitespace and insert the word into the T9 tree
                String word = line.trim();
                t9.insertWord(word);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        String sequence = "278624"; // This corresponds to "brunch"
        String word = t9.findWord(sequence);
        System.out.println("Word for sequence '" + sequence + "': " + word);
    }
}
