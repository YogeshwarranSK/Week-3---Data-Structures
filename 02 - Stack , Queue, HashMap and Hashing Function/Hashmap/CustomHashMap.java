package Day13.Hashmap;
import java.util.LinkedList;

class CustomHashMap<K, V> {
    private class Node {
        K key;
        V value;

        Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    private LinkedList<Node>[] table;
    private int capacity = 16;

    public CustomHashMap() {
        table = new LinkedList[capacity];
        for (int i = 0; i < capacity; i++) {
            table[i] = new LinkedList<>();
        }
    }

    private int hash(K key) {
        return Math.abs(key.hashCode()) % capacity;
    }

    public void put(K key, V value) {
        int index = hash(key);
        LinkedList<Node> bucket = table[index];

        for (Node node : bucket) {
            if (node.key.equals(key)) {
                node.value = value;
                return;
            }
        }
        bucket.add(new Node(key, value));
    }

    public V get(K key) {
        int index = hash(key);
        LinkedList<Node> bucket = table[index];

        for (Node node : bucket) {
            if (node.key.equals(key)) {
                return node.value;
            }
        }
        return null;
    }

    public void remove(K key) {
        int index = hash(key);
        LinkedList<Node> bucket = table[index];

        for (Node node : bucket) {
            if (node.key.equals(key)) {
                bucket.remove(node);
                return;
            }
        }
    }

    public boolean containsKey(K key) {
        return get(key) != null;
    }

    public void display() {
        for (int i = 0; i < capacity; i++) {
            if (!table[i].isEmpty()) {
                System.out.print("Bucket " + i + ": ");
                for (Node node : table[i]) {
                    System.out.print("[" + node.key + "=" + node.value + "] ");
                }
                System.out.println();
            }
        }
    }

    public static void main(String[] args) {
        CustomHashMap<String, Integer> map = new CustomHashMap<>();

        map.put("Alice", 25);
        map.put("Bob", 30);
        map.put("Charlie", 35);

        System.out.println("Alice's Age: " + map.get("Alice"));
        System.out.println("Contains Bob? " + map.containsKey("Bob"));

        map.remove("Bob");
        System.out.println("Contains Bob after removal? " + map.containsKey("Bob"));

        map.display();
    }
}
