import java.io.BufferedReader;
import java.io.FileReader;

public class ZipArray {
    Node[] data = new Node[100000]; // Increase the array size to accommodate zip codes from 0 to 99999.

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

    public ZipArray(String file) {
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] row = line.split(",");
                String zip = row[0].replaceAll("\\s", ""); // Remove spaces from the zip code.
                data[Integer.parseInt(zip)] = new Node(zip, row[1], Integer.valueOf(row[2]));
            }
        } catch (Exception e) {
            System.out.println("File " + file + " not found");
        }
    }

    public Node lookup(String zip) {
        int index = Integer.parseInt(zip);
        if (index >= 0 && index < data.length) {
            return data[index];
        } else {
            return null; // Zip code not found
        }
    }
    public Node binarySearch(String zip) {
        int left = 0;
        int right = data.length - 1;
        int zipInt = Integer.parseInt(zip);
    
        while (left <= right) {
            int mid = left + (right - left) / 2;
    
            if (data[mid] == null) {
                return null; // Zip code not found
            }
    
            int currentZip = Integer.parseInt(data[mid].code);
    
            if (currentZip == zipInt) {
                return data[mid];
            } else if (currentZip < zipInt) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
    
        return null; // Zip code not found
    }
    

    public static void main(String[] args) {
        ZipArray zipTable = new ZipArray("postnummer.csv");

        // Example usage of the lookup method:
        String searchZip = "12237";
        ZipArray.Node result = zipTable.lookup(searchZip);
        if (result != null) {
            System.out.println("Lookup Result for " + searchZip + ": " + result.name);
        } else {
            System.out.println("Zip code " + searchZip + " not found.");
        }
    }
}
