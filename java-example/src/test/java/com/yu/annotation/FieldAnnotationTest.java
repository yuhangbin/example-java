package com.yu.annotation;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;

/**
 * @author yuhangbin
 * @date 2022/2/14
 **/
public class FieldAnnotationTest {

	@Test
	void testGetAnnotation() {
		Field[] fields = Person.class.getFields();
		for (Field field : fields) {
			FieldAnnotation annotation = field.getAnnotation(FieldAnnotation.class);
			Assertions.assertEquals("charlie", annotation.value());
		}
	}
}
