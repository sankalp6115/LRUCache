import java.util.HashMap;

public class LRUCache<K,V>{
    public final HashMap<K,Node<K,V>> map;
    public final DoublyLinkedList<K,V> dll;
    public final int capacity;

    public LRUCache(int capacity){
        if(capacity <= 0) throw new IllegalArgumentException("Capacity must be greater than 0");
        this.capacity = capacity;
        map = new HashMap<K,Node<K,V>>(capacity);
        dll = new DoublyLinkedList<K,V>();
    }

    public V get(K key){
        if(!map.containsKey(key)){
            return null;
        }
        Node<K,V> node = map.get(key);
        dll.moveToLast((node));
        return node.value;
    }

    public void put(K key,V value){
        if(map.containsKey(key)){
            Node<K,V> node = map.get(key);
            node.value = value;
            dll.moveToLast((node));
            return;
        }
        if(map.size() == capacity){
            Node<K,V> lru = dll.removeFirst();
            if(lru != null) map.remove(lru.key);
        }
        Node<K,V> node = new Node<>(key,value);
        dll.add(node);
        map.put(node.key,node);
        return;
    }

    public void remove(K key){
        if(!map.containsKey(key)) return;
        Node<K,V> node = map.get(key);
        map.remove(key);
        dll.remove(node);

        return;
    }

    public void print(){
        dll.print();
        return;
    }
}