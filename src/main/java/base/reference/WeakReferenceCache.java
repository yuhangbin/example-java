package base.reference;

import java.lang.ref.WeakReference;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author yuhangbin
 * @date 2021/9/16
 **/
public class WeakReferenceCache<K,V> {
    private final Map<WeakReference<K>, WeakReference<V>> cache = new ConcurrentHashMap<>();

    public void put(K key, V value){
        // check hashcode
        CustomWeakReference<K> customWeakReference = new CustomWeakReference<>(key);
        System.out.println(customWeakReference.hashCode());
        cache.put(customWeakReference, new WeakReference<>(value));
    }

    public int size() {
        return cache.size();
    }

    public V get(K key) {
        CustomWeakReference<K> customWeakReference = new CustomWeakReference<>(key);
        System.out.println("===" + customWeakReference.hashCode());
        WeakReference<V> vWeakReference= cache.get(customWeakReference);
        return vWeakReference.get();
    }

    public static class CustomWeakReference<T> extends WeakReference<T> {
        public CustomWeakReference(T referent) {
            super(referent);
        }

        @Override
        public int hashCode() {
            return Objects.requireNonNull(super.get()).hashCode();
        }
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        cache.forEach((k, v) -> {
            result.append(k.get())
                    .append(": ")
                    .append(v.get())
                    .append("\n");
        });
        return result.toString();
    }
}
