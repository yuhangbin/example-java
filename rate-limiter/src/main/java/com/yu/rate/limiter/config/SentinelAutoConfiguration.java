package com.yu.rate.limiter.config;

import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author yuhangbin
 * @date 2023/12/18
 **/
public class SentinelAutoConfiguration {

	public static final String RESOURCE = "resource-1";
	public static final int MAX_QPS = 10;

	static {
		initFlowRule();
	}

	public static void initFlowRule() {
		List<FlowRule> flowRules = new LinkedList<>();
		FlowRule flowRule = new FlowRule();
		flowRule.setResource(RESOURCE);
		flowRule.setGrade(RuleConstant.FLOW_GRADE_QPS);
		// max qps is 10
		flowRule.setCount(MAX_QPS);
		flowRules.add(flowRule);
		FlowRuleManager.loadRules(flowRules);
	}
}
