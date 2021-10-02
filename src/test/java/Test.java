import lombok.extern.slf4j.Slf4j;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.function.BiConsumer;

/**
 * @author yuhangbin
 * @date 2021/6/30
 **/
@Slf4j
public class Test<T extends Integer,U extends Integer> {
    public T t;
    public U u;

    public Test(T t, U u) {
        this.t = t;
        this.u = u;
    }

    public static void main(String[] args) {
        List<Integer> list = new LinkedList<>();
        list.add(1);
        list.add(2);
        list.add(2);
        Iterator<Integer> integers = list.iterator();
        while (integers.hasNext()){
            integers.remove();
            System.out.println("remove: " + integers.next());
        }
        System.out.println(list.size());
    }
    private void functionA(BiConsumer<T, U> consumer) {
            consumer.andThen(((t, u) -> {
                System.out.println("then x: " + t);
                System.out.println("then y: " + u);
            })).accept(t, u);
    }
}
