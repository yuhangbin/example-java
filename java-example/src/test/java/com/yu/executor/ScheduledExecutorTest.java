package com.yu.executor;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author yuhangbin
 * @date 2022/2/17
 **/
public class ScheduledExecutorTest {

	@Test
	void testScheduleExecutor() throws Exception {
		AtomicInteger count = new AtomicInteger(0);
		ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);
		scheduledExecutorService.scheduleAtFixedRate(() -> {
			System.out.println(count.incrementAndGet());
		}, 0, 2, TimeUnit.SECONDS);
		Thread.sleep(9*1000);
		scheduledExecutorService.shutdown();
		Assertions.assertEquals(5, count.get());
	}
}
