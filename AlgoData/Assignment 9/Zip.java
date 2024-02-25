import java.io.BufferedReader;
import java.io.FileReader;

public class Zip {
    Node[] data;
    int max;

    public class Node {
        String code;
        String name;
        Integer pop;

        public Node(String code, String name, Integer pop) {
            this.code = code;
            this.name = name;
            this.pop = pop;
        }
    }

    public Zip(String file) {
        data = new Node[10000];
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            int i = 0;
            while ((line = br.readLine()) != null) {
                String[] row = line.split(",");
                data[i++] = new Node(row[0], row[1], Integer.valueOf(row[2]));
            }
            max = i - 1;
        } catch (Exception e) {
            System.out.println("File " + file + " not found");
        }
    }

    public Zip.Node linear(String searchZip) {
        for (int i = 0; i <= max; i++) {
            if (data[i] != null && data[i].code.equals(searchZip)) {
                return data[i];
            }
        }
        return null; // Zip code not found
    }
    

    public Node binary(String searchZipBinary) {
        int left = 0;
        int right = max;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            int compareResult = data[mid].code.compareTo(searchZipBinary);

            if (compareResult == 0) {
                return data[mid]; // Zip code found
            } else if (compareResult < 0) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return null; // Zip code not found
    }

    public static void main(String[] args) {
        Zip zipTable = new Zip("postnummer.csv");

        // Example usage of the linear search:
        String searchZip = "12345";
        Zip.Node resultLinear = zipTable.linear(searchZip);
        if (resultLinear != null) {
            System.out.println("Linear Search Result for " + searchZip + ": " + resultLinear.name);
        } else {
            System.out.println("Zip code " + searchZip + " not found (linear search).");
        }

        // Example usage of the binary search (assuming data is sorted by zip code):
        String searchZipBinary = "54321";
        Zip.Node resultBinary = zipTable.binary(searchZipBinary);
        if (resultBinary != null) {
            System.out.println("Binary Search Result for " + searchZipBinary + ": " + resultBinary.name);
        } else {
            System.out.println("Zip code " + searchZipBinary + " not found (binary search).");
        }
    }
}
