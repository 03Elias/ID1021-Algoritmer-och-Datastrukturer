import java.util.Arrays;
import java.util.Random;

public class HashTableBenchmark {
    public static void main(String[] args) {
        int[] tableSizes = {100, 500, 1000, 5000, 10000};
        int numTests = 1000;

        for (int size : tableSizes) {
            long[] examinationsBucket = new long[numTests];
            long[] examinationsOpenAddressing = new long[numTests];

            for (int i = 0; i < numTests; i++) {
                HashTableWithBuckets hashTableBucket = new HashTableWithBuckets(size);
                HashTableOpenAddressing hashTableOpenAddressing = new HashTableOpenAddressing(size);

                // Insert random data into the hash tables
                Random random = new Random();
                for (int j = 0; j < size; j++) {
                    int key = random.nextInt(100000);
                    String value = "Value" + key;
                    hashTableBucket.insert(key, value);
                    hashTableOpenAddressing.insert(key, value);
                }

                // Generate a random key for lookup
                int targetKey = random.nextInt(100000);

                // Measure the number of examinations needed for the bucket method
                int examinationsBucketCount = 0;
                String resultBucket = null;
                while (!String.valueOf(targetKey).equals(resultBucket)) {
                    resultBucket = hashTableBucket.lookup(targetKey);
                    examinationsBucketCount++;
                }

                examinationsBucket[i] = examinationsBucketCount;

                // Measure the number of examinations needed for the open addressing method
                int examinationsOpenAddressingCount = 0;
                String resultOpenAddressing = null;
                while (!String.valueOf(targetKey).equals(resultOpenAddressing)) {
                    resultOpenAddressing = hashTableOpenAddressing.lookup(targetKey);
                    examinationsOpenAddressingCount++;
                }

                examinationsOpenAddressing[i] = examinationsOpenAddressingCount;
            }

            // Sort the examination counts and find the median
            Arrays.sort(examinationsBucket);
            Arrays.sort(examinationsOpenAddressing);
            long medianBucket = examinationsBucket[numTests / 2];
            long medianOpenAddressing = examinationsOpenAddressing[numTests / 2];

            System.out.println("Table size: " + size);
            System.out.println("Median examinations (with buckets): " + medianBucket);
            System.out.println("Median examinations (open addressing): " + medianOpenAddressing);
            System.out.println();
        }
    }
}
