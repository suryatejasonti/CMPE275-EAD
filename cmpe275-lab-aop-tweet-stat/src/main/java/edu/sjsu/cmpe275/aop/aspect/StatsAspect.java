package edu.sjsu.cmpe275.aop.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;

import edu.sjsu.cmpe275.aop.TweetStatsImpl;

@Aspect
@Order(0)
public class StatsAspect {
    /***
     * Following is a dummy implementation of this aspect.
     * You are expected to provide an actual implementation based on the requirements, including adding/removing advices as needed.
     */

	@Autowired TweetStatsImpl stats;
	
	@After("execution(public void edu.sjsu.cmpe275.aop.TweetService.tweet(..))")
	public void dummyAfterAdvice(JoinPoint joinPoint) {
		Object[] args = joinPoint.getArgs();
		System.out.printf("After the executuion of the metohd %s\n", joinPoint.getSignature().getName());
		stats.addTweet((String)args[0], (String)args[1]);
	}
	
	@Before("execution(public void edu.sjsu.cmpe275.aop.TweetService.follow(..))")
	public void dummyBeforeAdviceFollow(JoinPoint joinPoint) {
		Object[] args = joinPoint.getArgs();
		System.out.printf("Before the executuion of the metohd %s\n", joinPoint.getSignature().getName());
		stats.addFollow((String)args[0], (String)args[1]);
	}
	
	@Before("execution(public void edu.sjsu.cmpe275.aop.TweetService.block(..))")
	public void dummyBeforeAdviceBlock(JoinPoint joinPoint) {
		Object[] args = joinPoint.getArgs();
		System.out.printf("Before the executuion of the metohd %s\n", joinPoint.getSignature().getName());
		stats.addBlock((String)args[0], (String)args[1]);
	}
	
}
