public class ZipHashCIB {

    public static void main(String[] args) {
        int numberOfIterations = 1000;
        String searchZip = "12237"; // Use a specific zip code for benchmarking.

        for (int arraySize = 1000; arraySize <= 20000; arraySize += 1000) {
            System.out.println("Benchmarking with array size: " + arraySize);
            ZipHashCollision2 zipTableOriginal = new ZipHashCollision2("postnummer.csv", arraySize);
            ZipHashCollision2 zipTableImproved = new ZipHashCollision2("postnummer.csv", arraySize);

            for (int i = 0; i < numberOfIterations; i++) {
                System.out.println("Running iteration: " + i);

                // Benchmark the original solution
                long startTimeOriginal = System.nanoTime();
                zipTableOriginal.lookup(searchZip);
                long endTimeOriginal = System.nanoTime();
                long originalTime = endTimeOriginal - startTimeOriginal;
                System.out.println("Original Lookup Time: " + originalTime + " ns");

                // Benchmark the improved solution
                long startTimeImproved = System.nanoTime();
                zipTableImproved.lookup(searchZip);
                long endTimeImproved = System.nanoTime();
                long improvedTime = endTimeImproved - startTimeImproved;
                System.out.println("Improved Lookup Time: " + improvedTime + " ns");
            }
        }
    }
}
