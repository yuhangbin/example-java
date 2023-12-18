package com.yu.rate.limiter.service;

import com.yu.rate.limiter.config.SentinelAutoConfiguration;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author yuhangbin
 * @date 2023/12/18
 **/
public class FooServiceTest {

	FooService fooService = new FooService();

	@Test
	void testFoo() {
		SentinelAutoConfiguration.initFlowRule();
		int counter = 0;
		while (true) {
			if (fooService.foo().equals("fallback")){
				break;
			}
			counter++;
		}
		Assertions.assertEquals(SentinelAutoConfiguration.MAX_QPS, counter);
	}
}
