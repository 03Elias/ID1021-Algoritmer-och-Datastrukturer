import java.util.ArrayList;
import java.util.List;

class HashTableWithBuckets {
    private int size;
    private List<List<Entry>> buckets;

    public HashTableWithBuckets(int size) {
        this.size = size;
        buckets = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            buckets.add(null);
        }
    }

    public int hash(int key) {
        return key % size;
    }

    public void insert(int key, String value) {
        int index = hash(key);
        if (buckets.get(index) == null) {
            buckets.set(index, new ArrayList<>());
        }
        buckets.get(index).add(new Entry(key, value));
    }

    public String lookup(int key) {
        int index = hash(key);
        List<Entry> bucket = buckets.get(index);
        if (bucket != null) {
            for (Entry entry : bucket) {
                if (entry.key == key) {
                    return entry.value;
                }
            }
        }
        return null;
    }

    private class Entry {
        int key;
        String value;

        Entry(int key, String value) {
            this.key = key;
            this.value = value;
        }
    }

    public static void main(String[] args) {
        HashTableWithBuckets hashTable = new HashTableWithBuckets(100);
        hashTable.insert(12345, "Alice");
        hashTable.insert(54321, "Bob");
        System.out.println(hashTable.lookup(12345));  // Output: "Alice"
    }
}
