import java.io.BufferedReader;
import java.io.FileReader;

public class UpdatedZip {
    Node[] data;
    int max;

    public class Node {
        Integer code;
        String name;
        Integer pop;

        public Node(Integer code, String name, Integer pop) {
            this.code = code;
            this.name = name;
            this.pop = pop;
        }
    }

    public UpdatedZip(String file) {
        data = new Node[10000];
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            int i = 0;
            while ((line = br.readLine()) != null) {
                String[] row = line.split(",");
                Integer code = Integer.valueOf(row[0].replaceAll("\\s", ""));
                data[i++] = new Node(code, row[1], Integer.valueOf(row[2]));
            }
            max = i - 1;
        } catch (Exception e) {
            System.out.println("File " + file + " not found");
        }
    }

    public Node linear(Integer zip) {
        for (int i = 0; i <= max; i++) {
            if (data[i].code.equals(zip)) {
                return data[i];
            }
        }
        return null; // Zip code not found
    }

    public Node binary(Integer zip) {
        int left = 0;
        int right = max;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (data[mid] == null) {
                return null; // Zip code not found (data[mid] is null)
            }

            int compareResult = data[mid].code.compareTo(zip);

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
        UpdatedZip zipTable = new UpdatedZip("postnummer.csv");

        // Example usage of the linear search:
        Integer searchZip = 11115;
        UpdatedZip.Node resultLinear = zipTable.linear(searchZip);
        if (resultLinear != null) {
            System.out.println("Linear Search Result for " + searchZip + ": " + resultLinear.name);
        } else {
            System.out.println("Zip code " + searchZip + " not found (linear search).");
        }

        // Example usage of the binary search (assuming data is sorted by zip code):
        Integer searchZipBinary = 98499;
        UpdatedZip.Node resultBinary = zipTable.binary(searchZipBinary);
        if (resultBinary != null) {
            System.out.println("Binary Search Result for " + searchZipBinary + ": " + resultBinary.name);
        } else {
            System.out.println("Zip code " + searchZipBinary + " not found (binary search).");
        }
    }
}
