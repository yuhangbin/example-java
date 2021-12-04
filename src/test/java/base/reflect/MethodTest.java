package base.reflect;

import lombok.Data;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;

/**
 * @author yuhangbin
 * @date 2021/11/12
 **/
public class MethodTest {

    @Test
    void testGetMethod() {
        Method[] methods = A.class.getMethods();
        Method[] declareMethods = A.class.getDeclaredMethods();
        Assertions.assertTrue(methods.length >= declareMethods.length);
    }

    @Data
    public static class A {
        private Integer value;
    }
}
