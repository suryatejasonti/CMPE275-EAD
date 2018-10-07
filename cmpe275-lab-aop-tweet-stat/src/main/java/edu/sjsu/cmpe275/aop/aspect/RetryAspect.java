package edu.sjsu.cmpe275.aop.aspect;

import java.io.IOException;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import org.aspectj.lang.annotation.Around;

@Aspect
@Order(1)
public class RetryAspect {
    /***
     * Following is a dummy implementation of this aspect.
     * You are expected to provide an actual implementation based on the requirements, including adding/removing advices as needed.
     */
	int count = 0;
	int maxTries = 3;
	@Around("execution(public void edu.sjsu.cmpe275.aop.TweetService.*(..))")
	public void dummyAdvice(ProceedingJoinPoint joinPoint) throws Throwable {
		System.out.printf("Prior to the executuion of the metohd %s\n", joinPoint.getSignature().getName());
		Object result = null;
		while(true) {
			try {
				result = joinPoint.proceed();
				System.out.printf("Finished the executuion of the metohd %s with result %s\n", joinPoint.getSignature().getName(), result);
				break;
			}
			catch (IOException io) {
				System.out.println("Network failure retrying again");
				if (++count == maxTries) throw new Throwable();
			}
			catch (Throwable e) {
				e.printStackTrace();
				System.out.printf("Aborted the executuion of the metohd %s\n", joinPoint.getSignature().getName());
			}
		}
		
	}

}
