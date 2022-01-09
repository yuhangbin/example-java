package base.reference.string;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author yuhangbin
 * @date 2022/1/5
 **/
public class StringReferenceTest {

	@Test
	void testStaticString() {
		UseStaticConstant useConstant = new UseStaticConstant();
		Constant.value = "A";
		Assertions.assertEquals("default", useConstant.getV());
	}

	@Test
	void testString() {
		UseConstant useConstant = new UseConstant();
		Constant.value = "A";
		Assertions.assertEquals("default", useConstant.getV());
	}

	public static void main(String[] args) {
		UseConstant useConstant = new UseConstant();
		Constant.value = "A";
		System.out.println(useConstant.getV());
	}
}
