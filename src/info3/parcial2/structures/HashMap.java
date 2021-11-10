package info3.parcial2.structures;

public class HashMap<K extends Comparable<K>, T> {
    private HashEntry<K, T>[] table;
    private int size;

    public HashMap() {
        table = new HashEntry[10000];
        size = 10000;
    }

    public HashMap(int size) {
        table = new HashEntry[size];
        this.size = size;
    }

    public void put(K key, T value) throws Exception {
        int pos = inRange(key);
        if (table[pos] != null)
            if (value != table[pos].getData()) {
                table[pos] = new HashEntry<K, T>(key, value);
            } else {

            }
        ;

        table[pos] = new HashEntry<K, T>(key, value);
    }

    public T get(K key) throws Exception {
        int pos = inRange(key);
        if (table[pos] == null || table[pos].getKey().compareTo(key) != 0)
            throw new Exception("not found");

        return table[pos].getData();
    }

    public void remove(K key) throws Exception {
        int pos = inRange(key);
        if (table[pos] == null || table[pos].getKey() != key)
            throw new Exception("not found");

        table[pos] = null;
    }

    private int inRange(K key) {
        return hashFunc(key) % size;
    }

    private int hashFunc(K key) {
        if (key == null) {
            return 0;
        }
        return Math.abs(key.hashCode() % size);
    }

}