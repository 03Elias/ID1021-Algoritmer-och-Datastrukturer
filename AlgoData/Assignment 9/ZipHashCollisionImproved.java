import java.io.BufferedReader;
import java.io.FileReader;

public class ZipHashCollisionImproved {
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

    public ZipHashCollisionImproved(String file) {
        data = new Node[20000]; // Double the array size to reduce collisions.
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] row = line.split(",");
                String zip = row[0].replaceAll("\\s", ""); // Remove spaces from the zip code.
                int index = Integer.parseInt(zip) % data.length;
                while (data[index] != null) {
                    index = (index + 1) % data.length; // Linear probing to find the next available slot.
                }
                data[index] = new Node(zip, row[1], Integer.valueOf(row[2]));
            }
        } catch (Exception e) {
            System.out.println("File " + file + " not found");
        }
    }

    public Node lookup(String zip) {
        int index = Integer.parseInt(zip) % data.length;
        while (data[index] != null) {
            if (data[index].code.equals(zip)) {
                return data[index];
            }
            index = (index + 1) % data.length; // Continue probing.
        }
        return null; // Zip code not found
    }

    public static void main(String[] args) {
        ZipHashCollisionImproved zipTable = new ZipHashCollisionImproved("postnummer.csv");

        // Example usage of the lookup method:
        String searchZip = "12237";
        Node result = zipTable.lookup(searchZip);
        if (result != null) {
            System.out.println("Lookup Result for " + searchZip + ": " + result.name);
        } else {
            System.out.println("Zip code " + searchZip + " not found.");
        }
    }
}
