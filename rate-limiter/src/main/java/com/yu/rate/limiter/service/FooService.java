package com.yu.rate.limiter.service;

import com.alibaba.csp.sentinel.Entry;
import com.alibaba.csp.sentinel.SphU;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.yu.rate.limiter.config.SentinelAutoConfiguration;

/**
 * @author yuhangbin
 * @date 2023/12/18
 **/
public class FooService {

	public static final String FALLBACK = "fallback";

	public String foo() {
		try (Entry entry = SphU.entry(SentinelAutoConfiguration.RESOURCE)){
			return "foo";
		} catch (BlockException e) {
			return fallback();
		}
	}

	public String fallback() {
		return "fallback";
	}


}
