class Node<K,V>{
    K key;
    V value;
    Node<K,V> next;
    Node<K,V> prev;

    public Node(K key,V value){
        this.key = key;
        this.value = value;
    }
}
class DoublyLinkedList<K,V> {
    private final Node<K,V> head;
    private final Node<K,V> tail;

    public DoublyLinkedList(){
        head = new Node<>(null,null);
        tail = new Node<>(null,null);

        head.next = tail;
        tail.prev = head;
    }

    // add node to last
    public void add(Node<K,V> node){
        node.prev = tail.prev;
        tail.prev.next = node;
        node.next = tail;
        tail.prev = node;
        return;
    }

    // removes specific node
    public Node<K,V> remove(Node<K,V> node){
        node.prev.next = node.next;
        node.next.prev = node.prev;
        return node;
    }

    // moves the just used key to the last i.e. tail.prev
    public void moveToLast(Node<K,V> node){
        remove(node);
        add(node);
        return;
    }
    
    // removes the LRU Key
    public Node<K,V> removeFirst(){
        if (head.next == tail) return null;
        Node<K, V> first = head.next;
        remove(first);
        return first;
    }

    // print
    public void print(){
        Node<K,V> temp = head.next;
        while(temp != tail){
            if(temp.next != tail) System.out.print(temp.key + ":" + temp.value + " <=> ");
            else System.out.println(temp.key + ":" + temp.value);
            temp = temp.next;
        }
        System.out.println();
        return;
    }

}

