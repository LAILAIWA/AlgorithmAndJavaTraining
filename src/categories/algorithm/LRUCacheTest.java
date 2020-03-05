package categories.algorithm;


public class LRUCacheTest {
    private static LRUCache<String, Integer> cache = new LRUCache<>(10);
    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            cache.put("k" + i, i);
        }
        System.out.println("all cache :'{}'" + cache);
        cache.get("k3");
        System.out.println("get k3 :'{}'"+ cache);
        cache.get("k4");
        System.out.println("get k4 :'{}'"+ cache);
        cache.get("k4");
        System.out.println("get k4 :'{}'"+ cache);
        cache.put("k" + 10, 10);
        System.out.println("After running the LRU algorithm cache :'{}'"+ cache);
    }
}
