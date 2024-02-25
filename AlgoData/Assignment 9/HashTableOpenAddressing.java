class HashTableOpenAddressing {
    private int size;
    private Entry[] array;

    public HashTableOpenAddressing(int size) {
        this.size = size;
        array = new Entry[size];
    }

    public int hash(int key) {
        return key % size;
    }

    public void insert(int key, String value) {
        int index = hash(key);
        while (array[index] != null) {
            index = (index + 1) % size;
        }
        array[index] = new Entry(key, value);
    }

    public String lookup(int key) {
        int index = hash(key);
        while (array[index] != null) {
            Entry entry = array[index];
            if (entry.key == key) {
                return entry.value;
            }
            index = (index + 1) % size;
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
        HashTableOpenAddressing hashTable = new HashTableOpenAddressing(100);
        hashTable.insert(12345, "Alice");
        hashTable.insert(54321, "Bob");
        System.out.println(hashTable.lookup(12345));  // Output: "Alice"
    }
}
