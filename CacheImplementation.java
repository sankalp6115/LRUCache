public class CacheImplementation {
    public static void main(String[] args) {
        LRUCache<Integer,String> cache = new LRUCache<Integer,String>(5);
        // filling
        cache.put(1,"Aerobics");
        cache.put(2,"Biking");
        cache.put(3,"Cycling");
        cache.put(4,"Deadlifting");

        cache.print();

        // get
        cache.get(2);
        cache.print();

        // update
        cache.put(2,"Bodybuilding");
        cache.print();
        
        // put
        cache.put(5,"E-Sports");
        cache.print();
    }
}
