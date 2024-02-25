import java.io.BufferedReader;
import java.io.FileReader;

public class ZipHashCollision2 {
    Node[] data;
    int max;
    int arraySize;

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

    public ZipHashCollision2(String file, int arraySize) {
        this.arraySize = arraySize;
        data = new Node[arraySize];
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] row = line.split(",");
                String zip = row[0].replaceAll("\\s", "");
                int index = hash(Integer.parseInt(zip));
                int collisions = 0; // New variable to count collisions
                while (data[index] != null) {
                    // Handle collisions by linear probing.
                    collisions++; // Count collisions
                    System.out.println("Collision at index: " + index + ", Collisions so far: " + collisions);
                    index = (index + 1) % arraySize;
                }
                data[index] = new Node(zip, row[1], Integer.valueOf(row[2]));
            }
        } catch (Exception e) {
            System.out.println("File " + file + " not found");
        }
    }

    public Node lookup(String zip) {
        int index = hash(Integer.parseInt(zip));
        int originalIndex = index;
        while (data[index] != null) {
            if (data[index].code.equals(zip)) {
                return data[index];
            }
            index = (index + 1) % arraySize;
            if (index == originalIndex) {
                break; // Prevent infinite loop if the array is full.
            }
        }
        return null; // Zip code not found
    }

    private int hash(int zip) {
        return zip % arraySize;
    }
}
