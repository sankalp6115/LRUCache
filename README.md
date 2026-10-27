# LRU Cache Implementation in Java

A lightweight, generic, in-memory Least Recently Used (LRU) cache written in Java without external dependencies.

---

## Overview

An LRU Cache retains a fixed number of items. When the cache reaches its capacity and a new item needs to be added, it evicts the item that has not been accessed for the longest time (the least recently used item).

To achieve constant time complexity for both reads and writes, the implementation combines two data structures:
1. **Hash Map (`HashMap<K, Node<K, V>>`)**: Provides O(1) key-based lookups to directly access nodes in the linked list.
2. **Doubly Linked List (`DoublyLinkedList<K, V>`)**: Maintains access order with O(1) node additions, removals, and relocations.

---

## Design and Data Flow

The doubly linked list uses dummy sentinel nodes (`head` and `tail`) to eliminate null pointer checks at the list boundaries:

```
[head] <---> [LRU Node] <---> ... <---> [MRU Node] <---> [tail]
```

- **Least Recently Used (LRU):** Positioned immediately after `head` (`head.next`). When capacity is reached, `removeFirst()` evicts this node.
- **Most Recently Used (MRU):** Positioned immediately before `tail` (`tail.prev`). Any newly added or accessed item moves here.

### Operations

- **`get(K key)`**:
  1. Check if the key exists in the hash map.
  2. If missing, return `null`.
  3. If present, move the corresponding node to the MRU position (`tail.prev`) and return its value.

- **`put(K key, V value)`**:
  1. If the key already exists, update its value and move the node to the MRU position.
  2. If the cache is full (`size == capacity`), evict the node at `head.next` from both the linked list and the map.
  3. Create a new node, insert it at `tail.prev`, and store its reference in the map.

- **`remove(K key)`**:
  1. Locate the node via the map.
  2. Unlink it from the doubly linked list and remove the key from the map.

---

## Complexity

| Operation | Time Complexity | Space Complexity |
|-----------|-----------------|------------------|
| `get`     | O(1)            | O(1)             |
| `put`     | O(1)            | O(1)             |
| `remove`  | O(1)            | O(1)             |
| Overall   | -               | O(capacity)      |

---

## Project Structure

```
LRUCache/
├── DoublyLinkedList.java    # Doubly linked list with sentinel nodes
├── LRUCache.java            # Generic LRU Cache core logic
├── CacheImplementation.java # Entry point and usage demonstrations
├── REFERENCES.md            # Reference links
└── README.md                # Documentation
```

---

## Usage Example

```java
public class Example {
    public static void main(String[] args) {
        // Initialize cache with capacity of 3
        LRUCache<Integer, String> cache = new LRUCache<>(3);

        cache.put(1, "Alpha");
        cache.put(2, "Beta");
        cache.put(3, "Gamma");

        // Access key 1 (makes 1 the most recently used)
        cache.get(1);

        // Add key 4 (causes key 2 to be evicted because 1 and 3 were accessed more recently)
        cache.put(4, "Delta");

        // Print current state in LRU order
        cache.print();
    }
}
```

---

## How to Compile and Run

From inside the `LRUCache` folder:

```bash
# Compile all files
javac *.java

# Run the driver class
java CacheImplementation
```
