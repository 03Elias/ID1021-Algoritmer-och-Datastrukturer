import java.io.BufferedReader;
import java.io.FileReader;

public class ZipHashExperiment {
    int[] keys;
    int max;

    public ZipHashExperiment(String file) {
        keys = new int[10000]; // Assuming you have less than 10,000 zip codes
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            int i = 0;
            while ((line = br.readLine()) != null) {
                String[] row = line.split(",");
                int zip = Integer.parseInt(row[0].replaceAll("\\s", ""));
                keys[i++] = zip;
            }
            max = i;
        } catch (Exception e) {
            System.out.println("File " + file + " not found");
        }
    }

    public void collisions(int mod) {
        int[] data = new int[mod];
        int[] cols = new int[10];

        for (int i = 0; i < max; i++) {
            int index = keys[i] % mod;
            cols[data[index]]++;
            data[index]++;
        }

        System.out.print(mod);
        for (int i = 0; i < 10; i++) {
            System.out.print("\t" + cols[i]);
        }
        System.out.println();
    }

    public static void main(String[] args) {
        ZipHashExperiment zipExperiment = new ZipHashExperiment("postnummer.csv");
        
        System.out.println("Mod Value\tCollisions Count (0-9)");
        int[] modValues = {10000, 20000, 12345, 13513, 13600, 14000};
        for (int mod : modValues) {
            zipExperiment.collisions(mod);
        }
    }
}
