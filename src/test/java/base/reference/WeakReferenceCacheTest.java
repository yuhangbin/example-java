package base.reference;

import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author yuhangbin
 * @date 2021/9/16
 **/
public class WeakReferenceCacheTest {

    @Test
    public void gcTest(){
        WeakReferenceCache<Object, Object> cache = new WeakReferenceCache<>();
        cache.put(new Element("1"), new Element("2"));
        Assertions.assertEquals(1, cache.size());
        System.gc();
        System.out.println(cache.toString());
    }

    @AllArgsConstructor
    public static class Element{
        private String content;
    }

    @Test
    void getTest() {
        WeakReferenceCache<Integer, Integer> cache = new WeakReferenceCache<>();
        cache.put(1, 2);
        Assertions.assertEquals(2, cache.get(1));
    }
}
