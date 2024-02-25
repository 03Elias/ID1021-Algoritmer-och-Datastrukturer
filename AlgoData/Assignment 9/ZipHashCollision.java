import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class ZipHashCollision {
    Bucket[] data;
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

    public class Bucket {
        List<Node> nodes;

        public Bucket() {
            nodes = new ArrayList<>();
        }

        public void add(Node node) {
            nodes.add(node);
        }
    }

    public ZipHashCollision(String file) {
        data = new Bucket[10000]; // Initialize the array with buckets.
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] row = line.split(",");
                String zip = row[0].replaceAll("\\s", ""); // Remove spaces from the zip code.
                int index = Integer.parseInt(zip) % data.length;
                if (data[index] == null) {
                    data[index] = new Bucket();
                }
                data[index].add(new Node(zip, row[1], Integer.valueOf(row[2])));
            }
        } catch (Exception e) {
            System.out.println("File " + file + " not found");
        }
    }

    public Node lookup(String zip) {
        int index = Integer.parseInt(zip) % data.length;
        Bucket bucket = data[index];
        if (bucket != null) {
            for (Node node : bucket.nodes) {
                if (node.code.equals(zip)) {
                    return node;
                }
            }
        }
        return null; // Zip code not found
    }

    public static void main(String[] args) {
        ZipHashCollision zipTable = new ZipHashCollision("postnummer.csv");

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
