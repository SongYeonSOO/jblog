package com.estsoft.jblog.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Aspect
@Component
public class MeasureDaoExcutionTimeAspect {

	@Around("execution( * *..dao.*.*(..)) || execution( * *..service.*.*(..)) || execution( * *..controller.*.*(..))")
	public Object around(ProceedingJoinPoint pjp) throws Throwable {
		StopWatch stopWatch = new StopWatch();
		stopWatch.start();

		Object result = pjp.proceed();

		stopWatch.stop();
		String taskName = pjp.getTarget().getClass() + "." + pjp.getSignature().getName();
		System.out.println("[ExecutionTime][" + taskName + "] : " + stopWatch.getTotalTimeMillis() + "millis");

		return result;
	}
}
